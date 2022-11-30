/*
 Navicat Premium Data Transfer

 Source Server         : local-db
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : leave

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/11/2022 22:44:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_leave_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_leave_comment`;
CREATE TABLE `t_leave_comment`  (
  `id` bigint(20) NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `flag` bit(1) NOT NULL,
  `leave_id` bigint(20) NULL DEFAULT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_leave_comment
-- ----------------------------
INSERT INTO `t_leave_comment` VALUES (1669818919294, NULL, '2022-11-30 22:35:19.294000', b'1', 2, 1);
INSERT INTO `t_leave_comment` VALUES (1669818984952, '不行哦，明天来加班', '2022-11-30 22:36:24.952000', b'0', 3, 1);

-- ----------------------------
-- Table structure for t_leave_order
-- ----------------------------
DROP TABLE IF EXISTS `t_leave_order`;
CREATE TABLE `t_leave_order`  (
  `id` bigint(20) NOT NULL,
  `approval_user_id` bigint(20) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` datetime(6) NULL DEFAULT NULL,
  `create_user_id` bigint(20) NULL DEFAULT NULL,
  `state` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_leave_order
-- ----------------------------
INSERT INTO `t_leave_order` VALUES (2, 1, '今天不舒服，请假一天', '2022-11-30 22:14:05.054000', 5, 1);
INSERT INTO `t_leave_order` VALUES (3, 1, '今天不舒服，请假一天', '2022-11-30 22:15:03.436000', 5, 1);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `UK_jhib4legehrm4yscx9t3lirqi`(`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, '123456', 'daluge');
INSERT INTO `t_user` VALUES (2, '123456', 'daluge2');
INSERT INTO `t_user` VALUES (3, '123456', 'daluge3');
INSERT INTO `t_user` VALUES (4, '123456', 'daluge4');
INSERT INTO `t_user` VALUES (5, '123456', 'xiaotang');

SET FOREIGN_KEY_CHECKS = 1;
