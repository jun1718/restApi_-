drop table if exists member;

create table member (
	member_no bigint auto_increment primary key,
	id varchar(20) not null unique,
    pw varchar(100) not null,
    email varchar(100) not null unique,
    member_status varchar(50) not null,
    authority varchar(50) not null
);

-- insert into member values (1, '안녕하1', '1234', 'hi@nhn1.com', '가입', 'ROLE_USER');
-- insert into member values (null, '안녕하', '1234', 'hi@nhn.com', '가입', 'ROLE_USER');
-- insert into member values (null, 'hi1', '1234', 'hi1@nhn.com', '탈퇴', 'ROLE_USER');

desc member;
select * from member;
commit;


