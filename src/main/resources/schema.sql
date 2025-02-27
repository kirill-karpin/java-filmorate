create table if not exists films
(
    id           long         not null auto_increment,
    name         varchar(100) not null,
    description  text,
    release_date date,
    duration     int,
    constraint films_pk
        primary key (id)
);


create table if not exists users
(
    id       long         not null auto_increment,
    email    varchar(512) not null,
    login    varchar(512),
    name     varchar(512),
    birthday date,
    constraint users_pk
        primary key (id)
);

create table if not exists film_likes
(
    id      long not null auto_increment,
    film_id long not null,
    user_id long not null
);

create table if not exists friends
(
    id        long not null auto_increment,
    user_id   long not null,
    friend_id long not null,
    confirmed boolean not null default false
);

create table if not exists mpa
(
    id          long auto_increment,
    name        varchar(512) not null,
    description text,
    constraint mpa_pk
        primary key (id)
);



create table if not exists film_mpa
(
    id      long not null auto_increment,
    film_id long not null,
    mpa_id  long not null
);

create table if not exists genres
(
    id   long auto_increment,
    name varchar(512) not null,
    constraint genres_pk
        primary key (id)
);


create table if not exists film_genre
(
    id       long not null auto_increment,
    film_id  long not null,
    genre_id long not null
);

