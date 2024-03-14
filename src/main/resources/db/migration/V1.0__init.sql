create table landing_types (
                               id              bigint auto_increment primary key,
                               name            varchar(64) not null,
                               price           int not null
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

create table hot_requests (
    id          bigint auto_increment primary key,
    name        varchar(64) not null,
    email       varchar(64) not null,
    phone       varchar(32) not null,
    type_id     bigint not null references landing_types(id),
    created_at  TIMESTAMP
);

create table cold_requests (
    id          bigint auto_increment primary key,
    phone       varchar(32) not null,
    name        varchar(64) not null
);

create table reviews (
    author          varchar(64) primary key,
    review          text not null
);

create table portfolios (
    title           varchar(64) primary key,
    description     text,
    img_id          bigint references images(id)
);

insert into users values
                      (1, 'Admin', '$2a$05$qGutJh4i/RsIpwccDu4PQOSfCZoTMBARIqMgk0VMUTJvnzSL/36Fi', 'ADMIN');

insert into landing_types values
                              (1, 'Startup Landing Page', 50000),
                              (2, 'Product Landing Page', 70000),
                              (3, 'Consultation Landing Page', 90000);


