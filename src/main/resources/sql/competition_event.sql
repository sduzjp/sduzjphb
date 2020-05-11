/*
Navicat MySQL Data Transfer

Source Server         : localohost
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : image_search

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020/3/25 16:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- --------------------------------------
-- Table structure for competition_event
-- --------------------------------------
DROP TABLE IF EXISTS `competition_event`;
CREATE TABLE `competition_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_code` varchar(20) NOT NULL COMMENT '租户代码',
  `competition_event_code` varchar(20) NOT NULL COMMENT '比赛项目编码',
  `competition_event_name` varchar(50) DEFAULT NULL COMMENT '比赛项目名称',
  `suite_type` int(11) DEFAULT NULL COMMENT '组别',
  `plan_start_at` DATE DEFAULT NULL COMMENT '计划开始时间',
  `plan_end_at` DATE DEFAULT NULL COMMENT '计划结束时间',
  `status` varchar(20) DEFAULT NULL COMMENT '状态',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar (50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar (50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- --------------------------------------
-- Table structure for dict
-- --------------------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(20) NOT NULL COMMENT '数据类型',
  `dict_code` int(10)  DEFAULT NULL COMMENT '字典编码',
  `dict_value` varchar (20) DEFAULT NULL COMMENT '字典值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
