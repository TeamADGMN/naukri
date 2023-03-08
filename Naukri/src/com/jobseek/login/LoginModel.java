package com.jobseek.login;

import com.repository.Repository;

public class LoginModel {
private LoginView loginView;
	public LoginModel(LoginView loginView) {
		this.loginView  = loginView;
	}
	public boolean checkCredentials(String mailId, String password) {
		return Repository.getInstance().checkCredentials(mailId,password);
	}
	public boolean addUser(String user,String userName, String emailId, String phoneNumber, String password) {
		boolean isExistingUser=false;
		if(user.equals("jobSeeker"))
			isExistingUser=Repository.getInstance().addUser(userName,emailId,phoneNumber,password);
		else if(user.equals("requiter"))
		{
			isExistingUser=Repository.getInstance().addRequiter(userName,emailId,phoneNumber,password);
		}
			
		return isExistingUser;	
	}


}
