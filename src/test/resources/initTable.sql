CREATE TABLE IF NOT EXISTS BASIC (
                 basic_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                 name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS unexpected_faults (
    alarm_id VARCHAR(10) PRIMARY KEY,
    line VARCHAR(10),
    process VARCHAR(10),
    alarm_type VARCHAR(10),
    alarm_time DATETIME
);