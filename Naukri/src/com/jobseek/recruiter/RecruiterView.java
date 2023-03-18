package com.jobseek.recruiter;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.jobseek.dto.JobPosts;

import utility.Validator;

public class RecruiterView {

	private Scanner scanner = new Scanner(System.in);
	private RecruiterViewModel recruiterViewModel;

	public static void main(String[] args) {
		RecruiterView recruiterView = new RecruiterView();
		recruiterView.jobRecruiterOptions("dharanish@gmail.com");
	}

	public RecruiterView() {
		this.recruiterViewModel = new RecruiterViewModel(this);
	}

	public void jobRecruiterOptions(String emailId) { // mail id
		boolean askAgain = true;
		while (askAgain) {
			System.out.println("----------------------------------------------------------------");
			System.out.println("Press 1 to Show Posts");
			System.out.println("Press 2 to Add Post");
			System.out.println("Press 3 to Delete Post");
			System.out.println("Press 4 to Exit");
			char option = scanner.next().charAt(0);
			switch (option) {
				case '1':
					List<JobPosts> jobPosts = recruiterViewModel.getPosts(emailId);
					showPosts(jobPosts);
					// show post
					break;
				case '2':
					addPost(emailId);
					break;
				case '3':
					deletePost(emailId);
					break;
				case '4':
					askAgain = false;
					break;
				default:
					System.out.println("Invalid Option");
			}
		}
	}

	private void showPosts(List<JobPosts> jobPosts) {
		if (jobPosts.size() == 0) {
			System.out.println("---------------------------YOU DIDN'T POSTED ANY JOB YET----------------------------");
			return;
		} else {
			System.out.println("---------------------------JOBS POSTED----------------------------");
			System.out.println("  JOB ID  |     ROLE      | CTC (lpa)   |  EXPERIENCE (yrs)");
			for (JobPosts jobs : jobPosts) {
				System.out.printf("%5d%15s%15.2f%15d\n", jobs.jobId, jobs.role, jobs.ctcPackage, jobs.experience);
			}
		}
	}

	private void addPost(String emailId) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Role: ");
		String role = getString();
		System.out.println("Description: ");
		String description = getStringLine();
		System.out.println("Package: ");
		float ctcPackage = scanner.nextFloat();
		System.out.println("Skils: ");
		String skills = getString();
		System.out.println("Experience : ");
		int experience = scanner.nextInt();
		System.out.println("Deadline : YYYY-MM-DD");
		LocalDate deadline = getDate();
		recruiterViewModel.addPost(emailId, role, description, ctcPackage, skills, experience, deadline);
	}

	private void deletePost(String emailId) {
		List<JobPosts> jobPosts = recruiterViewModel.getPosts(emailId);
		showPosts(jobPosts);

		System.out.println("----------------------------------------------------------------");
		System.out.println("Job ID: ");
		int job_id = scanner.nextInt();
		for (JobPosts post : jobPosts) {
			if (job_id == post.jobId) {
				recruiterViewModel.deletePost(job_id);
				postDeletedSuccessfully();
			}
		}

	}

	private void postDeletedSuccessfully() {
		System.out.println("--------------------------------------------------------------------");
		System.out.println("Post deleted successfully");
	}

	public void addedSuccessfully() {
		System.out.println("------------------------------------------------------------------");
		System.out.println("Post added successfully!.");
	}

	private String getString() {
		return Validator.getString();
	}

	private LocalDate getDate() {
		return Validator.getDate();
	}

	private String getStringLine() {
		return Validator.getStringLine();
	}

}