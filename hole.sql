/*
 Navicat Premium Data Transfer

 Source Server         : local
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : localhost:3306
 Source Schema         : hole

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 17/07/2020 10:42:03
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
  `hug` int(11) NOT NULL DEFAULT 0 COMMENT '拥抱',
  `release_time` datetime(0) NOT NULL COMMENT '发布时间',
  `evaluable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '可评论的?0不可评论1可评论，默认可评论',
  `state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '0私密1公开，默认公开',
  `valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除保留位，0无效1有效，默认有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `user_id` int(11) NOT NULL COMMENT '所属用户',
  `blog_id` int(11) NOT NULL COMMENT '所属心事',
  `content` varchar(280) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '心事内容',
  `thank` int(11) NOT NULL DEFAULT 0 COMMENT '感谢',
  `state` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0公开1私信，默认公开',
  `tip_off` tinyint(1) NOT NULL DEFAULT 0 COMMENT '举报保留位，1为被举报',
  `valid` tinyint(1) NOT NULL DEFAULT 1 COMMENT '删除保留位，0无效1有效，默认有效',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键自增',
  `open_id` varchar(128) NOT NULL COMMENT '小程序用户唯一标识',
  `address` int(11) NOT NULL COMMENT '树洞地址',
  `nick_name` varchar(64) NOT NULL COMMENT '昵称',
  `valid` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否有效，0无效，1有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `open_id` (`open_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
SET FOREIGN_KEY_CHECKS = 1;
