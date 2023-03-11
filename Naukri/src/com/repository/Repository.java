package com.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class Repository {
	private static Repository repository = null;
	private Connection con;
	private Statement stmt;

	private Repository() {
		stmt = null;
		con = null;
	}

//===============================creating constructor===============
	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
			repository.getConnection();
		}
		return repository;
	}

//=====================================conntecting to the database==================
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

//===================================check a usercredentials===============================
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

//===========================add a new user=====================================
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

//=================================adding Recruiter information==================================
	public boolean addRecruiter(String userName, String emailId, String phoneNumber, String password) {
		String SELECT_STATEMENT = "SELECT * FROM credentials WHERE username= '" + emailId + "';";
		String UPDATE_STATEMENT = "INSERT INTO credentials (username , password) VALUES ('" + emailId + "','"
				+ password + "');";
		String UPDATE_STATEMENT1 = "INSERT INTO Recruiter (emailid,name,phone_no) VALUES ('" + emailId + "','" + userName
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
//=================================Adding Post information==================================
	public void addPost(String emailID, String role, String description, float ctcPackage, String skills, int experience,
						LocalDate deadline) {
		String ADD_POST_QUERY = "INSERT INTO job_info (emailID, role, description, package, experience, skill_set, deadline)"
		  + "values(?, ?, ?, ?, ?, ?, ?)";
		try {
			Date date = Date.valueOf(deadline);
			PreparedStatement preparedStatement = con.prepareStatement(ADD_POST_QUERY);

			preparedStatement.setString(1, emailID);
			preparedStatement.setString(2, role);
			preparedStatement.setString(3, description);
			preparedStatement.setFloat(4, ctcPackage);
			preparedStatement.setString(5, skills);
			preparedStatement.setInt(6, experience);
			preparedStatement.setDate(7, date);
		} catch (Exception e) {
			
		}

	}
}
