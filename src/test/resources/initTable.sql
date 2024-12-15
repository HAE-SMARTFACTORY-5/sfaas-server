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

CREATE TABLE IF NOT EXISTS failure_rate (
    id VARCHAR(20) PRIMARY KEY,
    process VARCHAR(10),
    failure_month INT,
    failure_rate_plan DECIMAL(8, 4),
    failure_rate_actual DECIMAL(8, 4),
    mtbf DECIMAL(10, 4),
    mttr DECIMAL(10, 4),
    operation_time DECIMAL(10, 4),
    fault_num INT,
    downtime DECIMAL(10, 4)
);

create table IF NOT EXISTS user
(
    user_id    int auto_increment
        primary key,
    name       varchar(50)                         not null,
    employ_id  varchar(50)                         not null,
    password   varchar(200)                        not null,
    dept_id    varchar(20)                         null,
    position   varchar(50)                         null,
    role       varchar(20)                         null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    factory_id int                                 null,
    constraint email
        unique (employ_id),
    constraint employ_id
        unique (employ_id)
);

CREATE index IF NOT EXISTS dept_id
    on user (dept_id);

CREATE TABLE IF NOT EXISTS department
(
    dept_id   bigint auto_increment
        primary key,
    dept_name varchar(255) not null
);

create table IF NOT EXISTS factory
(
    factory_id   int         not null
        primary key,
    factory_name varchar(20) not null
);

CREATE TABLE IF NOT EXISTS maintenance_schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    line VARCHAR(10),
    process VARCHAR(10),
    machine VARCHAR(50) NOT NULL,
    contents VARCHAR(100) NOT NULL,
    remarks VARCHAR(50)
);

create table IF NOT EXISTS quality_defects
(
    date               date         not null,
    model              varchar(255) not null,
    defect_type        varchar(255) not null,
    resolved           boolean   not null,
    defective_quantity int          not null,
    shift              char(10)     not null,
    cause              varchar(255) null,
    action             varchar(255) null,
    factory_id         int          not null,
    resolved_date      date         null
);

create index IF NOT EXISTS factory_id
    on quality_defects (factory_id);



CREATE TABLE IF NOT EXISTS maintenance_schedule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    line VARCHAR(10),
    process VARCHAR(10),
    machine VARCHAR(50) NOT NULL,
    contents VARCHAR(100) NOT NULL,
    remarks VARCHAR(50)
);
create table IF NOT EXISTS quality_defects
(
    factory_id   int         not null
        primary key,
    factory_name varchar(20) not null
);

