
drop table if exists recurring_unavailabilities;
drop table if exists time_off_requests;
drop table if exists scheduled_shifts;
drop table if exists employee_role_junctions;
drop table if exists roles;
drop table if exists employee_shift_type_junctions;
drop table if exists shift_types;
drop table if exists employees;

create table if not exists employees(
	employee_id serial primary key,
	name varchar(50) not null,
	username varchar(50) unique not null,
	password varchar(50) not null,
	start_date bigint -- absolute time
);

create table if not exists shift_types(
	shift_type_id serial primary key,
	name varchar(50) not null,
	start_time bigint not null, -- relative to a given day
	end_time bigint not null
);

-- defines which employees are allowed to work on which shifts
create table if not exists employee_shift_type_junctions(
	employee_shift_type_junction_id serial primary key,
	employee_id int not null references employees on delete cascade,
	shift_type_id int not null references shift_types on delete cascade
);

-- defines user permissions in the api, web app, etc
create table if not exists roles(
	role_id serial primary key,
	role_name varchar(50),
	is_manager boolean not null
);

-- defines which employees have which roles
create table if not exists employee_role_junctions(
	employee_role_junction_id serial primary key,
	employee_id int not null references employees on delete cascade,
	role_id int not null references roles on delete cascade
);

create table if not exists scheduled_shifts(
	scheduled_shift_id serial primary key,
	shift_type_id int not null references shift_types on delete restrict,
	employee_id int not null references employees on delete cascade,
	date bigint not null -- stored as the start of a date, e.g. November 17th 2022
);

create table if not exists time_off_requests(
	time_off_request_id serial primary key,
	employee_id int not null references employees on delete cascade,
	start_time bigint not null, -- absolute time in millis
	end_time bigint not null,
	approved boolean null -- null=approval pending, true = approved, false = denied
);

create table if not exists recurring_unavailabilities(
	recurring_unavailability_id serial primary key,
	employee_id int not null references employees on delete cascade,
	weekday int not null check (weekday in (0,1,2,3,4,5,6)), -- 0=sunday, 1=monday, etc
	start_time bigint not null, -- stored as millis relative to start of day
	end_time bigint not null
);

insert into roles values
	(default, 'Manager', true),
	(default, 'clerk', false);

insert into employees values
	(default, 'Larry Manager', 'larrymanager', 'larrypassword', 0),
	(default, 'Steve Clerk', 'steveclerk', 'stevepassword', 0);

insert into employee_role_junctions values
	(default, 1, 1), -- assign manager role to Larry Manager;
	(default, 2,2);