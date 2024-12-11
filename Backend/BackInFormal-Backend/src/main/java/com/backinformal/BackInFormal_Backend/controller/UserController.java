package com.backinformal.BackInFormal_Backend.controller;

import com.backinformal.BackInFormal_Backend.DTO.LoginRequest;
import com.backinformal.BackInFormal_Backend.DTO.UserForgetPasswordDTO;
import com.backinformal.BackInFormal_Backend.repository.UserMasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backinformal.BackInFormal_Backend.entity.UserMaster;
import com.backinformal.BackInFormal_Backend.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMasterRepository userMasterRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/new-user")
    public ResponseEntity<String> signUp(@RequestBody UserMaster userMaster) {

        if (userMaster.getRoles() == null) {
            userMaster.setRoles("ROLE_ADMIN");
        }

        return userService.saveUser(userMaster);
    }


    //    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam("username") String username,
//                                        @RequestParam("password") String password) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return ResponseEntity.ok("Login successful");
//        } catch (Exception e) {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
//    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//
//        UserMaster existingUser = userService.findByUserName(loginRequest.getUserName());
//
//        if (existingUser != null &&
//                existingUser.getPassword().equals(loginRequest.getPassword())) {
//            return ResponseEntity.ok("Login successful");
//        } else if (existingUser == null) {
//
//            return ResponseEntity.status(404).body("User not found");
//        }
//        return ResponseEntity.status(401).body("Invalid credentials");
//
//    }


    @GetMapping("/welcome")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome to the application");
    }


    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> testAdmin() {
        return ResponseEntity.ok("I am Admin");
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<String> testUser() {
        return ResponseEntity.ok("I am user");
    }

    @PostMapping("/user-login")
    ResponseEntity<String> userLogIn(@RequestBody LoginRequest loginRequest)
    {
        return userService.userLogIn(loginRequest);
    }
    
    //Forget Password
    @PostMapping("/forget-password")
    ResponseEntity<String> userForgetPassword(@RequestBody UserForgetPasswordDTO forgetPassObj)
    {
    	return userService.userUpdatePasswordByEmail(forgetPassObj);
    }
    
    //OTP based contoller
    @PostMapping("/forget-password-otp")
	ResponseEntity<?> userForgertPassword(@RequestParam String email)
	{
		return userService.sendOTPtoHisMail(email);
	}
	
	@PostMapping("/verify-otp")
	ResponseEntity<?> verifyOTPwithRespectToEmail(@RequestParam String email, @RequestParam String otpPass)
	{
		return userService.verifyOTP(email, otpPass);
	}
	
	@PostMapping("/password-reset")
	ResponseEntity<?> changePasswordOfEmail(@RequestParam String email, @RequestParam String newPassword)
	{
		return userService.updatePassword(email, newPassword);
	}
}
