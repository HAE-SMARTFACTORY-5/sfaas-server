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

CREATE TABLE IF NOT EXISTS preventive_maintenance (
    maintenance_id INT PRIMARY KEY, -- 정비 작업 고유 ID
    equipment_id VARCHAR(20) NOT NULL,            -- 장비 ID
    planned_date DATETIME NOT NULL,               -- 예정된 정비 일자 및 시간
    execution_date DATETIME,                      -- 정비 완료된 일자 및 시간
    inspect_result VARCHAR(10),                  -- 정비 결과 (정상, 비정상)
    estimated_time INT NOT NULL,                  -- 예상 소요 시간 (시간 단위)
    status VARCHAR(10) NOT NULL,                 -- 정비 상태 (예정, 진행중, 완료)
    created_at DATETIME NOT NULL                  -- 데이터 생성일
);

create table IF NOT EXISTS process
(
    process_id   varchar(20) not null
    primary key,
    process_name varchar(20) not null,
    description  text        null
    );

create table IF NOT EXISTS production_performance
(
    performance_id   int                                 not null
        primary key,
    line_id          varchar(50)                         null,
    date             date                                not null,
    planned_quantity int                                 not null,
    actual_quantity  int                                 not null,
    created_at       timestamp default CURRENT_TIMESTAMP null,
    shift            varchar(20)                         null,
    operating_rate   decimal(10, 2)                      null,
    process_id       varchar(50)                         null,
    constraint fk_production_process
        foreign key (process_id) references process (process_id)
);

create index IF NOT EXISTS line_id
    on production_performance (line_id);


CREATE TABLE IF NOT EXISTS unexpected_faults (
    alarm_id VARCHAR(10) PRIMARY KEY,
    line_id VARCHAR(10),
    process_id VARCHAR(10),
    alarm_type VARCHAR(10),
    alarm_time DATETIME
);

CREATE TABLE IF NOT EXISTS unexpected_faults_user_input (
    alarm_id varchar(9) NOT NULL,
    fault_detail varchar(50) NOT NULL,
    action_start_time datetime NOT NULL,
    action_completion_time datetime DEFAULT NULL,
    downtime int DEFAULT NULL,
    completion_status varchar(20) NOT NULL,
    PRIMARY KEY (alarm_id),
    CONSTRAINT unexpected_faults_user_input_ibfk_1 
    FOREIGN KEY (alarm_id) REFERENCES unexpected_faults(alarm_id)
);

CREATE TABLE IF NOT EXISTS alert_action_details (
    alert_action_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    alarm_id varchar(9),
    fault_type varchar(9),
    action_detail VARCHAR(255),
    maintenance_staff VARCHAR(100),
    action_time TIMESTAMP,
    action_sequence INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (alarm_id) REFERENCES unexpected_faults(alarm_id),
    CONSTRAINT check_max_sequence CHECK (action_sequence <= 10)
);