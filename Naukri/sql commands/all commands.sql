CREATE DATABASE narkuri;
CREATE TABLE narkuri.credentials (username VARCHAR(50) ,password VARCHAR(50));
INSERT INTO narkuri.credentials (username,password) VALUES('dharanish@gmail.com','dharanish');
CREATE TABLE narkuri.userinfo (emailid VARCHAR(50) ,name VARCHAR(50) ,phone_no CHAR(10) ,highest_qualification VARCHAR(50) , specialization VARCHAR(50) , percentage DOUBLE , skills VARCHAR(50) ,prefered_salary DOUBLE,no_of_years DOUBLE , job_role VARCHAR(50) , company_name VARCHAR(50) , is_updated BOOLEAN);
CREATE TABLE narkuri.requiter(emailid VARCHAR(50) ,name VARCHAR(50) , phone_no CHAR(10) , organization_name VARCHAR(50) , location VARCHAR(100) , desigination VARCHAR(60));
INSERT INTO narkuri.userinfo (emailid,name,phone_no) VALUES ('hello@gmail.com','hello','9159474530');
CREATE TABLE narkuri.job_info (role VARCHAR(225) ,description VARCHAR(225) , package FLOAT , experience INTEGER , skill_set VARCHAR(225) , deadline_date DATE);