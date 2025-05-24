create table notification
(
    id        bigserial      primary key    ,
    user_id   bigint                        ,
    user_name varchar(30)                   ,
    user_mail varchar(50)                   ,
    tab_name  varchar(30)    not null       ,
    state     varchar(10)    not null       ,
    channel   varchar(30)    DEFAULT 'MAIL' ,
    enable    boolean        not null
);