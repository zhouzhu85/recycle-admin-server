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

 Date: 24/04/2022 23:21:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `id` varchar(255) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL COMMENT '分类名称',
  `unit_name` varchar(255) DEFAULT NULL COMMENT '废品单位名称',
  `unit_value` decimal(20,2) DEFAULT NULL COMMENT '废品回收单价',
  `sale_unit_value` decimal(20,2) DEFAULT NULL COMMENT '出售单价',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_no` varchar(255) NOT NULL COMMENT '订单编号',
  `user_id` varchar(255) DEFAULT NULL COMMENT '回收客id',
  `user_name` varchar(255) DEFAULT NULL COMMENT '回收客名字',
  `receipt_date` datetime DEFAULT NULL COMMENT '收据日期',
  `all_amount` decimal(65,2) DEFAULT NULL COMMENT '总计金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `order_item_no` varchar(255) NOT NULL COMMENT '子订单号',
  `order_no` varchar(255) DEFAULT NULL COMMENT '订单号',
  `category_id` varchar(255) DEFAULT NULL COMMENT '分类id',
  `user_id` varchar(255) DEFAULT NULL COMMENT '用户id',
  `catty_number` bigint(15) DEFAULT NULL COMMENT '斤数',
  `amount` decimal(50,2) DEFAULT NULL COMMENT '回收金额',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `sale_amount` decimal(50,2) DEFAULT NULL COMMENT '出售金额',
  PRIMARY KEY (`order_item_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tb_users
-- ----------------------------
DROP TABLE IF EXISTS `tb_users`;
CREATE TABLE `tb_users` (
  `id` varchar(255) NOT NULL,
  `user_name` varchar(255) DEFAULT NULL COMMENT '回收客名字',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

SET FOREIGN_KEY_CHECKS = 1;
