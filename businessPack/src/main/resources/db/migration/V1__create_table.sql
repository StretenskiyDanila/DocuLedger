CREATE TABLE person(
    id          integer         primary key autoincrement               ,
    name        varchar         not null                                ,
    "role"      varchar         not null                                ,
    last_used   timestamp       not null    default current_date        ,
    usage_count integer         not null    default 1
);

CREATE TABLE maintenance(
    id          integer         primary key autoincrement               ,
    name        varchar         not null    unique                      ,
    amount      numeric(10, 2)  not null
);

CREATE TABLE journal_work(
    id          integer         primary key autoincrement               ,
    client      varchar         not null    unique                      ,
    work_date   timestamp       not null    default current_date        ,
    maintenance varchar         not null    references maintenance(id)  ,
    person      varchar         not null    references person(id)
);

CREATE TABLE data_work(
    id          integer         primary key autoincrement               ,
    count       varchar                                                 ,
    name        varchar                                                 ,
    price       varchar                                                 ,
    "group"     varchar                                                 ,
    vat         varchar                                                 ,
    unit_meas   varchar                                                 ,
    summa       varchar
);