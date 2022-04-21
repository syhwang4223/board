-- -- 회원
insert into member (email_address, join_date_time, login_id, nickname, password)
    values ('test@email.com', '2020-02-13', 'test', 'test', 'test');
insert into post (title, content, likes, views, write_date_time, writer_id)
    values ('게시글 제목', '게시글내용', 0, 0, '2022-04-20', 2);
-- -- 게시글
insert into post (title, content, likes, views, write_date_time, writer_id)
    select title, content, likes, views, write_date_time, writer_id from post;
insert into post (title, content, likes, views, write_date_time, writer_id)
    select title, content, likes, views, write_date_time, writer_id from post;
insert into post (title, content, likes, views, write_date_time, writer_id)
    select title, content, likes, views, write_date_time, writer_id from post;
insert into post (title, content, likes, views, write_date_time, writer_id)
    select title, content, likes, views, write_date_time, writer_id from post;
insert into post (title, content, likes, views, write_date_time, writer_id)
    select title, content, likes, views, write_date_time, writer_id from post;
insert into post (title, content, likes, views, write_date_time, writer_id)
    select title, content, likes, views, write_date_time, writer_id from post;
insert into post (title, content, likes, views, write_date_time, writer_id)
    select title, content, likes, views, write_date_time, writer_id from post;
