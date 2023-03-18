package com.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.jobseek.dto.JobPosts;

public class Repository {
	private static Repository repository = null;
	private Connection con;
	private Statement stmt;
	private PreparedStatement preparedStatement;

	private List<JobPosts> jobPosts = new ArrayList<>();

	private Repository() {
		stmt = null;
		con = null;
	}

	// ===============================creating constructor===============
	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
			repository.getConnection();
		}
		return repository;
	}

	// =====================================conntecting to the
	// database==================
	private void getConnection() {
		try {
			String url = "jdbc:mysql://localhost:3306/naukri";
			String username = "root";
			String password = "ArunEswari3#";
			con = DriverManager.getConnection(url, username, password);
			stmt = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to connect database");
		}
	}

	// ===================================check a
	// usercredentials===============================
	public boolean checkCredentials(String mailId, String password) {
		String SELECT_COMMAND = "SELECT * FROM credentials WHERE username= '" + mailId + "' && password = '"
				+ password + "';";
		try {
			ResultSet set = stmt.executeQuery(SELECT_COMMAND);
			if (set.next())
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("unable to get data due to network error");
		}
		return false;
	}

	// ===========================add a new
	// user=====================================
	public boolean addUser(String userName, String emailId, String phoneNumber, String password) {
		String SELECT_STATEMENT = "SELECT * FROM credentials WHERE username= '" + emailId + "' ;";
		String UPDATE_STATEMENT = "INSERT INTO credentials (username , password) VALUES ('" + emailId + "','"
				+ password + "');";
		String UPDATE_STATEMENT1 = "INSERT INTO userinfo (emailid,name,phone_no) VALUES ('" + emailId
				+ "', '" + userName + "','" + phoneNumber + "');";
		try {
			ResultSet set = stmt.executeQuery(SELECT_STATEMENT);
			if (set.next())
				return false;
			else {
				stmt.executeUpdate(UPDATE_STATEMENT);
				stmt.executeUpdate(UPDATE_STATEMENT1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Failed to add user");
		}

		return true;
	}

	// =================================adding Recruiter
	// information==================================
	public boolean addRecruiter(String userName, String emailId, String phoneNumber, String password) {
		String SELECT_STATEMENT = "SELECT * FROM credentials WHERE username= '" + emailId + "';";
		String UPDATE_STATEMENT = "INSERT INTO credentials (username , password) VALUES ('" + emailId + "','"
				+ password + "');";
		String UPDATE_STATEMENT1 = "INSERT INTO Recruiter (emailid,name,phone_no) VALUES ('" + emailId + "','"
				+ userName
				+ "' ,'" + phoneNumber + "');";
		try {
			ResultSet set = stmt.executeQuery(SELECT_STATEMENT);
			if (set.next())
				return false;
			else {
				stmt.executeUpdate(UPDATE_STATEMENT);
				stmt.executeUpdate(UPDATE_STATEMENT1);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update Recruiter");
		}
		return false;
	}
	// =================================getting posts of corresponding
	// recruiter==================================

	public List<JobPosts> getPosts(String emailId) {
		String SELECT_STATEMENT = "SELECT * FROM job_info WHERE emailId= '" + emailId + "';";
		try {
			ResultSet set = stmt.executeQuery(SELECT_STATEMENT);
			jobPosts.clear();
			while (set.next()) {
				jobPosts.add(new JobPosts(set));
			}
		} catch (Exception e) {
			System.out.println("exception occured in getting job posts");
		}
		return jobPosts;
	}

	// =================================Adding Post
	// information==================================
	public void addPost(String emailID, String role, String description, float ctcPackage, String skills,
			int experience,
			LocalDate deadline) {
		String ADD_POST_QUERY = "INSERT INTO job_info(emailid, role, description, package, experience, skill_set, deadline_date)"
				+ " values(?, ?, ?, ?, ?, ?, ?)";
		try {
			Date date = Date.valueOf(deadline);
			preparedStatement = con.prepareStatement(ADD_POST_QUERY);

			preparedStatement.setString(1, emailID);
			preparedStatement.setString(2, role);
			preparedStatement.setString(3, description);
			preparedStatement.setFloat(4, ctcPackage);
			preparedStatement.setInt(5, experience);
			preparedStatement.setString(6, skills);
			preparedStatement.setDate(7, date);

			if (preparedStatement.executeUpdate() > 0) {
				addToSeekerJobs(emailID);
			}
			;
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occurred while adding jobs into JOB_INFO");
		}
	}

	private void addToSeekerJobs(String emailID) {
		String GET_JOB_IDs = "SELECT MAX(job_id) FROM job_info WHERE emailid = '" + emailID + "'";
		String ADD_TO_SEEKER_JOBS = "INSERT INTO SEEKER_JOBS VALUES (?, ?)";
		int job_id = 0;
		try {
			stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(GET_JOB_IDs);
			while (rs.next()) {
				job_id = rs.getInt(1);
			}
			preparedStatement = con.prepareStatement(ADD_TO_SEEKER_JOBS);

			preparedStatement.setString(1, emailID);
			preparedStatement.setInt(2, job_id);

			preparedStatement.executeUpdate();
			preparedStatement.close();
			rs.close();

		} catch (Exception e) {
			System.out.println("Error occurred while inserting into SEEKER_JOBS");
		}

	}

	// =================================Delete Post
	// information==================================
	public void deletePost(int job_id) {
		String DELETE_POST_QUERY1 = "DELETE FROM job_info WHERE job_id = " + job_id;
		String DELETE_POST_QUERY2 = "DELETE FROM seeker_jobs WHERE job_id = " + job_id;

		try {
			preparedStatement = con.prepareStatement(DELETE_POST_QUERY1);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			preparedStatement = con.prepareStatement(DELETE_POST_QUERY2);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
