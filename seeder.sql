create database if not exists wBlog;
use wBlog;

drop table if exists postComments;
drop table if exists comments;
drop table if exists posts;
drop table if exists users;

create table if not exists users(
    id int unsigned auto_increment primary key,
    username varchar(255) not null,
    password varchar(255) not null,
    email varchar(255) not null,
    is_admin tinyint not null,
    profile_img text
);

create table if not exists posts(
    id int unsigned auto_increment primary key,
    title varchar(255) not null,
    content text not null,
    date datetime,
    likes int unsigned
);

create table if not exists comments(
    id int unsigned auto_increment primary key,
    comment text not null,
    user_id int unsigned not null,
    is_approved tinyint not null,
    foreign key (user_id) references users(id)
);

create table if not exists postComments(
    post_id int unsigned not null,
    comment_id int unsigned not null,
    foreign key (post_id) references posts(id),
    foreign key (comment_id) references comments(id)
);

insert into users(id, username, password, email, is_admin) values (1, 'mandy', 'password', 'email@email.com', 1);

insert into users(id, username, password, email, is_admin) values (2, 'user', 'password1', 'email@email.com', 0);
insert into users(id, username, password, email, is_admin) values (3, 'other', 'password2', 'email@email.com', 0);

insert into posts(id, title, content, date, likes) VALUES (1, 'first post', 'about life and animals', '2021-01-02', 5);
insert into posts(id, title, content, date, likes) VALUES (2, 'second post', 'about water and food', '2020-03-02', 1);
insert into posts(id, title, content, date, likes) VALUES (3, 'third post', 'about items and possessions', '2021-11-21', 15);

insert into comments(id, comment, user_id, is_approved) VALUES (1, 'i like this one waiting', 2, 0);
insert into comments(id, comment, user_id, is_approved) VALUES (2, 'i like this one approved', 3, 1);
insert into comments(id, comment, user_id, is_approved) VALUES (3, 'i do not really like this one approved', 2, 1);
insert into comments(id, comment, user_id, is_approved) VALUES (4, 'i do not really like like this one waiting', 3, 0);

insert into postComments(post_id, comment_id) VALUES (1, 1);
insert into postComments(post_id, comment_id) VALUES (2, 2);
insert into postComments(post_id, comment_id) VALUES (1, 3);
insert into postComments(post_id, comment_id) VALUES (3, 4);

select * from posts;
select * from users;
select * from comments;
select * from comments where is_approved = 1;