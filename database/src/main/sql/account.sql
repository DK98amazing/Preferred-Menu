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

CREATE TABLE public.sysuser
(
    user_id character varying NOT NULL,
    user_name character varying NOT NULL,
    password character varying NOT NULL,
    email character varying,
    phone character(11) NOT NULL,
    sex integer,
    status integer,
    last_login_time date,
    create_time date,
    update_time date,
    PRIMARY KEY (user_id)
)

CREATE TABLE public.sysrole
(
    role_id character varying NOT NULL,
    role character varying NOT NULL,
    description character varying,
    status integer,
    selected integer,
    PRIMARY KEY (role_id)
)

CREATE TABLE public.sys_user_role
(
    id integer NOT NULL,
    user_id character varying COLLATE pg_catalog."default" NOT NULL,
    role_id character varying COLLATE pg_catalog."default",
    PRIMARY KEY (id)
)

CREATE TABLE public.sysresource
(
    resource_id character varying COLLATE pg_catalog."default" NOT NULL,
    name character varying COLLATE pg_catalog."default" NOT NULL,
    description character varying COLLATE pg_catalog."default",
    url character varying COLLATE pg_catalog."default",
    permission character varying COLLATE pg_catalog."default",
	type integer,
	priority integer,
    status integer,
	primary key (resource_id)
)

CREATE TABLE public.sysrole_resource
(
    id serial NOT NULL ,
    role_id character varying COLLATE pg_catalog."default" NOT NULL,
    resource_id character varying COLLATE pg_catalog."default",
    PRIMARY KEY (id)
)
