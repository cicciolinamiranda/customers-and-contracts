CREATE DATABASE IF NOT EXISTS javelin;

Use javelin;

CREATE TABLE CONTRACT (
	id						INTEGER			NOT NULL	UNIQUE 	AUTO_INCREMENT,
	contract_number			VARCHAR(30)		NOT NULL 	UNIQUE	,
	contract_name			VARCHAR(30)		NOT NULL		  	,
	contract_start_date		DATE			NOT NULL			,
	contract_end_date		DATE			NOT NULL			,
	contract_title			VARCHAR(360)	NOT NULL			,
	contract_review_date	DATE								,
	contract_signed_date	DATE								,
	contract_wef_date		DATE								
);

INSERT INTO CONTRACT (
	contract_number,
	contract_name,
	contract_start_date,
	contract_end_date,
	contract_title,
	contract_review_date,
	contract_signed_date,
	contract_wef_date

) VALUES (
	"6764324",
	"Contract N1",
	CURDATE(),
	CURDATE(),
	"Contract T1",
	CURDATE(),
	CURDATE(),
	CURDATE()
);

INSERT INTO CONTRACT (
	contract_number,
	contract_name,
	contract_start_date,
	contract_end_date,
	contract_title,
	contract_review_date,
	contract_signed_date,
	contract_wef_date

) VALUES (
	"0090864",
	"Contract N2",
	CURDATE(),
	CURDATE(),
	"Contract T2",
	CURDATE(),
	CURDATE(),
	CURDATE()
);

INSERT INTO CONTRACT (
	contract_number,
	contract_name,
	contract_start_date,
	contract_end_date,
	contract_title,
	contract_review_date,
	contract_signed_date,
	contract_wef_date

) VALUES (
	"4512321",
	"Contract N3",
	CURDATE(),
	CURDATE(),
	"Contract T3",
	CURDATE(),
	CURDATE(),
	CURDATE()
);

INSERT INTO CONTRACT (
	contract_number,
	contract_name,
	contract_start_date,
	contract_end_date,
	contract_title,
	contract_review_date,
	contract_signed_date,
	contract_wef_date

) VALUES (
	"2139000",
	"Contract N4",
	CURDATE(),
	CURDATE(),
	"Contract T4",
	CURDATE(),
	CURDATE(),
	CURDATE()
);

INSERT INTO CONTRACT (
	contract_number,
	contract_name,
	contract_start_date,
	contract_end_date,
	contract_title,
	contract_review_date,
	contract_signed_date,
	contract_wef_date

) VALUES (
	"8892312",
	"Contract N5",
	CURDATE(),
	CURDATE(),
	"Contract T5",
	CURDATE(),
	CURDATE(),
	CURDATE()
);