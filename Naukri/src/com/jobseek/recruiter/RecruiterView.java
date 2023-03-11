package com.jobseek.recruiter;

import java.time.LocalDate;
import java.util.Scanner;

import utility.Validator;

public class RecruiterView {
	
	private Scanner scanner = new Scanner(System.in);
	private RecruiterViewModel recruiterViewModel;
	
	public RecruiterView() {
		this.recruiterViewModel = new RecruiterViewModel(this);
	}

	public void jobRecruiterOptions(String emailId) {
		boolean askAgain = true;
		while (askAgain) {
			System.out.println("Press 1 to Show Posts");
			System.out.println("Press 2 to Add Post");
			System.out.println("Press 3 to Delete Post");
			System.out.println("Press 4 to Exit");
			char option = scanner.next().charAt(0);
			switch (option) {
			case '1':
				
				break;
			case '2':
				addPost(emailId);
				break;
			case '3':
				//delete post
				break;
			case '4':
				askAgain = false;
				break;
			default:
				System.out.println("Invalid Option");
			}
		}
	}
	private void addPost(String emailId) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Role: ");
		String role = getString();
		System.out.println("Description: ");
		String description = getString();
		System.out.println("Package: ");
		float ctcPackage = Float.parseFloat(getString());
		System.out.println("Skils: ");
		String skills = getString();
		System.out.println("Experience : ");
		int experience = Integer.parseInt(getString());
		System.out.println("Deadline : YYYY-MM-DD");
		LocalDate deadline = getDate();
		recruiterViewModel.addPost(emailId, role, description, ctcPackage, skills, experience, deadline);
	}

	private String getString() {
		return Validator.getString();
	}
	private LocalDate getDate() {
		return Validator.getDate();
	}

	public void addedSuccessfully() {
		System.out.println("------------------------------------------------------------------");
		System.out.println("Post added successfully!.");
	}

}