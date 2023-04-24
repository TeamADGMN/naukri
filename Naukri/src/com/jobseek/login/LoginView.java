package com.jobseek.login;

import java.util.Scanner;

import com.jobseek.jobseeker.JobSeekerView;
import com.jobseek.recruiter.RecruiterView;

import utility.Validator;

public class LoginView {
	private Scanner scanner = new Scanner(System.in);
	private LoginModel loginModel;

	public LoginView() {
		this.loginModel = new LoginModel(this);
	}

	public void loginOptions(String user) {
		boolean askAgain = true;
		while (askAgain) {
			System.out.println("Press 1 to Login");
			System.out.println("Press 2 to SignUp");
			System.out.println("Press 3 to Exit");
			char option = scanner.next().charAt(0);
			switch (option) {
			case '1':
				credentials(user);
				break;

			case '2':
				if (signup(user)) {
					if (user.equals("jobSeeker")) {
						System.out.println("job seeker profile added");
						loginOptions(user);
					} else if (user.equals("recruiter")) {
						System.out.println("recuiter seeker profile added");
						loginOptions(user);
					}
				}
			case '3':
				askAgain = false;
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}

	private void credentials(String user) {
		System.out.println("Enter EmailId: ");
		String emailId = scanner.next();
		if (!Validator.validateEmail(emailId)) {
			System.out.println("Invalid Email Id");
			credentials(user);
		} else {
			System.out.println("Enter your password:");
			String password = scanner.next();
			if (loginModel.checkCredentials(emailId, password)) {
				if (user.equals("jobSeeker")) {
					JobSeekerView jobSeekerView = new JobSeekerView(emailId);
					jobSeekerView.jobSeekerOptions(emailId);
				} else if (user.equals("recruiter")) {
					RecruiterView recruiter = new RecruiterView();
					recruiter.jobRecruiterOptions(emailId);
				}
			} else {
				System.out.println("Invalid data");
			}
		}
	}

	public boolean signup(String user) {
		System.out.println("Enter your Name:");
		String username = scanner.next();
		return enterEmail(username, user);
	}

	private boolean enterEmail(String username, String user) {
		System.out.println("Enter Email Id:");
		String emailId = scanner.next();
		if (!Validator.validateEmail(emailId)) {
			System.out.println("Invalid Email Id");
			enterEmail(username, user);
		} else {
			return enterPhoneNumber(username, emailId, user);
		}
		return true;
	}

	private boolean enterPhoneNumber(String username, String emailId, String user) {
		boolean flag = true;
		System.out.println("Enter Phone Number:");
		String phoneNumber = scanner.next();
		if (!Validator.validatePhone(phoneNumber)) {
			System.out.println("Invalid Phone Number");
			enterPhoneNumber(username, emailId, user);
		} else {
			System.out.println("Enter Password");
			String password = scanner.next();
			flag = loginModel.addUser(user, username, emailId, phoneNumber, password);
		}
		return flag;
	}

	public void invalidCredentials(String errMsg) {
		System.out.println(errMsg);
	}

}
