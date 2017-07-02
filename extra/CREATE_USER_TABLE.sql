CREATE TABLE USER (
	ID INTEGER PRIMARY KEY,
	USERNAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(100) NOT NULL,
	NAME TEXT NOT NULL,
	AGE INT NOT NULL,
	SALARY REAL,
	USER_TYPE VARCHAR(10),
	TEAM INT NULL
);