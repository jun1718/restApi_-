commit;
select * from project; 
select * from composition;

select * from composition c
	inner join project p 
    on c.project_no = p.project_no
    where c.member_no = 1;