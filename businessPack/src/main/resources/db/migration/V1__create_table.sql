CREATE TABLE masters(
    id          serial          not null     primary key,
    name        varchar         not null                ,
    position    varchar         not null
);
--
-- CREATE TABLE maintenance(
--     id          serial          not null    primary key,
--     name        varchar         not null    unique     ,
--     amount      numeric(10, 2)  not null
-- );
--
-- CREATE TABLE journal_work(
--     id          serial          not null    primary key                 ,
--     client      varchar         not null    unique                      ,
--     work_date   timestamp       not null    default current_date        ,
--     maintenance varchar         not null    references maintenance(id)  ,
--     master      varchar         not null    references masters(id)      ,
--     amount      numeric(10,2)   not null
-- );