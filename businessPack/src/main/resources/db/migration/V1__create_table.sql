-- Таблица, хранящая данные для данных о людях, указанных в документе 
CREATE TABLE person(
    id          integer         primary key autoincrement               ,
    name        varchar         not null                                ,
    "role"      varchar         not null                                ,
    last_used   date            not null    default current_date        ,
    usage_count integer         not null    default 1                   ,
    tab         varchar         not null
);

-- CREATE TABLE maintenance(
--     id          integer         primary key autoincrement               ,
--     name        varchar         not null    unique                      ,
--     amount      numeric(10, 2)  not null
-- );
--
-- CREATE TABLE journal_work(
--     id          integer         primary key autoincrement               ,
--     client      varchar         not null    unique                      ,
--     work_date   date       not null    default current_date        ,
--     maintenance_id integer      not null    references maintenance(id)  ,
--     person      integer         not null    references person(id)
-- );

-- Хранятся данные из таблиц документа
CREATE TABLE data_work(
    id          integer         primary key autoincrement               ,
    name        varchar                                                 ,
    unit_meas   varchar(10)                                             ,
    count       integer         default 0                               ,
    price       numeric(10,2)                                           ,
    vat         numeric(3,2)                                            ,
    summa       numeric(10,2)                                           ,
    "group"     varchar                                                 ,
    tab         varchar         not null
);

-- Таблица данных пользователя
CREATE TABLE user_data (
    id               integer         primary key                        ,
    telegram_name    VARCHAR(255)                                       ,
    email            VARCHAR(255)
);

-- Таблица состояния документа
CREATE TABLE tab_status (
    tab_name         VARCHAR(30)    NOT NULL                            ,
    status           VARCHAR(30)    NOT NULL
);