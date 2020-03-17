drop TABLE if EXISTS sysuser;
CREATE TABLE sysuser
(
    user_id varchar(1024) NOT NULL PRIMARY KEY,
    user_name varchar(1024) NOT NULL,
    password varchar(1024) NOT NULL,
    email varchar(1024),
    phone varchar(11) NOT NULL,
    sex integer,
    status integer,
    last_login_time date,
    create_time date,
    update_time date
);

INSERT INTO sysuser(
	user_id, user_name, password, email, phone, sex, status, last_login_time, create_time, update_time)
	VALUES ('lgy', 'lgy', 'Admin@1234', '1254770191@qq.com', '15957194307', 0, 0, now(), now(), now());

drop TABLE if EXISTS sysrole;
CREATE TABLE sysrole
(
    role_id varchar(1024) NOT NULL PRIMARY KEY,
    role varchar(1024) NOT NULL,
    description varchar(1024),
    status integer,
    selected integer
);

INSERT INTO sysrole(
	role_id, role, description, status, selected)
	VALUES ('admin', 'admin', 'admin', 0, 0);

drop TABLE if EXISTS sys_user_role;
CREATE TABLE sys_user_role
(
    id varchar(1024) NOT NULL PRIMARY KEY,
    user_id varchar(1024) NOT NULL,
    role_id varchar(1024)
);

INSERT INTO sys_user_role(
	id, user_id, role_id)
	VALUES ('lgy', 'lgy', 'admin');

drop TABLE if EXISTS sysresource;
CREATE TABLE sysresource
(
    resource_id varchar(1024) NOT NULL primary key,
    name varchar(1024) NOT NULL,
    description varchar(1024),
    url varchar(1024),
    permission varchar(1024),
	type integer,
	priority integer,
    status integer
);

INSERT INTO sysresource(
	resource_id, name, description, url, permission, type, priority, status)
	VALUES ('all', 'all', 'all', 'www.baidu.com', 'all', 0, 0, 0);

drop TABLE if EXISTS sys_role_resource;
CREATE TABLE sys_role_resource
(
    id varchar(1024) NOT NULL PRIMARY KEY,
    role_id varchar(1024) NOT NULL,
    resource_id varchar(1024)
);

INSERT INTO sys_role_resource(
	id, role_id, resource_id)
	VALUES ('admin', 'admin', 'all');

drop table user;
create table user (
  id int not null primary key auto_increment,
	name VARCHAR(50),
	age int
);

insert into user VALUES(1, '111', 20);
insert into user VALUES(2, '333', 25);
insert into user VALUES(3, '555', 30);

select * from user;

drop table customer;
create table customer(
    id int primary key not null auto_increment,
	first_name varchar(100),
    last_name varchar(100)
)
