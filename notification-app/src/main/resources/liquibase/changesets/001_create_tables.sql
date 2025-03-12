create table notification
(
    user_id   bigint primary key,
    user_name varchar(30) not null,
    tab_name  varchar(30) not null,
    state     varchar(10) not null
);