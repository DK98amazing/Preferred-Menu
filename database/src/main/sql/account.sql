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

INSERT INTO public.account(
	account_id, user_name, password, description, email, phone, card_id, real_name, role_id)
	VALUES ('lgy', 'lgy', 'Admin@1234', 'liguoyao', '1254770191@qq.com', '15957194307', 'lgy', 'liguoyao', 'lgy');

CREATE TABLE account_role (
  id char(16) NOT NULL,
  role_name varchar(64) DEFAULT '',
  PRIMARY KEY (id)
);

INSERT INTO public.account_role(
	id, role_name)
	VALUES ('admin', 'admin');

CREATE TABLE account_authority (
  id char(16) NOT NULL,
  authority_name varchar(64) DEFAULT '',
  icon varchar(255) DEFAULT '',
  uri varchar(255) DEFAULT '',
  permission varchar(1000) DEFAULT '',
  PRIMARY KEY (id)
);

INSERT INTO public.account_authority(
	id, authority_name, icon, uri, permission)
	VALUES ('all', 'all', 'all', 'all', 'allpermission');

CREATE TABLE role_authority (
  role_id char(16) NOT NULL DEFAULT '',
  authority_id char(16) NOT NULL DEFAULT '',
  PRIMARY KEY (role_id)
);

INSERT INTO public.role_authority(
	role_id, authority_id)
	VALUES ('admin', 'all');
