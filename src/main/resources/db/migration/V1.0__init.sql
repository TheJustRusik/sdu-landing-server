create table landing_types (
    id          int auto_increment primary key,
    name        varchar(64),
    price       int not null,
    discount    double not null
);

create table users (
    id          bigint auto_increment primary key,
    name        varchar(16) not null unique,
    password    varchar(80) not null,
    role        varchar(16) not null
);

create table orders (
    id          bigint auto_increment primary key,
    name        varchar(64) not null,
    phone       varchar(32) not null,
    created_at  TIMESTAMP   not null,
    checked     bool        not null default false,
    status      int         not null default 0,

    type_id     bigint references landing_types(id),
    email       varchar(64)
);

create table reviews (
    id              bigint auto_increment primary key,
    author          varchar(64) not null,
    review          text not null,
    visible         boolean not null
);

create table portfolios (
    id              bigint auto_increment primary key,
    title           varchar(64) not null,
    description     text not null,
    image           varchar(255) unique not null
);

create table contacts (
    key             varchar(32) primary key,
    value           text not null
);

insert into users values
                      (1, 'Admin', '$2a$05$qGutJh4i/RsIpwccDu4PQOSfCZoTMBARIqMgk0VMUTJvnzSL/36Fi', 'ADMIN');

insert into landing_types values
                              (1, 'Startup Landing Page', 70000, 30),
                              (2, 'Product Landing Page', 90000, 30),
                              (3, 'Consultation Landing Page', 11000, 30);

insert into contacts values
                              ('phone', '+7 (777) 777 7777'),
                              ('email', 'technopark@sdu.edu.kz'),
                              ('address', 'г. Каскелен Проспект Абылай Хана, 1/1')


