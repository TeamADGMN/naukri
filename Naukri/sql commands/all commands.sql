CREATE DATABASE naukri;

use naukri;

CREATE TABLE credentials (username VARCHAR(50), password VARCHAR(50));

INSERT INTO
    credentials (username, password)
VALUES
('dharanish@gmail.com', 'dharanish');

CREATE TABLE userinfo (
    emailid VARCHAR(50),
    name VARCHAR(50),
    phone_no CHAR(10),
    highest_qualification VARCHAR(50),
    specialization VARCHAR(50),
    percentage DOUBLE,
    skills VARCHAR(50),
    prefered_salary DOUBLE,
    no_of_years DOUBLE,
    job_role VARCHAR(50),
    company_name VARCHAR(50),
    is_updated BOOLEAN
);

CREATE TABLE recruiter(
    emailid VARCHAR(50),
    name VARCHAR(50),
    phone_no CHAR(10),
    organization_name VARCHAR(50),
    location VARCHAR(100),
    desigination VARCHAR(60)
);

INSERT INTO
    userinfo (emailid, name, phone_no)
VALUES
    ('hello@gmail.com', 'hello', '9159474530');

CREATE TABLE job_info (
    role VARCHAR(225),
    description VARCHAR(225),
    package FLOAT,
    experience INTEGER,
    skill_set VARCHAR(225),
    deadline_date DATE
);

insert into
    job_info (
        role,
        description,
        package,
        experience,
        skill_set,
        deadline_date,
        emailid
    )
values
    (
        "jfsd",
        "java therinja pothum",
        8.5,
        0,
        "sapda theriyanum",
        "2023-06-06",
        "a@gmail.com"
    );
create table seeker_jobs (emailid varchar(50) , jobid int);
INSERT INTO job_info(emailid, role, description, package, experience, skill_set, deadline_date) values
					('dharanish@gmail.com', 'Front-End', 'This is the actual application.', 10.8, 2, 'HTML, CSS, JS', '2023-04-14'),
                    ('dharanish@gmail.com', 'Back-End', 'This is the brain of the application.', 10, 2, 'JAVA/C#/PHP', '2023-05-14');
Insert Into seeker_jobs values('dharanish@gmail.com', 2),
							('dharansih@gmail.com', 3);