create table users (
    id          bigint auto_increment primary key,
    name        varchar(16) not null unique,
    password    varchar(80) not null,
    role        varchar(16) not null
);

insert into users values (
                          1, 'Admin', '$2a$05$qGutJh4i/RsIpwccDu4PQOSfCZoTMBARIqMgk0VMUTJvnzSL/36Fi', 'ADMIN'
                         )

