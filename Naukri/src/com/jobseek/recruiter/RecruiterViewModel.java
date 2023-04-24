package com.jobseek.recruiter;

import java.time.LocalDate;
import java.util.List;

import com.jobseek.dto.JobPosts;
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

	public List<JobPosts> getPosts(String emailId) {
		return Repository.getInstance().getPosts(emailId);
	}

	public void deletePost(int job_id) {
		Repository.getInstance().deletePost(job_id);
	}

}