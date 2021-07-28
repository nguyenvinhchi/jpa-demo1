INSERT INTO person
  (ID, NAME, LOCATION, BIRTH_DATE)
  VALUES(1001, 'Chi', 'Hanoi', sysdate());

INSERT INTO person
  (ID, NAME, LOCATION, BIRTH_DATE)
  VALUES(1002, 'Test2', 'Hai Phong', sysdate());

INSERT INTO person
  (ID, NAME, LOCATION, BIRTH_DATE)
  VALUES(1003, 'Test3', 'Da Nang', sysdate());

insert into course(id, name, is_deleted) values(1001, 'JPA learning', false);
insert into course(id, name, is_deleted) values(1002, 'React learning', false);
insert into passport(id, number) values(1001, 'A0123456');
insert into passport(id, number) values(1002, 'A0123457');
insert into passport(id, number) values(1003, 'A0123458');
insert into student(id, name, passport_id) values(1001, 'Adam Smith', 1001);
insert into student(id, name, passport_id) values(1002, 'Lena Edge', 1002);
insert into student(id, name, passport_id) values(1003, 'James Ado', 1003);
insert into review(id, rating, description, course_id) values(1001, 'FOUR', 'Great course', 1001);

insert into course_student(course_id, student_id) values(1001, 1001);
insert into course_student(course_id, student_id) values(1001, 1002);
insert into course_student(course_id, student_id) values(1002, 1001);
insert into course_student(course_id, student_id) values(1002, 1002);