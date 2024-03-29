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

 Date: 17/12/2022 19:39:16
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
-- Records of tb_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_category` VALUES ('1509535590197235712', '纸', '斤', 0.60, 0.62, '2022-03-31 22:18:25', '2022-03-31 22:18:25');
INSERT INTO `tb_category` VALUES ('1509535689396719616', '铁', '斤', 1.30, 1.50, '2022-03-31 22:18:49', '2022-03-31 22:18:49');
INSERT INTO `tb_category` VALUES ('1510261458578182144', '铜', '斤', 2.00, 2.10, '2022-04-02 22:22:46', '2022-04-02 22:22:46');
INSERT INTO `tb_category` VALUES ('1516805479660261376', '铝', '斤', 1.30, 1.31, '2022-04-20 23:46:22', '2022-04-20 23:46:22');
INSERT INTO `tb_category` VALUES ('1516805542763565056', '铁皮', '斤', 0.80, 1.00, '2022-04-20 23:46:37', '2022-04-20 23:46:37');
INSERT INTO `tb_category` VALUES ('1516805772816945152', '响胶', '斤', 1.00, 1.10, '2022-04-20 23:47:32', '2022-04-20 23:47:32');
COMMIT;

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
-- Records of tb_order
-- ----------------------------
BEGIN;
INSERT INTO `tb_order` VALUES ('1510261557832192000', '1509535417618403328', '张三', '2022-04-02 00:00:00', 5622.20, '2022-04-02 22:23:09', '2022-04-26 23:47:16');
INSERT INTO `tb_order` VALUES ('1510261674324791296', '1509535475998920704', '李四', '2022-04-02 00:00:00', 4501.70, '2022-04-02 22:23:37', '2022-04-26 23:45:37');
INSERT INTO `tb_order` VALUES ('1516079963504381952', '1510261314382204928', '王五', '2022-04-06 00:00:00', 10509.70, '2022-04-18 23:43:25', '2022-04-26 23:46:43');
INSERT INTO `tb_order` VALUES ('1516084820814598144', '1509535475998920704', '李四', '2022-04-12 00:00:00', 1264.40, '2022-04-19 00:02:43', '2022-04-26 23:47:03');
INSERT INTO `tb_order` VALUES ('1519337792541560832', '1517873601322422272', '小明', '2022-04-27 00:00:00', 1490.60, '2022-04-27 23:28:52', '2022-04-27 23:29:59');
COMMIT;

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
-- Records of tb_order_item
-- ----------------------------
BEGIN;
INSERT INTO `tb_order_item` VALUES ('1510261557861552128', '1510261557832192000', '1509535689396719616', '1509535417618403328', 3242, 4214.60, '2022-04-02 22:23:09', '2022-04-26 23:47:16', 4863.00);
INSERT INTO `tb_order_item` VALUES ('1510261557882523648', '1510261557832192000', '1509535590197235712', '1509535417618403328', 12, 7.20, '2022-04-02 22:23:09', '2022-04-26 23:47:16', 7.44);
INSERT INTO `tb_order_item` VALUES ('1510261557895106560', '1510261557832192000', '1510261458578182144', '1509535417618403328', 123, 246.00, '2021-04-02 22:23:09', '2022-04-26 23:47:16', 258.30);
INSERT INTO `tb_order_item` VALUES ('1510261674349957120', '1510261674324791296', '1509535689396719616', '1509535475998920704', 123, 159.90, '2022-04-02 22:23:37', '2022-04-26 23:45:37', 184.50);
INSERT INTO `tb_order_item` VALUES ('1510261674370928640', '1510261674324791296', '1509535590197235712', '1509535475998920704', 123, 73.80, '2022-04-02 22:23:37', '2022-04-26 23:45:37', 76.26);
INSERT INTO `tb_order_item` VALUES ('1510261674391900160', '1510261674324791296', '1510261458578182144', '1509535475998920704', 2134, 4268.00, '2022-04-02 22:23:37', '2022-04-26 23:45:37', 4481.40);
INSERT INTO `tb_order_item` VALUES ('1516079963646988288', '1516079963504381952', '1509535590197235712', '1510261314382204928', 666, 399.60, '2022-04-18 23:43:25', '2022-04-26 23:46:43', 412.92);
INSERT INTO `tb_order_item` VALUES ('1516079963667959808', '1516079963504381952', '1509535689396719616', '1510261314382204928', 7777, 10110.10, '2022-04-18 23:43:25', '2022-04-26 23:46:43', 11665.50);
INSERT INTO `tb_order_item` VALUES ('1516084820906872832', '1516084820814598144', '1510261458578182144', '1509535475998920704', 55, 110.00, '2022-04-19 00:02:43', '2022-04-26 23:47:03', 115.50);
INSERT INTO `tb_order_item` VALUES ('1516084820936232960', '1516084820814598144', '1509535689396719616', '1509535475998920704', 888, 1154.40, '2022-04-19 00:02:43', '2022-04-26 23:47:03', 1332.00);
INSERT INTO `tb_order_item` VALUES ('1516806000697675776', '1510261557832192000', '1516805479660261376', '1509535417618403328', 888, 1154.40, '2022-04-20 23:48:26', '2022-04-26 23:47:16', 1163.28);
INSERT INTO `tb_order_item` VALUES ('1519337792776441856', '1519337792541560832', '1516805542763565056', '1517873601322422272', 1155, 924.00, '2022-04-27 23:28:52', '2022-04-27 23:29:59', 1155.00);
INSERT INTO `tb_order_item` VALUES ('1519337792809996288', '1519337792541560832', '1509535689396719616', '1517873601322422272', 42, 54.60, '2022-04-27 23:28:52', '2022-04-27 23:29:59', 63.00);
INSERT INTO `tb_order_item` VALUES ('1519337792830967808', '1519337792541560832', '1516805479660261376', '1517873601322422272', 234, 304.20, '2022-04-27 23:28:52', '2022-04-27 23:29:59', 306.54);
INSERT INTO `tb_order_item` VALUES ('1519337792851939328', '1519337792541560832', '1509535590197235712', '1517873601322422272', 123, 73.80, '2022-04-27 23:28:52', '2022-04-27 23:29:59', 76.26);
INSERT INTO `tb_order_item` VALUES ('1519337792872910848', '1519337792541560832', '1510261458578182144', '1517873601322422272', 56, 112.00, '2022-04-27 23:28:52', '2022-04-27 23:29:59', 117.60);
INSERT INTO `tb_order_item` VALUES ('1519337792898076672', '1519337792541560832', '1516805772816945152', '1517873601322422272', 22, 22.00, '2022-04-27 23:28:52', '2022-04-27 23:29:59', 24.20);
COMMIT;

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

-- ----------------------------
-- Records of tb_users
-- ----------------------------
BEGIN;
INSERT INTO `tb_users` VALUES ('1509535417618403328', '张三', '1234567788', '男', '2022-03-31 22:17:44', '2022-03-31 22:17:44');
INSERT INTO `tb_users` VALUES ('1509535475998920704', '李四', '234121452345', '男', '2022-04-23 22:17:58', '2022-03-31 22:17:58');
INSERT INTO `tb_users` VALUES ('1510261314382204928', '王五', '3442323', '男', '2022-04-22 22:22:11', '2022-04-02 22:22:11');
INSERT INTO `tb_users` VALUES ('1517873601322422272', '小明', '12345678', '男', '2022-04-23 22:30:42', '2022-04-23 22:30:42');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
