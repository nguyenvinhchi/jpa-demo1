create table person (
  id varchar(255) not null,
  name varchar(255) not null,
  location varchar(255),
  birth_date timestamp,
  primary key(id)
);

INSERT INTO PERSON
  (ID, NAME, LOCATION, BIRTH_DATE)
  VALUES('1001', 'Chi', 'Hanoi', sysdate());

INSERT INTO PERSON
  (ID, NAME, LOCATION, BIRTH_DATE)
  VALUES('1002', 'Test2', 'Hai Phong', sysdate());

INSERT INTO PERSON
  (ID, NAME, LOCATION, BIRTH_DATE)
  VALUES('1003', 'Test3', 'Da Nang', sysdate());