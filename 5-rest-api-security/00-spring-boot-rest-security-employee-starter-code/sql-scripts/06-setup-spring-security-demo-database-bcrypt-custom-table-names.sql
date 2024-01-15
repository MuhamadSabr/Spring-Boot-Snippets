For encypted password the length must be at least 68.   {bcrypt} - 8 chars . encodedPassword - 60 chars.

	members					roles
	pk user_id VARCHAR(50)			fk user_id VARCHAR(50)
	   pw VARCHAR(68)			   role VARCHAR(50)		//authority is the same as role.
	   active  NUMBER(1)


CREATE TABLE hr.members (
  user_id varchar2(50) PRIMARY KEY,
  pw char(68) NOT NULL,
  active NUMBER(1) NOT NULL
)

		password is fun123 for all three.
INSERT ALL
INTO hr.members VALUES ('mmd','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1)
INTO hr.members VALUES ('ah','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1)
INTO hr.members VALUES ('susan','{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q',1)
SELECT * FROM dual


roles table:
CREATE TABLE hr.roles(
user_id varchar(50),
role varchar(50) not null,
constraint uniq_role UNIQUE(user_id, role),
constraint fk_role_users FOREIGN KEY(user_id) references hr.members(user_id))


Insert
INSERT ALL
INTO hr.roles VALUES('mmd', 'ROLE_EMPLOYEE')
INTO hr.roles VALUES('mmd', 'ROLE_MANAGER')
INTO hr.roles VALUES('mmd', 'ROLE_ADMIN')
INTO hr.roles VALUES('ah', 'ROLE_EMPLOYEE')
INTO hr.roles VALUES('ah', 'ROLE_MANAGER')
INTO hr.roles VALUES('susan', 'ROLE_EMPLOYEE')
SELECT * FROM DUAL
