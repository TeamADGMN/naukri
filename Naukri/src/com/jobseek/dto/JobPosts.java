package com.jobseek.dto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobPosts {
    public int jobId;
    public String emailid;
    public String role;
    public String description;
    public float ctcPackage;
    public int experience;
    public String skillSet;
    public Date deadlineDate;
	
	public JobPosts(ResultSet set) throws SQLException {
		this.jobId = set.getInt("job_id");
		this.emailid = set.getString("emailid");
		this.role = set.getString("role");
		this.description = set.getString("description");
		this.ctcPackage = set.getFloat("package");
		this.experience = set.getInt("experience");
		this.skillSet = set.getString("skill_set");
		this.deadlineDate = set.getDate("deadline_date");
	}

}