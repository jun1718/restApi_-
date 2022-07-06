
drop table if exists project;

create table project (
	project_no bigint auto_increment primary key,
    admin_member_no bigint not null,
    name varchar(30) not null unique,
    project_status varchar(2) not null
);

insert into project values (null, 1, '오늘의 강의1', '활성');
insert into project values (null, 1, '오늘의 강의2', '활성');
insert into project values (null, 1, '오늘의 강의3', '활성');
insert into project values (null, 1, '오늘의 강의4', '활성');
insert into project values (null, 1, '오늘의 강의5', '활성');
insert into project values (null, 1, '오늘의 강의6', '활성');
insert into project values (null, 1, '오늘의 강의7', '활성');
insert into project values (null, 2, '오늘의 강의11', '활성');
insert into project values (null, 2, '오늘의 강의22', '활성');
insert into project values (null, 2, '오늘의 강의33', '활성');
commit;

-- -------------------------------------

drop table if exists composition;

create table composition (
	composition_no bigint auto_increment primary key,
    project_no bigint not null,
    member_no bigint not null,
    unique (project_no, member_no)
);

insert into composition values (null, 1, 2);
insert into composition values (null, 2, 2);
insert into composition values (null, 3, 2);
insert into composition values (null, 4, 2);
insert into composition values (null, 5, 2);
insert into composition values (null, 6, 2);
insert into composition values (null, 8, 1);
commit;

select * from composition;

