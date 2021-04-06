create database if not exists wBlog;
use wBlog;

# drop table if exists post_categories;
# drop table if exists post_questions;
# drop table if exists questions;
# drop table if exists category;
# drop table if exists comments;
# drop table if exists posts;
# drop table if exists users;

# create table if not exists users(
#     id int unsigned auto_increment primary key,
#     username varchar(255) not null,
#     password varchar(255) not null,
#     email varchar(255) not null,
#     is_admin tinyint not null,
#     profile_img text
# );
#
# create table if not exists posts(
#     id int unsigned auto_increment primary key,
#     title varchar(255) not null,
#     content text not null,
#     date datetime,
#     likes int unsigned
# );
#
# create table if not exists comments(
#     id int unsigned auto_increment primary key,
#     comment text not null,
#     user_id int unsigned not null,
#     is_approved tinyint not null,
#     foreign key (user_id) references users(id)
# );
#
# create table if not exists postComments(
#     post_id int unsigned not null,
#     comment_id int unsigned not null,
#     foreign key (post_id) references posts(id),
#     foreign key (comment_id) references comments(id)
# );
#
# create table if not exists categories(
#     id int unsigned auto_increment primary key,
#     name text not null
# );
#
# create table if not exists postCategories(
#     post_id int unsigned not null,
#     category_id int unsigned not null,
#     foreign key (post_id) references posts(id),
#     foreign key (category_id) references categories(id)
# );

insert into users(id, username, password, email, is_admin, profile_image, is_authenticated, favorite_list, answered_list) values (1, 'mandy', '$2a$10$wygL0OSDnArYf9YRA3UWZ.9JLAij1DK1aJDaFMk9S7gemTbwh5BIC', 'email@email.com', 1, 'image', 1, '3%1%2%', '');

insert into users(id, username, password, email, is_admin, profile_image, is_authenticated, favorite_list, answered_list) values (2, 'user', '$2a$10$wygL0OSDnArYf9YRA3UWZ.9JLAij1DK1aJDaFMk9S7gemTbwh5BIC', 'email@email.com', 0, 'image', 1, '3%1%', '');
insert into users(id, username, password, email, is_admin, profile_image, is_authenticated, favorite_list, answered_list) values (3, 'other', '$2a$10$wygL0OSDnArYf9YRA3UWZ.9JLAij1DK1aJDaFMk9S7gemTbwh5BIC', 'email@email.com', 0, 'image', 1, '1%2%', '');

insert into category(id, name) VALUES (1, 'cooking');
insert into category(id, name) VALUES (2, 'animals');
insert into category(id, name) VALUES (3, 'life');

insert into questions(id, created_at, title) VALUES (1, '2020-01-02', 'what is your name');
insert into questions(id, created_at, title) VALUES (2, '2020-01-02', 'do you have any pets');
insert into questions(id, created_at, title) VALUES (3, '2020-01-02', 'do you like movies');

insert into posts(id, title, content, created_at, like_count) VALUES (1, 'first post',
'about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals
about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals
about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals
about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals
about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals
about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animalsabout life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals, about life and animals', '2021-01-02', 5);

insert into posts(id, title, content, created_at, like_count) VALUES (2, 'second post', 'about water and food, about water and food, about water and food, about water and food, about water and food, about water and food, about water and food, about water and food, about water and food', '2020-03-02', 1);

insert into posts(id, title, content, created_at, like_count) VALUES (3, 'third post', 'about items and possessions, about items and possessions, about items and possessions, about items and possessions, about items and possessions, about items and possessions, about items and possessions, about items and possessions, about items and possessions', '2021-11-21', 15);




insert into post_questions(post_id, question_id) VALUES (1, 1);

insert into post_questions(post_id, question_id) VALUES (2, 2);

insert into post_questions(post_id, question_id) VALUES (3, 3);



insert into post_categories(post_id, category_id) VALUES (1, 1);
insert into post_categories(post_id, category_id) VALUES (1, 3);
insert into post_categories(post_id, category_id) VALUES (2, 2);
insert into post_categories(post_id, category_id) VALUES (3, 2);
insert into post_categories(post_id, category_id) VALUES (3, 3);

insert into comments(post_id, created_at, content, user_id, is_approved) VALUES (1, '2020-02-18', 'i like this one waiting', 2, 0);
insert into comments(post_id, created_at, content, user_id, is_approved) VALUES (2, '2020-02-11', 'i like this one approved', 3, 1);
insert into comments(post_id, created_at, content, user_id, is_approved) VALUES (3, '2020-02-13', 'i do not really like this one approved', 2, 1);
insert into comments(post_id, created_at, content, user_id, is_approved) VALUES (3, '2020-02-24', 'i do not really like like this one waiting', 3, 0);


select * from posts;
select * from users;
select * from comments;
select * from comments where is_approved = 1;
select * from category;
select * from post_categories;