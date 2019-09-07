insert into article(id,email,title,content,date,published) values(1,'john@simpledev.com','Introduction to Spring', 'This is an example','2018-09-12',1);
insert into article(id,email,title,content,date,published) values(2,'doe@simpledev.com','Spring Cloud', 'This is an example','2018-09-11',1);

--insert into article(id,email,title,content,date,published) values(2,'doe@simpledev.com','Spring Cloud', 'This is an example',STR_TO_DATE('11-09-2018', '%d-%m-%Y'),1);
--TODO:
insert into comment(email,message,date,article_id) values('ivy@simpledev.com','This is amazing!','2018-03-16',1);

--insert into comment(id,email,message,date,published) values(2,'elli@simpledev.com','Love this article!','9/12/2018',2);