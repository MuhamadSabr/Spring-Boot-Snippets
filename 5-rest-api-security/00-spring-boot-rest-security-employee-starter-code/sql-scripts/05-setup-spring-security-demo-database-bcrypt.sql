For encypted password the length must be at least 68.   {bcrypt} - 8 chars . encodedPassword - 60 chars.

	users					authorities
	pk username VARCHAR(50)			fk username VARCHAR(50)
	   password VARCHAR(68)			   authority VARCHAR(50)		//authority is the same as role.
	   enabled  TINYINT(1)

CREATE TABLE users (
  username varchar(50) PRIMARY KEY,
  password varchar(68) NOT NULL,
  enabled NUMBER(1) NOT NULL,
);

								test123 is password for all 3.
CREATE the table and for users insert the encrypted password:
INSERT INTO hr.users values('susan', '{bcrypt}$2a$10$i6A.s.XN4Ne2vpyASRAtb.C05si7wPsuW.zHkxUriCAV3XMoAlAae', 1)
INSERT INTO hr.users values('mmd', '{bcrypt}$2a$10$LcFp9qbZFoW5T5gesDFe2uDc7Q1cm21LeMtawC1Ax.dfg.ny9Eynm', 1)
INSERT INTO hr.users values('ah', '{bcrypt}$2a$10$Xe7ohnpnnnL92g0p3fy8neugKyN5IfeINJ3Yaou.8B8MQmhAdlFbi', 1)

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
