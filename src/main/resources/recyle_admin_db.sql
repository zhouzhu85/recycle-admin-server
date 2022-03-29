/*
 Navicat Premium Data Transfer

 Source Server         : 本地连接
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : recyle_admin_db

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 29/03/2022 22:56:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` varchar(255) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `unit_name` varchar(255) DEFAULT NULL,
  `unit_value` decimal(20,2) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of category
-- ----------------------------
BEGIN;
INSERT INTO `category` VALUES ('1507929784670490624', '纸', '斤', 0.81, NULL, NULL);
INSERT INTO `category` VALUES ('1507994286992658432', '铁', '斤', 1.50, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `order_no` varchar(255) NOT NULL COMMENT '订单编号',
  `user_id` varchar(255) DEFAULT NULL COMMENT '回收客id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '回收客名字',
  `receipt_date` datetime DEFAULT NULL COMMENT '收据日期',
  `all_amount` decimal(65,0) DEFAULT NULL COMMENT '总计金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `order_item_no` varchar(255) NOT NULL COMMENT '子订单号',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `category_id` varchar(255) DEFAULT NULL COMMENT '分类id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `catty_number` bigint(15) DEFAULT NULL COMMENT '斤数',
  `amount` decimal(50,0) DEFAULT NULL COMMENT '金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL COMMENT '回收客名字',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('1480463285475348480', '张三', '2234', '男', NULL, NULL);
INSERT INTO `users` VALUES ('1480463369738915840', '来来来', '2555', '男', NULL, NULL);
INSERT INTO `users` VALUES ('1480531882914615296', '551', '222', '女', NULL, NULL);
INSERT INTO `users` VALUES ('1480531914527084544', '2223111', '33', '女', NULL, NULL);
INSERT INTO `users` VALUES ('1480531945309081600', '1223', '212', '女', NULL, NULL);
INSERT INTO `users` VALUES ('1480531974803427328', '1233', '333', '女', NULL, NULL);
INSERT INTO `users` VALUES ('1480532022077427712', '33311', '33444', '男', NULL, NULL);
INSERT INTO `users` VALUES ('1480532070274174976', '去11', '333', '男', NULL, NULL);
INSERT INTO `users` VALUES ('1480532147839438848', '112223', '44488', '男', NULL, NULL);
INSERT INTO `users` VALUES ('1480532261475717120', '12344555', '233', '男', NULL, NULL);
INSERT INTO `users` VALUES ('1484823650854244352', '000', '122', '女', NULL, NULL);
INSERT INTO `users` VALUES ('1484823921839837184', '防辐射', '222', '男', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
