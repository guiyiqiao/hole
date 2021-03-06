/*
 Navicat Premium Data Transfer

 Source Server         : 47.105.184.154_3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : 47.105.184.154:3306
 Source Schema         : hole

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 29/08/2020 17:12:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` int(11) NOT NULL COMMENT '所属用户',
  `title` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '心事标题',
  `content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '心事内容',
  `hug` int(11) UNSIGNED NOT NULL DEFAULT 0 COMMENT '拥抱',
  `publish_time` datetime(0) NOT NULL COMMENT '发布时间',
  `evaluable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '可评论的?0不可评论1可评论，默认可评论',
  `overt` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0私密1公开，默认私密',
  `valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除保留位，0无效1有效，默认有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for blog_report
-- ----------------------------
DROP TABLE IF EXISTS `blog_report`;
CREATE TABLE `blog_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '举报内容详情',
  `solved` tinyint(1) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `blog_report_ibfk_1` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `blog_report_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` int(11) NOT NULL COMMENT '所属用户',
  `blog_id` int(11) NOT NULL COMMENT '所属心事',
  `content` varchar(280) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '心事内容',
  `publish_time` datetime(0) NOT NULL,
  `report` tinyint(1) NOT NULL DEFAULT 0 COMMENT '举报保留位，1为被举报',
  `valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除保留位，0无效1有效，默认有效',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `blog_id`(`blog_id`) USING BTREE,
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for comment_report
-- ----------------------------
DROP TABLE IF EXISTS `comment_report`;
CREATE TABLE `comment_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '举报内容详情',
  `solved` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `comment_id`(`comment_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `comment_report_ibfk_1` FOREIGN KEY (`comment_id`) REFERENCES `echo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `comment_report_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for echo
-- ----------------------------
DROP TABLE IF EXISTS `echo`;
CREATE TABLE `echo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` int(11) NOT NULL COMMENT '所属用户',
  `blog_id` int(11) NOT NULL COMMENT '所属心事',
  `content` varchar(280) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '心事内容',
  `thank` int(11) NOT NULL DEFAULT 0 COMMENT '感谢',
  `overt` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0私密1公开，默认私密',
  `publish_time` datetime(0) NOT NULL,
  `report` tinyint(1) NOT NULL DEFAULT 0 COMMENT '举报保留位，1为被举报',
  `solved` tinyint(1) NOT NULL DEFAULT 0 COMMENT '默认0 未被处理',
  `valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除保留位，0无效1有效，默认有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for echo_report
-- ----------------------------
DROP TABLE IF EXISTS `echo_report`;
CREATE TABLE `echo_report`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `echo_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `reason` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '举报内容详情',
  `solved` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `echo_id`(`echo_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `echo_report_ibfk_1` FOREIGN KEY (`echo_id`) REFERENCES `echo` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `echo_report_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键自增',
  `open_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小程序用户唯一标识',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否有效，0无效，1有效',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `open_id`(`open_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
