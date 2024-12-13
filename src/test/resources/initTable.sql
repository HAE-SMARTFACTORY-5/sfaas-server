CREATE TABLE IF NOT EXISTS BASIC (
                 basic_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                 name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS department
(
    dept_id   bigint auto_increment
        primary key,
    dept_name varchar(255) not null
);


CREATE TABLE IF NOT EXISTS unexpected_faults (
    alarm_id VARCHAR(10) PRIMARY KEY,
    line VARCHAR(10),
    process VARCHAR(10),
    alarm_type VARCHAR(10),
    alarm_time DATETIME
);

CREATE TABLE IF NOT EXISTS user
(
    user_id     int auto_increment
        primary key,
    factory_id  bigint                              null,
    name        varchar(50)                         null,
    employee_id varchar(100)                        not null,
    password    varchar(200)                        not null,
    dept_id     varchar(20)                         null,
    position    varchar(50)                         null,
    role        varchar(20)                         null,
    created_at  timestamp default CURRENT_TIMESTAMP null,
    constraint employee_id
        unique (employee_id)
);

CREATE index IF NOT EXISTS dept_id
    on user (dept_id);

CREATE TABLE IF NOT EXISTS department
(
    dept_id   bigint auto_increment
        primary key,
    dept_name varchar(255) not null
);
