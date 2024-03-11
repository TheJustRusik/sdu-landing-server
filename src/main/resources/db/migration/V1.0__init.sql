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

insert into users values (
                          1, 'Admin', '$2a$05$qGutJh4i/RsIpwccDu4PQOSfCZoTMBARIqMgk0VMUTJvnzSL/36Fi', 'ADMIN'
                         );

insert into requests values (
                             1, 'Test Name', 'test_mail@gmail.com', '+1 (123) 456 78 90', '2024-03-11 14:30:00'
                            );


