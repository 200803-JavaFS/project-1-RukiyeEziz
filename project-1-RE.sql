--project1 SQL script

CREATE DATABASE project1;

-------------------------------
--------- DANGER ZONE ---------
--DROP TABLE IF EXISTS ers_reimbursement_status; 
--DROP TABLE IF EXISTS ers_reimbursement_type CASCADE;
--DROP TABLE IF EXISTS ers_user_roles CASCADE;
--DROP TABLE IF EXISTS ers_reimbursement CASCADE;
--DROP TABLE IF EXISTS ers_users CASCADE;
--------------------------------
--------------------------------

CREATE TABLE ers_reimbursement_status (
	reimb_status_id SERIAL PRIMARY KEY,
	reimb_status VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement_type (
	reimb_type_id SERIAL PRIMARY KEY,
	reimb_type VARCHAR(10) NOT NULL
);

CREATE TABLE ers_user_roles (
	ers_user_role_id SERIAL PRIMARY KEY,
	ers_user_role VARCHAR(10) NOT NULL
);

CREATE TABLE ers_reimbursement (
	reimb_id SERIAL PRIMARY KEY,
	reimb_amount NUMERIC(10,2) NOT NULL,   	--INTEGER
	reimb_submitted TIMESTAMP NOT NULL,
	reimb_resolved TIMESTAMP,
	reimb_description VARCHAR(250),
	reimb_receipt BYTEA,					-- blob
	ers_users_id_fk_auth INTEGER NOT NULL REFERENCES ers_users(ers_users_id),
	ers_users_id_fk_reslvr INTEGER REFERENCES ers_users(ers_users_id),
	reimb_status_id_fk INTEGER NOT NULL REFERENCES ers_reimbursement_status(reimb_status_id),
	reimb_type_id_fk INTEGER NOT NULL REFERENCES ers_reimbursement_type(reimb_type_id)
);

CREATE TABLE ers_users (
	ers_users_id SERIAL PRIMARY KEY,
	ers_username VARCHAR(50) NOT NULL UNIQUE,
	ers_password VARCHAR(50) NOT NULL,
	user_first_name VARCHAR(100) NOT NULL,
	user_last_name VARCHAR(100) NOT NULL,
	user_email VARCHAR(150) NOT NULL UNIQUE,
	user_role_id_fk INTEGER NOT NULL REFERENCES ers_user_roles(ers_user_role_id)
);

----------------------------------------------

INSERT INTO ers_reimbursement_status(reimb_status)
	VALUES ('Approved'),
			('Denied'),
			('Pending');
		
		  
UPDATE ers_reimbursement_status 
	SET reimb_status = 'Denied'
	WHERE reimb_status_id = 2; 


INSERT INTO ers_reimbursement_type(reimb_type)
	VALUES ('Lodging'),
			('Travel'),
			('Food'),
			('Other');

INSERT INTO ers_user_roles(ers_user_role)
	VALUES ('Manager'),
			('Employee');
			
----------------- IMPORTANT --------------------------------------------------------*************** SET TIME TRIGGER
CREATE OR REPLACE FUNCTION trigger_set_time() RETURNS TRIGGER 
AS $$
	BEGIN
		NEW.reimb_resolved = NOW();
		RETURN NEW; 
	END;
$$ LANGUAGE plpgsql; 

--------------------

CREATE TRIGGER set_time BEFORE UPDATE ON ers_reimbursement FOR EACH ROW
EXECUTE PROCEDURE trigger_set_time();
----------------------------------------------------------------------------------*****************


INSERT INTO ers_reimbursement (reimb_amount , reimb_submitted , reimb_resolved , reimb_description , reimb_receipt , ers_users_id_fk_auth , ers_users_id_fk_reslvr , reimb_status_id_fk , reimb_type_id_fk )
	VALUES (550.50 , clock_timestamp(), NULL, 'certification cost', NULL, 2, NULL, 3, 4);


--renaming column data type
ALTER TABLE ers_reimbursement 
ALTER COLUMN reimb_amount TYPE NUMERIC(10,2);

--update a cell value
UPDATE ers_reimbursement 
	SET reimb_amount = 555.11
	WHERE reimb_id = 1;

----------
SELECT clock_timestamp(); 	

---------------------------------------
-- find all users' role by their user id 
SELECT ers_users.ers_users_id, ers_users.user_role_id_fk, ers_user_roles.ers_user_role 
FROM ers_users , ers_user_roles
WHERE ers_users.user_role_id_fk = ers_user_roles.ers_user_role_id;

-- find user role and user information for specific user by user id
-- UserRolesDAO.java
SELECT * FROM ers_users 
JOIN ers_user_roles
ON ers_users.user_role_id_fk = ers_user_roles.ers_user_role_id
WHERE ers_users.ers_users_id = 1;

-- find reimb status and reimb information for specific user by their user id, (employee only)
SELECT * FROM ers_reimbursement 
JOIN ers_reimbursement_status 
ON ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id 
WHERE ers_reimbursement.ers_users_id_fk_auth = 2;

SELECT * FROM ers_reimbursement WHERE ers_users_id_fk_auth = 2;

-- find reimb status and reimb information 
SELECT * FROM ers_reimbursement 
JOIN ers_reimbursement_status 
ON ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id;

-- find reimb status and reimb information with specific status (public List<Reimbursement> findReimbursementByStatus(String status))
SELECT * FROM ers_reimbursement 
JOIN ers_reimbursement_status 
ON ers_reimbursement.reimb_status_id_fk = ers_reimbursement_status.reimb_status_id
WHERE ers_reimbursement_status.reimb_status = 'Pending';

