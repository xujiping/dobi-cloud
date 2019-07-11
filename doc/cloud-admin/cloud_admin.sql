/*
Navicat MySQL Data Transfer

Source Server         : dobi-rds
Source Server Version : 50725
Source Host           : rm-m5e8r75zz97048wqp3o.mysql.rds.aliyuncs.com:3306
Source Database       : cloud_admin

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-08 16:17:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '标题',
  `platform_id` int(11) NOT NULL COMMENT '平台ID',
  `menu_name` varchar(20) NOT NULL COMMENT '菜单名',
  `menu_value` varchar(20) NOT NULL COMMENT '菜单值',
  `up_id` int(11) DEFAULT NULL COMMENT '上级ID',
  `level` tinyint(4) NOT NULL DEFAULT '1' COMMENT '级别：1最高',
  `uri` varchar(255) NOT NULL COMMENT '菜单URI',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0不可用 1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '角色管理', '10000', '角色管理', 'role', null, '1', '/sysRole/**', '1');
INSERT INTO `sys_permission` VALUES ('2', '角色列表', '10000', '角色列表', 'role-list', '1', '2', '/sysRole/page', '1');
INSERT INTO `sys_permission` VALUES ('3', '分配角色', '10000', '分配角色', 'role-user', '1', '2', '/sysRole/user', '1');
INSERT INTO `sys_permission` VALUES ('4', '角色列表2', '10000', '列表2', 'role-list', '2', '3', '', '1');
INSERT INTO `sys_permission` VALUES ('5', '列表3', '10000', '列表3', '', '4', '4', '', '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL COMMENT '角色名',
  `role_intro` varchar(255) DEFAULT NULL COMMENT '简介',
  `platform_id` int(11) NOT NULL COMMENT '平台ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0不可用 1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='平台角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'administrator', '管理员', '10000', '2019-06-09 10:08:51', '2019-07-03 11:24:00', '1');
INSERT INTO `sys_role` VALUES ('2', 'test', '1233', '10000', '2019-07-03 10:59:03', '2019-07-03 11:24:02', '1');
INSERT INTO `sys_role` VALUES ('3', '111', '1213', '10001', '2019-07-03 11:26:44', '2019-07-03 11:27:45', '1');
INSERT INTO `sys_role` VALUES ('4', 'test11', '123', '10000', '2019-07-03 11:39:38', '2019-07-03 11:39:38', '1');
INSERT INTO `sys_role` VALUES ('5', 'test', 'sdf', '10001', '2019-07-03 11:40:33', '2019-07-03 11:40:33', '1');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `permission_id` int(11) NOT NULL COMMENT '权限ID',
  `level` tinyint(4) NOT NULL COMMENT '级别',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `create_user_id` varchar(255) NOT NULL COMMENT '创建者ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('8', '1', '4', '3', '2019-07-05 14:57:59', '2019-07-05 14:57:59', '5b7f9491ac164878b3bd9246655b75ae');
INSERT INTO `sys_role_permission` VALUES ('9', '1', '5', '4', '2019-07-05 14:57:59', '2019-07-05 14:57:59', '5b7f9491ac164878b3bd9246655b75ae');
INSERT INTO `sys_role_permission` VALUES ('10', '1', '2', '2', '2019-07-05 14:57:59', '2019-07-05 14:57:59', '5b7f9491ac164878b3bd9246655b75ae');
INSERT INTO `sys_role_permission` VALUES ('13', '2', '4', '3', '2019-07-05 15:04:48', '2019-07-05 15:04:48', '5b7f9491ac164878b3bd9246655b75ae');
INSERT INTO `sys_role_permission` VALUES ('14', '2', '5', '4', '2019-07-05 15:04:48', '2019-07-05 15:04:48', '5b7f9491ac164878b3bd9246655b75ae');
INSERT INTO `sys_role_permission` VALUES ('15', '2', '2', '2', '2019-07-05 15:04:48', '2019-07-05 15:04:48', '5b7f9491ac164878b3bd9246655b75ae');
INSERT INTO `sys_role_permission` VALUES ('16', '2', '1', '1', '2019-07-05 15:04:50', '2019-07-05 15:04:50', '5b7f9491ac164878b3bd9246655b75ae');
INSERT INTO `sys_role_permission` VALUES ('17', '2', '3', '2', '2019-07-05 15:04:50', '2019-07-05 15:04:50', '5b7f9491ac164878b3bd9246655b75ae');

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `platform_id` int(11) NOT NULL COMMENT '平台ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='角色-用户关联表';

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('1', '3', '5b7f9491ac164878b3bd9246655b75ae', '10001', '2019-07-03 18:24:53');
INSERT INTO `sys_role_user` VALUES ('3', '1', '5b7f9491ac164878b3bd9246655b75ae', '10000', '2019-07-03 18:30:59');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) NOT NULL,
  `username` varchar(20) NOT NULL COMMENT '登录用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(16) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(4) DEFAULT '0' COMMENT '性别：0未知 1男 2女',
  `age` tinyint(4) DEFAULT '0' COMMENT '年龄',
  `birthday` bigint(20) DEFAULT NULL COMMENT '出生日期',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态：0不可用 1正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='平台用户';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5b7f9491ac164878b3bd9246655b75ae', 'dobi', '200BEC91840C07955A5A1D170E34CF6C', 'admin', null, null, '2', '0', null, '2019-06-06 11:42:48', '2019-07-06 15:23:14', '1');
INSERT INTO `sys_user` VALUES ('5b7f9491ac164878b3bd9246655b75bv', 'test', 'E10ADC3949BA59ABBE56E057F20F883E', 'test', null, null, '0', '0', null, '2019-07-06 11:29:00', '2019-07-06 11:31:58', '1');
