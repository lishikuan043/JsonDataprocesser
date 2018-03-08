CREATE DATABASE jd_service;

USE jd_service;

/*
  创建数据表，表名账务
*/

CREATE TABLE jd_item (
  jdid INT PRIMARY KEY AUTO_INCREMENT,
  address VARCHAR(200),
  services VARCHAR(200),
  num INT
);

INSERT INTO jd_item(address,services,num) VALUES (?,?,?);