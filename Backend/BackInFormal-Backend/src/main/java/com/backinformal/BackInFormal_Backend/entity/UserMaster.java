package com.backinformal.BackInFormal_Backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

 
public class UserMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String email;
    private String password;
    private String roles;
    private String otpNum;
	public UserMaster(int userId, String userName, String email, String password, String roles, String otpNum) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.otpNum =otpNum;
	}
	public UserMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	
	
	public String getOtpNum() {
		return otpNum;
	}
	public void setOtpNum(String otpNum) {
		this.otpNum = otpNum;
	}
	@Override
	public String toString() {
		return "UserMaster [userId=" + userId + ", userName=" + userName + ", email=" + email + ", password=" + password
				+ ", roles=" + roles + "]";
	}
    
    

}
