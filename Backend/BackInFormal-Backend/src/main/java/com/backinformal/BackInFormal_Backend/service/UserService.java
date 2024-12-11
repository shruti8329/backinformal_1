package com.backinformal.BackInFormal_Backend.service;

import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backinformal.BackInFormal_Backend.DTO.LoginRequest;
import com.backinformal.BackInFormal_Backend.DTO.UserForgetPasswordDTO;
import com.backinformal.BackInFormal_Backend.Exception.UserNotFoundException;
import com.backinformal.BackInFormal_Backend.entity.UserMaster;
import com.backinformal.BackInFormal_Backend.repository.UserMasterRepository;

@Service
public class UserService {

	@Autowired
	private UserMasterRepository userMasterRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender mailSender;

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	public ResponseEntity<String> saveUser(UserMaster userMaster) {
		Optional<UserMaster> existingUser = userMasterRepository.findByUserName(userMaster.getUserName());

		System.out.println("USER-NAME -1-> " + userMaster.getUserName());

		if (existingUser.isPresent()) {
			System.out.println("USER-NAME -2-> " + userMaster.getUserName());
			return new ResponseEntity<>("Username already exists. Please choose a different username",
					HttpStatus.BAD_REQUEST);
		} else {
			userMaster.setPassword(passwordEncoder.encode(userMaster.getPassword()));
			userMasterRepository.save(userMaster);
			return ResponseEntity.ok("User Added  Successfully");

		}

	}

	public UserMaster findByUserName(String userName) {

		Optional<UserMaster> user = userMasterRepository.findByUserName(userName);

		UserMaster userMaster = null;

		if (user.isPresent()) {
			userMaster = user.get();
		} else {
			throw new RuntimeException("Did not find User name - " + userName);
		}
		return userMaster;
	}

	public ResponseEntity<String> userLogIn(LoginRequest loginRequest) {
		System.out.println("User name " + loginRequest.getUserName());
		UserMaster user = userMasterRepository.findByUserName(loginRequest.getUserName()).orElseThrow(()-> new UserNotFoundException("User Not found"));
		System.out.println("After user exist " + user.getUserName());

//		if (loginRequest.getUserName().equals(user.getUserName())) {
			if (user != null) {
				if (loginRequest.getUserName().equals(user.getUserName())) {
				if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
					return ResponseEntity.ok("User Log in Successfully");
				}
				return new ResponseEntity<>("Password is incorrect. Plz Try again", HttpStatus.BAD_REQUEST);

			}
				return new ResponseEntity<>("User Name does not match!!! Plz try again", HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>("User Not Exit!!! Plz Sign up", HttpStatus.BAD_REQUEST);
			
//		}
//		return new ResponseEntity<>("User Name does not match!!! Plz try again", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> userUpdatePasswordByEmail(UserForgetPasswordDTO forgetPassObj) {
		//
		UserMaster existedObj = userMasterRepository.findByEmail(forgetPassObj.getEmail()).orElse(null);

		if (existedObj != null) {

			if (passwordEncoder.matches(forgetPassObj.getOldPassword(), existedObj.getPassword())) {
				existedObj.setPassword(passwordEncoder.encode(forgetPassObj.getNewPassword()));

				userMasterRepository.save(existedObj);
				return new ResponseEntity<String>(
						"Password is change for email " + forgetPassObj.getEmail() + " successfully", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Old Password is incorrect for email " + forgetPassObj.getEmail(),
					HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>("User does not exist with email " + forgetPassObj.getEmail(),
				HttpStatus.BAD_REQUEST);
	}
	
	// OTP based methods
	public ResponseEntity<?> sendOTPtoHisMail(String userEmail) {
		// TODO Auto-generated method stub
		System.out.println("email --> "+userEmail);
		UserMaster user = userMasterRepository.findByEmail(userEmail).orElseThrow(()-> new RuntimeException("User not found"));
		
		String otpGenrated = generateOTP();
		
		user.setOtpNum(otpGenrated);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("user_bussiness@gmail.com");
		mail.setTo(userEmail);
		mail.setSubject("OTP for Password Reset");
		mail.setText("Dear User, OTP to reset your account password is "+otpGenrated+". Do not share with anyone.");
		userMasterRepository.save(user);
		mailSender.send(mail);
		
		return ResponseEntity.ok("OTP send to mail successfully!!!");
	}
	
	//Helping method to generate random 6 digit OTP 
	private static String generateOTP()
	{
		Random random = new Random();
		int otp = random.nextInt(900000) + 100000; // Generates a number between 100000 and 999999
        return String.format("%06d", otp); // Formats the number to ensure it is 6 digits
	}


	public ResponseEntity<?> verifyOTP(String email, String otpPass) {
		UserMaster user = userMasterRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
		
		if(user.getOtpNum().equals(otpPass))
		{
			
			return new ResponseEntity<>("OTP is correct", HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>("OTP is wrong", HttpStatus.BAD_REQUEST);
		
	}


	public ResponseEntity<?> updatePassword(String email, String newPassword) {
		UserMaster user = userMasterRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));
		user.setOtpNum(null);
		user.setPassword(newPassword);
		userMasterRepository.save(user);
		return new ResponseEntity<>("Password has been change", HttpStatus.OK);
	}
}
