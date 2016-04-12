CREATE DATABASE javelin;

USE javelin;

CREATE TABLE CUSTOMER (
	id 						INTEGER 		NOT NULL	UNIQUE	AUTO_INCREMENT,
	customer_num			VARCHAR(30)		NOT NULL	UNIQUE	,
	name 					VARCHAR(360) 	NOT NULL			,
	address1				VARCHAR(240)	NOT NULL			,
	address2				VARCHAR(240)						,
	address3				VARCHAR(240)						,
	address4				VARCHAR(240)						,
	city					VARCHAR(60)							,
	zip_code				VARCHAR(60)							,
	state					VARCHAR(60)							,
	country					VARCHAR(60)							,
	duns					VARCHAR(30)							,
	payment_method			VARCHAR(30)
);

-- First Record
INSERT INTO CUSTOMER (
			customer_num,
			name,
			address1,
			address2,
			address3,
			address4,
			city,
			zip_code,
			state,
			country,
			duns,
			payment_method
) VALUES (
			"709",
			"Maria Matthews",
			"1 Helena Trail",
			"",
			"",
			"",
			"Seattle",
			"98109", 
			"Washington",
			"United States",
			"143999023",
			"Credit"
);

-- Second Record
INSERT INTO CUSTOMER (
			customer_num,
			name,
			address1,
			address2,
			address3,
			address4,
			city,
			zip_code,
			state,
			country,
			duns,
			payment_method
) VALUES (
			"996",
			"Justin Mitchell",
			"6 Eastwood Junction",
			"",
			"",
			"",
			"San Antonio",
			"78215",
			"Texas",
			"United Staes",
			"50991222",
			"Electronic"
);

-- Third Record
INSERT INTO CUSTOMER (
			customer_num,
			name,
			address1,
			address2,
			address3,
			address4,
			city,
			zip_code,
			state,
			country,
			duns,
			payment_method
) VALUES (
			"345",
			"Craig Peterson",
			"4 Marquette Circle",
			"",
			"",
			"",
			"New Haven",
			"06538",
			"Connecticut",
			"United States",
			"450991224",
			"Electronic"

);

-- Fourth Record
INSERT INTO CUSTOMER (
			customer_num,
			name,
			address1,
			address2,
			address3,
			address4,
			city,
			zip_code,
			state,
			country,
			duns,
			payment_method
) VALUES (
			"887",
			"Jerry Brown",
			"1 Arizona Center",
			"",
			"",
			"",
			"Vienna",
			"22184",
			"Virginia",
			"United States",
			"450991200",
			"Electronic"

);

-- Fifth Record
INSERT INTO CUSTOMER (
			customer_num,
			name,
			address1,
			address2,
			address3,
			address4,
			city,
			zip_code,
			state,
			country,
			duns,
			payment_method
) VALUES (
			"675",
			"Helen Robertson",
			"0 Eastwood Street",
			"",
			"",
			"",
			"San Antonio",
			"78260",
			"Texas",
			"United States",
			"999912645",
			"Electronic"
);