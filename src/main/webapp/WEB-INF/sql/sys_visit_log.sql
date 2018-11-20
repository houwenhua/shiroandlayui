/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : shiroandlayui

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2018-11-20 13:38:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_visit_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_visit_log`;
CREATE TABLE `sys_visit_log` (
  `id` varchar(255) NOT NULL COMMENT '主键',
  `visit_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '访问日期',
  `oper_name` varchar(255) DEFAULT NULL COMMENT '操作，做了什么',
  `sys_user_id` varchar(255) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
