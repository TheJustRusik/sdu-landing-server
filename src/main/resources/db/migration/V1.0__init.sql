create table users (
    id          bigint auto_increment primary key,
    name        varchar(16) not null unique,
    password    varchar(80) not null,
    role        varchar(16) not null
);

create table requests (
    id          bigint auto_increment primary key,
    name        varchar(64) not null,
    email       varchar(64) not null,
    phone       varchar(32) not null,
    created_at  TIMESTAMP
);

create table contents (
    id          varchar(255) primary key,
    content     character large object not null
);

insert into users values
                      (1, 'Admin', '$2a$05$qGutJh4i/RsIpwccDu4PQOSfCZoTMBARIqMgk0VMUTJvnzSL/36Fi', 'ADMIN');

insert into contents values
                      ('phone_number', '+7 (777)-777-7777'),
                      ('logo_image', 'images/logo.png'),
                      ('license_file', 'files/LICENSE.md');



