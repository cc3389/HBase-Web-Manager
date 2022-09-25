/*
 Navicat Premium Data Transfer

 Source Server         : mysql data
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : localhost:3306
 Source Schema         : hbasemanager

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 25/09/2022 20:21:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hbase_data_source_configuration
-- ----------------------------
DROP TABLE IF EXISTS `hbase_data_source_configuration`;
CREATE TABLE `hbase_data_source_configuration`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `properties` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hbase_data_source_configuration
-- ----------------------------

-- ----------------------------
-- Table structure for hbase_manage
-- ----------------------------
DROP TABLE IF EXISTS `hbase_manage`;
CREATE TABLE `hbase_manage`  (
  `datasource_id` int(0) NOT NULL,
  `user_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `datasource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hbase_manage
-- ----------------------------

-- ----------------------------
-- Table structure for hbase_user
-- ----------------------------
DROP TABLE IF EXISTS `hbase_user`;
CREATE TABLE `hbase_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `is_admin` bit(1) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hbase_user
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
