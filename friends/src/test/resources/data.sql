insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)
values (1,'martin',10,'A',1997,6,27);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)
values (2,'Yboy',10,'A',1997,5,9);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)
values (3,'maks',10,'AB',1997,7,7);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)
values (4,'sophia',10,'O',1997,2,16);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)
values (5,'benny',10,'A',1997,1,20);
insert into person(`id`,`name`,`age`,`blood_type`,`year_of_birthday`,`month_of_birthday`,`day_of_birthday`)
values (6,'dennis',10,'B',1997,9,20);

insert into block(`id`,`name`) values(1,'dennis');
insert into block(`id`,`name`) values(2,'sophia');

update person set block_id = 1 where id = 6;
update person set block_id = 2 where id = 4;