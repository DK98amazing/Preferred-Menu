create table account(
	account_id char(16),
	user_name varchar not null,
	password varchar not null,
	description varchar null,
	email varchar null,
	phone char(11) not null unique,
	card_id char(18) null unique,
	real_name varchar(16) null,
	role_id char(16),
	primary key(account_id)
);
create index account_index on account(account_id,phone,card_id);

CREATE TABLE account_role (
  id char(16) NOT NULL,
  role_name varchar(64) DEFAULT '',
  PRIMARY KEY (id)
);

CREATE TABLE account_authority (
  id char(16) NOT NULL,
  authority_name varchar(64) DEFAULT '',
  icon varchar(255) DEFAULT '',
  uri varchar(255) DEFAULT '',
  permission varchar(1000) DEFAULT '',
  PRIMARY KEY (id)
);

CREATE TABLE role_authority (
  role_id char(16) NOT NULL DEFAULT '',
  authority_id char(16) NOT NULL DEFAULT '',
  PRIMARY KEY (role_id)
);