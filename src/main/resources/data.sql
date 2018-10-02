insert into technology (category, technologytype) values('java', 'spring');
insert into technology (category, technologytype) values('java', 'dwr');
insert into technology (category, technologytype) values('java','hibernate');

insert into info (description, modifieddate, subject, submitdate, technology) 
values ('test' , current_timestamp, 'test', current_timestamp, 1); 
insert into info (description, modifieddate, subject, submitdate, technology) 
values ('test' , current_timestamp, 'test', current_timestamp, 2);

insert into problem (modifieddate, problem, reasonforproblem, solution, submitdate, technology) 
values (current_timestamp, 'test' , 'test' , 'test', current_timestamp, 2);
insert into problem (modifieddate, problem, reasonforproblem, solution, submitdate, technology) 
values (current_timestamp, 'test' , 'test' , 'test', current_timestamp, 1);