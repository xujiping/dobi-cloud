/*
Navicat MySQL Data Transfer

Source Server         : dobi-rds
Source Server Version : 50725
Source Host           : rm-m5e8r75zz97048wqp3o.mysql.rds.aliyuncs.com:3306
Source Database       : pets

Target Server Type    : MYSQL
Target Server Version : 50725
File Encoding         : 65001

Date: 2019-07-08 16:17:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for carousel
-- ----------------------------
DROP TABLE IF EXISTS `carousel`;
CREATE TABLE `carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subject` varchar(20) NOT NULL DEFAULT 'home' COMMENT '主题：home首页',
  `url` varchar(255) DEFAULT NULL COMMENT '轮播图片路径json',
  `skip` varchar(255) DEFAULT NULL COMMENT '跳转',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status` varchar(10) NOT NULL DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='轮播图';

-- ----------------------------
-- Records of carousel
-- ----------------------------

-- ----------------------------
-- Table structure for circle
-- ----------------------------
DROP TABLE IF EXISTS `circle`;
CREATE TABLE `circle` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '发布人ID',
  `subject` varchar(500) DEFAULT NULL COMMENT '主题：视频、图文',
  `type_id` int(20) NOT NULL COMMENT '类别ID',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `cover` varchar(300) DEFAULT NULL COMMENT '封面JSON',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(20) DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='圈子';

-- ----------------------------
-- Records of circle
-- ----------------------------

-- ----------------------------
-- Table structure for circle_comment
-- ----------------------------
DROP TABLE IF EXISTS `circle_comment`;
CREATE TABLE `circle_comment` (
  `id` bigint(20) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `subject` varchar(20) NOT NULL DEFAULT 'circle' COMMENT '主体 ',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `content` varchar(255) NOT NULL COMMENT '评论内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` varchar(20) NOT NULL DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='圈子评论';

-- ----------------------------
-- Records of circle_comment
-- ----------------------------

-- ----------------------------
-- Table structure for circle_type
-- ----------------------------
DROP TABLE IF EXISTS `circle_type`;
CREATE TABLE `circle_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(20) DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='圈子类型';

-- ----------------------------
-- Records of circle_type
-- ----------------------------
INSERT INTO `circle_type` VALUES ('1', '萌宠趣事', '2019-06-18 14:58:21', 'normal');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `subject` varchar(10) NOT NULL DEFAULT 'circle' COMMENT '主题：circle圈子 demand需求',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COMMENT='收藏表';

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:35:47');
INSERT INTO `collect` VALUES ('2', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:38:20');
INSERT INTO `collect` VALUES ('3', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:38:27');
INSERT INTO `collect` VALUES ('4', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:39:02');
INSERT INTO `collect` VALUES ('5', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:41:30');
INSERT INTO `collect` VALUES ('6', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:42:15');
INSERT INTO `collect` VALUES ('7', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:43:01');
INSERT INTO `collect` VALUES ('8', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:45:06');
INSERT INTO `collect` VALUES ('9', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:45:07');
INSERT INTO `collect` VALUES ('10', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:45:31');
INSERT INTO `collect` VALUES ('11', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:45:33');
INSERT INTO `collect` VALUES ('12', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:48:54');
INSERT INTO `collect` VALUES ('13', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:50:10');
INSERT INTO `collect` VALUES ('14', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:55:08');
INSERT INTO `collect` VALUES ('15', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 16:59:17');
INSERT INTO `collect` VALUES ('16', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:00:17');
INSERT INTO `collect` VALUES ('17', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:01:29');
INSERT INTO `collect` VALUES ('18', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:02:19');
INSERT INTO `collect` VALUES ('19', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:03:07');
INSERT INTO `collect` VALUES ('20', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:04:33');
INSERT INTO `collect` VALUES ('21', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:06:17');
INSERT INTO `collect` VALUES ('22', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:07:38');
INSERT INTO `collect` VALUES ('23', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '123', '2019-06-15 17:08:22');
INSERT INTO `collect` VALUES ('24', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:12:18');
INSERT INTO `collect` VALUES ('25', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:13:09');
INSERT INTO `collect` VALUES ('26', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:13:39');
INSERT INTO `collect` VALUES ('27', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:15:05');
INSERT INTO `collect` VALUES ('28', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:15:49');
INSERT INTO `collect` VALUES ('29', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:18:10');
INSERT INTO `collect` VALUES ('30', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:21:43');
INSERT INTO `collect` VALUES ('31', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:22:36');
INSERT INTO `collect` VALUES ('32', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:23:07');
INSERT INTO `collect` VALUES ('33', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:29:53');
INSERT INTO `collect` VALUES ('34', '5b7f9491ac164878b3bd9246655b75ae', 'circle', '1', '2019-06-15 17:30:08');
INSERT INTO `collect` VALUES ('35', '5b7f9491ac164878b3bd9246655b75ae', 'home', '1', '2019-06-15 17:33:29');
INSERT INTO `collect` VALUES ('36', '5b7f9491ac164878b3bd9246655b75ae', 'home', '1', '2019-06-15 17:34:27');
INSERT INTO `collect` VALUES ('37', '5b7f9491ac164878b3bd9246655b75ae', 'home', '1', '2019-06-15 17:37:53');

-- ----------------------------
-- Table structure for demand
-- ----------------------------
DROP TABLE IF EXISTS `demand`;
CREATE TABLE `demand` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `type_id` int(20) NOT NULL COMMENT '需求类别ID',
  `type_name` varchar(200) NOT NULL COMMENT '类别名称',
  `pub_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `user_id` varchar(32) NOT NULL COMMENT '发布人ID',
  `pets_id` int(11) DEFAULT NULL COMMENT '宠物ID',
  `age` int(5) DEFAULT '1' COMMENT '年龄：单位月',
  `price` decimal(20,2) DEFAULT NULL COMMENT '价格',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `category_id` int(20) DEFAULT NULL COMMENT '宠物类别ID',
  `species_id` int(20) DEFAULT NULL COMMENT '宠物品种ID',
  `content` varchar(500) DEFAULT NULL COMMENT '内容详情',
  `status` varchar(20) NOT NULL DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='需求表';

-- ----------------------------
-- Records of demand
-- ----------------------------

-- ----------------------------
-- Table structure for demand_type
-- ----------------------------
DROP TABLE IF EXISTS `demand_type`;
CREATE TABLE `demand_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(20) DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='需求类型';

-- ----------------------------
-- Records of demand_type
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `timestmp` bigint(20) NOT NULL,
  `formatted_message` text NOT NULL,
  `logger_name` varchar(254) NOT NULL,
  `level_string` varchar(254) NOT NULL,
  `thread_name` varchar(254) DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `arg0` varchar(254) DEFAULT NULL,
  `arg1` varchar(254) DEFAULT NULL,
  `arg2` varchar(254) DEFAULT NULL,
  `arg3` varchar(254) DEFAULT NULL,
  `caller_filename` varchar(254) NOT NULL,
  `caller_class` varchar(254) NOT NULL,
  `caller_method` varchar(254) NOT NULL,
  `caller_line` char(4) NOT NULL,
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`event_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event_exception
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `event_id` bigint(20) NOT NULL,
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) NOT NULL,
  PRIMARY KEY (`event_id`,`i`) USING BTREE,
  CONSTRAINT `logging_event_exception_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event_exception
-- ----------------------------

-- ----------------------------
-- Table structure for logging_event_property
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `event_id` bigint(20) NOT NULL,
  `mapped_key` varchar(254) NOT NULL,
  `mapped_value` text,
  PRIMARY KEY (`event_id`,`mapped_key`) USING BTREE,
  CONSTRAINT `logging_event_property_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of logging_event_property
-- ----------------------------

-- ----------------------------
-- Table structure for pets
-- ----------------------------
DROP TABLE IF EXISTS `pets`;
CREATE TABLE `pets` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `category_id` int(20) NOT NULL COMMENT '类别ID',
  `species_id` int(20) NOT NULL COMMENT '品种ID',
  `nickname` varchar(100) NOT NULL COMMENT '昵称',
  `age` int(8) NOT NULL COMMENT '年龄',
  `sex` int(2) DEFAULT '1' COMMENT '性别   1： 男     2： 女     3：保密',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新日期',
  `survival` int(1) NOT NULL DEFAULT '1' COMMENT '是否存活    1：是    2：否',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物表';

-- ----------------------------
-- Records of pets
-- ----------------------------

-- ----------------------------
-- Table structure for pets_category
-- ----------------------------
DROP TABLE IF EXISTS `pets_category`;
CREATE TABLE `pets_category` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(20) DEFAULT 'normal',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='宠物类别表';

-- ----------------------------
-- Records of pets_category
-- ----------------------------
INSERT INTO `pets_category` VALUES ('1', '狗狗', '2019-06-18 14:01:08', 'normal');
INSERT INTO `pets_category` VALUES ('3', '猫咪', '2019-06-18 14:11:53', 'normal');
INSERT INTO `pets_category` VALUES ('4', '鸟类', '2019-06-18 14:22:01', 'normal');

-- ----------------------------
-- Table structure for pets_image
-- ----------------------------
DROP TABLE IF EXISTS `pets_image`;
CREATE TABLE `pets_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `pets_id` bigint(20) NOT NULL COMMENT '宠物ID',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `default_image` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否默认',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物图片表';

-- ----------------------------
-- Records of pets_image
-- ----------------------------

-- ----------------------------
-- Table structure for pets_like
-- ----------------------------
DROP TABLE IF EXISTS `pets_like`;
CREATE TABLE `pets_like` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(32) NOT NULL COMMENT '用户ID',
  `subject` varchar(10) NOT NULL DEFAULT 'circle' COMMENT '主题：circle圈子 demand需求',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='点赞表';

-- ----------------------------
-- Records of pets_like
-- ----------------------------

-- ----------------------------
-- Table structure for pets_species
-- ----------------------------
DROP TABLE IF EXISTS `pets_species`;
CREATE TABLE `pets_species` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `category_id` int(20) NOT NULL COMMENT '类别ID',
  `heat` int(20) DEFAULT NULL COMMENT '发情期',
  `image` varchar(255) DEFAULT NULL COMMENT '默认图片',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(20) DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物品种表';

-- ----------------------------
-- Records of pets_species
-- ----------------------------

-- ----------------------------
-- Table structure for pets_species_detail
-- ----------------------------
DROP TABLE IF EXISTS `pets_species_detail`;
CREATE TABLE `pets_species_detail` (
  `id` int(11) NOT NULL COMMENT '品种ID',
  `intro` varchar(255) DEFAULT NULL COMMENT '简介',
  `details` varchar(2048) NOT NULL DEFAULT '暂无介绍' COMMENT '详细介绍',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='宠物品种详情';

-- ----------------------------
-- Records of pets_species_detail
-- ----------------------------

-- ----------------------------
-- Table structure for remind_type
-- ----------------------------
DROP TABLE IF EXISTS `remind_type`;
CREATE TABLE `remind_type` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `status` varchar(20) DEFAULT 'normal' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提醒类型表';

-- ----------------------------
-- Records of remind_type
-- ----------------------------
