/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : labs

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-03-04 18:30:51
*/

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`
(
    `id`              int(11)     NOT NULL AUTO_INCREMENT,
    `tenant_code`     varchar(20) NOT NULL COMMENT '租户代码',
    `department_code` varchar(50)  DEFAULT NULL COMMENT '部门代码',
    `department_name` varchar(50)  DEFAULT NULL COMMENT '部门名称',
    `contact`         varchar(50)  DEFAULT NULL COMMENT '联系人',
    `contact_phone`   varchar(100) DEFAULT NULL COMMENT '联系电话',
    `description`     varchar(255) DEFAULT NULL COMMENT '描述',
    `created_at`      datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by`      varchar(50)  DEFAULT NULL COMMENT '联系电话',
    `updated_by`      varchar(50)  DEFAULT NULL COMMENT '联系电话',
    `deleted`         tinyint(4)   DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department`
VALUES ('1', '001', 'DP1912030007', '通信所', '张三', '1532384234234', '这是一条备注', '2019-12-03 17:31:28',
        '2020-03-04 18:14:23', 'TE00000001', '001', '0');
INSERT INTO `department`
VALUES ('2', '001', 'DP1912030008', '通信所', '张三', '1532384234234', '这是一条备注', '2019-12-03 17:48:06',
        '2019-12-03 17:48:06', 'TE00000001', 'TE00000001', '0');

-- ----------------------------
-- Table structure for key_max_value
-- ----------------------------
DROP TABLE IF EXISTS `key_max_value`;
CREATE TABLE `key_max_value`
(
    `id`            int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tenant_code`   varchar(20) DEFAULT NULL COMMENT '租户代码',
    `key_prefix`    char(2)     DEFAULT NULL COMMENT '业务主键前缀',
    `date_part`     char(6)     DEFAULT NULL COMMENT '日期',
    `current_value` int(11)     DEFAULT '1' COMMENT '业务后缀',
    `updated_at`    datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk` (`tenant_code`, `key_prefix`, `date_part`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of key_max_value
-- ----------------------------
INSERT INTO `key_max_value`
VALUES ('32', '001', 'DP', '191203', '8', '2019-12-03 10:40:28');

-- ----------------------------
-- Table structure for supply
-- ----------------------------
DROP TABLE IF EXISTS `supply`;
CREATE TABLE `supply`
(
    `id`                 int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title`              varchar(50)    DEFAULT NULL COMMENT '供应标题',
    `category_id`        int(11)        DEFAULT NULL COMMENT '对应品类',
    `province`           varchar(6)     DEFAULT NULL COMMENT '省级编号',
    `city`               varchar(6)     DEFAULT NULL COMMENT '市级编号',
    `district`           varchar(6)     DEFAULT NULL COMMENT '镇/区编号',
    `address`            varchar(100)   DEFAULT NULL COMMENT '详细地址',
    `property`           varchar(100)   DEFAULT NULL COMMENT '供应属性',
    `description`        varchar(1000)  DEFAULT NULL COMMENT '商品描述',
    `sell_date_type`     int(11)        DEFAULT NULL COMMENT '供货日期类型',
    `sell_date`          datetime       DEFAULT NULL COMMENT '供货日期',
    `specification_name` varchar(100)   DEFAULT NULL COMMENT '最低价规格名称',
    `unit`               int(11)        DEFAULT NULL COMMENT '最低价单位',
    `price`              decimal(20, 2) DEFAULT NULL COMMENT '最低价价格',
    `starting_value`     int(11)        DEFAULT NULL COMMENT '最低价起批量',
    `transport_type`     int(11)        DEFAULT NULL COMMENT '最低价运费设置',
    `clicks`             int(11)        DEFAULT NULL COMMENT '点击次数',
    `amount`             decimal(20, 2) DEFAULT '0.00' COMMENT '成交额',
    `created_by`         int(11)        DEFAULT NULL COMMENT '创建人',
    `updated_by`         int(11)        DEFAULT NULL COMMENT '更新人',
    `created_at`         datetime       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`         datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted`            tinyint(1)     DEFAULT '0' COMMENT '删除标记',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='供应';

-- ----------------------------
-- Records of supply
-- ----------------------------


-- --------------------------------------
-- Table structure for competition_event
-- --------------------------------------
DROP TABLE IF EXISTS `competition_event`;
CREATE TABLE `competition_event`
(
    `id`                     int(11)     NOT NULL AUTO_INCREMENT,
    `tenant_code`            varchar(20) NOT NULL COMMENT '租户代码',
    `competition_event_code` varchar(20) NOT NULL COMMENT '比赛项目编码',
    `competition_event_name` varchar(50) DEFAULT NULL COMMENT '比赛项目名称',
    `suite_type`             int(11)     DEFAULT NULL COMMENT '组别',
    `plan_start_at`          DATE        DEFAULT NULL COMMENT '计划开始时间',
    `plan_end_at`            DATE        DEFAULT NULL COMMENT '计划结束时间',
    `status`                 varchar(20) DEFAULT NULL COMMENT '状态',
    `created_at`             datetime    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`             datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `created_by`             varchar(50) DEFAULT NULL COMMENT '创建人',
    `updated_by`             varchar(50) DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of competition_event
-- ----------------------------
INSERT INTO `competition_event`
VALUES ('1', '001', '0001', '长跑', '1', '2020-03-30 23:13:28',
        '2020-04-30 18:14:23', '1', '2020-03-29 08:30:20', '2020-03-29 18:30:25', '张三', '李四');
INSERT INTO `competition_event`
VALUES ('2', '001', '0002', '长跑', '1', '2020-03-31 23:13:28',
        '2020-04-20 18:14:23', '1', '2020-03-29 08:30:20', '2020-03-29 19:20:29', '张三', '李四');

-- --------------------------------------
-- Table structure for dict
-- --------------------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`
(
    `id`         int(11)     NOT NULL AUTO_INCREMENT,
    `dict_type`  varchar(20) NOT NULL COMMENT '数据类型',
    `dict_code`  int(10)     DEFAULT NULL COMMENT '字典编码',
    `dict_value` varchar(20) DEFAULT NULL COMMENT '字典值',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

INSERT INTO `dict`
VALUES ('1', 'suite_type', '1', '成年组');
INSERT INTO `dict`
VALUES ('2', 'suite_type', '2', '青少年组');
INSERT INTO `dict`
VALUES ('3', 'suite_type', '3', '老年组');
INSERT INTO `dict`
VALUES ('4', 'status_type', '1', '未开始');
INSERT INTO `dict`
VALUES ('5', 'status_type', '2', '进行中');
INSERT INTO `dict`
VALUES ('6', 'status_type', '3', '已结束');