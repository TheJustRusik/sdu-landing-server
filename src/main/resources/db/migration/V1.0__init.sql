create table landing_types (
    id          int auto_increment primary key,
    name        varchar(64),
    price       int not null,
    discount    double not null
);

create table images (
    id          bigint auto_increment primary key,
    url         varchar(255) unique not null
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
    created_at  TIMESTAMP   not null ,

    type_id     bigint references landing_types(id),
    email       varchar(64)
);

create table reviews (
    author          varchar(64) primary key,
    review          text not null
);

create table portfolios (
    title           varchar(64) primary key,
    description     text not null,
    img_id          bigint references images(id)
);

insert into users values
                      (1, 'Admin', '$2a$05$qGutJh4i/RsIpwccDu4PQOSfCZoTMBARIqMgk0VMUTJvnzSL/36Fi', 'ADMIN');

insert into landing_types values
                              (1, 'Startup Landing Page', 70000, 30),
                              (2, 'Product Landing Page', 90000, 30),
                              (3, 'Consultation Landing Page', 11000, 30);


