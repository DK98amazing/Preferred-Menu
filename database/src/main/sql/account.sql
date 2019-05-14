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

INSERT INTO public.sysuser(
	user_id, user_name, password, email, phone, sex, status, last_login_time, create_time, update_time)
	VALUES ('lgy', 'lgy', 'Admin@1234', '1254770191@qq.com', '15957194307', 0, 0, now(), now(), now());

CREATE TABLE public.sysrole
(
    role_id character varying NOT NULL,
    role character varying NOT NULL,
    description character varying,
    status integer,
    selected integer,
    PRIMARY KEY (role_id)
)

INSERT INTO public.sysrole(
	role_id, role, description, status, selected)
	VALUES ('admin', 'admin', 'admin', 0, 0);

CREATE TABLE public.sys_user_role
(
    id integer NOT NULL,
    user_id character varying COLLATE pg_catalog."default" NOT NULL,
    role_id character varying COLLATE pg_catalog."default",
    PRIMARY KEY (id)
)

INSERT INTO public.sys_user_role(
	id, user_id, role_id)
	VALUES ('sys_user_role_1', 'lgy', 'admin');

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

INSERT INTO public.sysresource(
	resource_id, name, description, url, permission, type, priority, status)
	VALUES ('all', 'all', 'all', 'www.baidu.com', 'all', 0, 0, 0);

CREATE TABLE public.sysrole_resource
(
    id serial NOT NULL ,
    role_id character varying COLLATE pg_catalog."default" NOT NULL,
    resource_id character varying COLLATE pg_catalog."default",
    PRIMARY KEY (id)
)

INSERT INTO public.sys_role_resource(
	id, role_id, resource_id)
	VALUES ('sys_role_resource_1', 'admin', 'all');
