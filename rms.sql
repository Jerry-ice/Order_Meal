/*
 Navicat Premium Data Transfer

 Source Server         : admin
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : rms

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 24/05/2023 12:20:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for rfood
-- ----------------------------
DROP TABLE IF EXISTS `rfood`;
CREATE TABLE `rfood`  (
  `fid` int(11) NOT NULL COMMENT '菜品id',
  `ftype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品类型',
  `fname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜品名称',
  `fprice` decimal(10, 2) NULL DEFAULT NULL COMMENT '菜品单价',
  `fnum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rfood
-- ----------------------------
INSERT INTO `rfood` VALUES (1, '米饭', '白米饭', 2.00, 10);
INSERT INTO `rfood` VALUES (2, '鲜汤', '紫菜汤', 1.50, 10);
INSERT INTO `rfood` VALUES (3, '鲜汤', '西红柿鸡蛋汤', 2.00, 10);
INSERT INTO `rfood` VALUES (4, '凉菜', '凉拌番茄', 6.00, 10);
INSERT INTO `rfood` VALUES (5, '凉菜', '凉拌黄瓜', 5.00, 10);
INSERT INTO `rfood` VALUES (6, '热菜', '宫保鸡丁', 12.00, 10);

-- ----------------------------
-- Table structure for rorder
-- ----------------------------
DROP TABLE IF EXISTS `rorder`;
CREATE TABLE `rorder`  (
  `uid` int(11) NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ocontent` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `oamount` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `payway` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for ruser
-- ----------------------------
DROP TABLE IF EXISTS `ruser`;
CREATE TABLE `ruser`  (
  `uid` int(11) NOT NULL,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `upasswd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ruser
-- ----------------------------
INSERT INTO `ruser` VALUES (1, 'admin', '123456');
INSERT INTO `ruser` VALUES (123, '123', '123456');

SET FOREIGN_KEY_CHECKS = 1;
