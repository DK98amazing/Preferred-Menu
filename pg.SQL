-- char长度固定，拥有更高的读写效率，尤其在频繁更新的字段，每次更新的长短不一，varchar会造成行迁移，char读取的数据需要额外处理空字符
-- varchar 长度可变，拥有更好的看，空间效率，取出的字符不需要处理多余的空字符

--------------------------------account
create table account(
	account_id char(16),
	user_name varchar not null,
	password varchar not null,
	description varchar null,
	email varchar null,
	phone char(11) not null unique,
	card_id char(18) null unique,
	reall_name varchar(16) null,
	primary key(account_id)
);

-- 使用索引字段时,注意避免模糊查询,模糊查询导致索引失效,进而扫面全表
create index account_index on account(account_id,phone,card_id);

select * from account where accont_id in (...);


---------------------------------

-- 为了减小误差,数据库中存放金额一般存放最低单位,人民币为分,数据类型使用numeric(10)
-- 表示精度为10(最多10位有效数字,最大额度:999999999分),标度为0
create table account_ext(
	account_id char(16),
	location varchar not null,
	breakfast_upper numeric(10) default 20,
	breakfast_lower numeric(10) default 1,
	lunch_upper numeric(10) default 50,
	lunch_lower numeric(10) default 0,
	dinner_upper numeric(10) default 50,
	dinner_lower numeric(10) default 0,
	height smallint not null,
	age smallint not null,
	sex boolean not null,
	weight smallint not null,
	healthy_id char(16) not null,
	primary key(account_id)
)

create index account_ext_index on account_ext(account_id,healthy_id);


-----------------------------------

create table taste(
	account_id char(16) not null,
	sour smallint default 3,
	sweet smallint default 3,
	bitter smallint default 3,
	salty smallint default 3,
	fresh smallint default 3,
	spicy smallint default 3,
	hot smallint default 3,
	hard smallint default 3,
	primary key(account_id)
)

create index taste_index on taste(account_id);


------------------------------------



create table level(
	code smallint ,
	cndisplay varchar not null,
	endisplay varchar not null,
	description varchar,
	primary key(code)
);


------------------------------------

create table healthy_temp(
	healthy_id char(16) ,
	code smallint not null,
	description varchar,
	primary key(healthy_id)
)


--------------------------------------

create table dishes_type(
	type_id char(16),
	type_name varchar not null,
	description varchar,
	primary key(type_id)
)

--------------------------------------


create table store(
	store_id char(16),
	location varchar not null,
	name varchar not null,
	primary key(store_id)
)




--------------------------------------
create table dishes(
	dishes_id char(16),
	name varchar not null,
	price numeric(10) not null,
	type_id char(16) not null,
	ingredient varchar not null,
	tage varchar not null,
	store_id char(16) not null
	primary key(dishes_id)
)







































