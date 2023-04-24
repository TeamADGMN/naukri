package com.jobseek.main;

import java.util.Scanner;

import com.jobseek.login.LoginView;

public class Main {
	Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Main main = new Main();
		main.options();
	}

	public void options() {
		LoginView loginView = new LoginView();

		while (true) {
			System.out.println("Welcome to Jop Portal");
			System.out.println("Press 1 for Jobseeker ");
			System.out.println("Press 2 for Recruiter");
			System.out.println("Press 3 to Exit");
			System.out.println("ENter your choice ");
			char option = scanner.next().charAt(0);
			switch (option) {
				case '1':
					loginView.loginOptions("jobSeeker");
					break;
				case '2':
					loginView.loginOptions("recruiter");
					break;
				case '3':
					System.exit(0);
				default:
					System.out.println("Invalid Option");
					options();
					break;
			}
		}
	}
}
