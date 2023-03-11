package com.jobseek.recruiter;

import java.time.LocalDate;

import com.jobseek.login.LoginView;
import com.repository.Repository;

public class RecruiterViewModel {
	
	private RecruiterView recruiterView;

	public RecruiterViewModel(RecruiterView recruiterView) {
		this.recruiterView = recruiterView;
	}

    public void addPost(String emailID, String role, String description, float ctcPackage, String skills, int experience,
            LocalDate deadline) {
		Repository.getInstance().addPost(emailID, role, description, ctcPackage, skills, experience, deadline);
		recruiterView.addedSuccessfully();
    }
	
	

}