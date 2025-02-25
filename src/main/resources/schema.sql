create table IF NOT EXISTS films
(
    id           long         not null AUTO_INCREMENT,
    name         varchar(100) not null,
    description  TEXT,
    release_date DATE,
    duration     int,
    constraint FILMS_PK
        primary key (ID)
);


create table IF NOT EXISTS users
(
    id       long         not null AUTO_INCREMENT,
    email    varchar(512) not null,
    login    varchar(512),
    name     varchar(512),
    birthday DATE,
    constraint USERS_PK
        primary key (ID)
);

create table IF NOT EXISTS film_likes
(
    id      long not null AUTO_INCREMENT,
    film_id long not null,
    user_id long not null
);

create table IF NOT EXISTS friends
(
    id        long not null AUTO_INCREMENT,
    user_id   long not null,
    friend_id long not null
);


