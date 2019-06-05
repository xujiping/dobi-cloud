/*
Navicat MySQL Data Transfer

Source Server         : dobi-rds
Source Server Version : 50725
Source Host           : rm-m5e8r75zz97048wqp3o.mysql.rds.aliyuncs.com:3306
Source Database       : cloud_admin

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-06-05 18:14:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '登录用户名',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `nickname` varchar(20) NOT NULL COMMENT '昵称',
  `realname` varchar(20) NOT NULL COMMENT '真实姓名',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(4) NOT NULL DEFAULT '0' COMMENT '性别：0未知 1男 2女',
  `age` tinyint(4) NOT NULL DEFAULT '0' COMMENT '年龄',
  `birthday` bigint(20) DEFAULT NULL COMMENT '出生日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` tinyint(4) NOT NULL COMMENT '状态：0不可用 1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='平台用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
