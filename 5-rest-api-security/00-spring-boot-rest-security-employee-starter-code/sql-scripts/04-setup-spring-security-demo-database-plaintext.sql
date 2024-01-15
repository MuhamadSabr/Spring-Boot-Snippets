	users					authorities
	pk username VARCHAR(50)			fk username VARCHAR(50)
	   password VARCHAR(50)			   authority VARCHAR(50)		//authority is the same as role.
	   enabled  TINYINT(1)

CREATE TABLE users (
  username varchar(50) PRIMARY KEY,
  password varchar(50) NOT NULL,
  enabled NUMBER(1) NOT NULL,
);


CREATE the table and for users insert:
INSERT INTO hr.users values('susan', '{noop}test123', 1)
INSERT INTO hr.users values('mmd', '{noop}test123', 1)
INSERT INTO hr.users values('ah', '{noop}test123', 1)

authorities table:
CREATE TABLE hr.authorities(
username varchar(50),
authority varchar(50) not null
constraint uniq_con UNIQUE(username, authority)
constraint fk_users FOREIGN KEY(username) references users(username))

Insert
INSERT ALL
INTO hr.authorities VALUES('mmd', 'ROLE_EMPLOYEE')
INTO hr.authorities VALUES('mmd', 'ROLE_MANAGER')
INTO hr.authorities VALUES('mmd', 'ROLE_ADMIN')
INTO hr.authorities VALUES('ah', 'ROLE_EMPLOYEE')
INTO hr.authorities VALUES('ah', 'ROLE_MANAGER')
INTO hr.authorities VALUES('susan', 'ROLE_EMPLOYEE')
SELECT * FROM DUAL;
