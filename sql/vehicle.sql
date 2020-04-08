/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : vehicle

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 08/04/2020 13:48:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_driver
-- ----------------------------
DROP TABLE IF EXISTS `base_driver`;
CREATE TABLE `base_driver`  (
  `driver_id` bigint(20) UNSIGNED NOT NULL,
  `driver_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '驾驶员姓名',
  `driver_phone` bigint(255) NOT NULL COMMENT '驾驶员电话',
  `driver_deadline` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '驾照到期时间',
  `driver_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `is_usable` tinyint(1) NOT NULL COMMENT '0：不可用，1：可用',
  `opt_user` int(255) UNSIGNED NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  PRIMARY KEY (`driver_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_driver
-- ----------------------------
INSERT INTO `base_driver` VALUES (1, '张三', 12345678910, '2029-11-23', 'remark', 1, 1, '2020-01-17 11:16:18');
INSERT INTO `base_driver` VALUES (402783580486512640, 'plr', 12345678910, '2023-01-17', 'test', 1, 1, '2020-01-17 14:51:07');
INSERT INTO `base_driver` VALUES (402835718285701120, 'test', 12345678910, '2020-01-29', 'test', 0, 1, '2020-01-17 14:45:19');
INSERT INTO `base_driver` VALUES (402835841933783040, 'test1', 12345678910, '2020-01-22', 'test', 0, 1, '2020-01-17 14:45:49');
INSERT INTO `base_driver` VALUES (402836964576997376, 'test12', 12345678910, '2020-01-29', 'test', 0, 1, '2020-01-17 14:50:57');

-- ----------------------------
-- Table structure for base_manufacturer
-- ----------------------------
DROP TABLE IF EXISTS `base_manufacturer`;
CREATE TABLE `base_manufacturer`  (
  `mfr_id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一ID',
  `mfr_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '厂商编号',
  `mfr_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '厂商名称',
  `mfr_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商地址',
  `mfr_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '厂商备注',
  `opt_user` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`mfr_id`) USING BTREE,
  UNIQUE INDEX `un_bma_mfrcode`(`mfr_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_manufacturer
-- ----------------------------
INSERT INTO `base_manufacturer` VALUES (402756456702685184, 'm000001', '大众', '上海市嘉定区安亭于田路123号', '上汽大众', 1, '2020-01-17 09:30:22');
INSERT INTO `base_manufacturer` VALUES (402757037915779072, 'm000002', '本田', '广东省广州市白云区黄石东路448号', '广汽本田', 1, '2020-01-17 09:32:40');

-- ----------------------------
-- Table structure for base_news
-- ----------------------------
DROP TABLE IF EXISTS `base_news`;
CREATE TABLE `base_news`  (
  `news_id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一字段ID',
  `news_title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `news_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `news_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '作者',
  `news_show` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否展示',
  `show_time` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `opt_user` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`news_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_news
-- ----------------------------
INSERT INTO `base_news` VALUES (345934476280471552, '公告测试', '初步完成管理页<img src=\"http://localhost:8086/layui/images/face/1.gif\" alt=\"[嘻嘻]\">', '管理员', 1, '2020-01-10', 1, '2020-01-10 16:10:13');

-- ----------------------------
-- Table structure for base_oil
-- ----------------------------
DROP TABLE IF EXISTS `base_oil`;
CREATE TABLE `base_oil`  (
  `oil_id` bigint(20) UNSIGNED NOT NULL,
  `oil_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `oil_manufacturer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '油卡厂商',
  `oil_balance` double NOT NULL COMMENT '余额',
  `opt_user` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`oil_id`) USING BTREE,
  INDEX `oid_code`(`oil_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_oil
-- ----------------------------
INSERT INTO `base_oil` VALUES (402757325947023360, 'o000001', '中国石油天然气集团有限公司', 30000, 1, '2020-01-17 09:33:49');
INSERT INTO `base_oil` VALUES (402758182243545088, 'o000002', '中国石油化工集团有限公司', 30000, 1, '2020-01-17 09:43:56');
INSERT INTO `base_oil` VALUES (402763966952124416, 'o000003', '测试厂商', 100, 1, '2020-01-17 10:00:12');

-- ----------------------------
-- Table structure for base_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `base_vehicle`;
CREATE TABLE `base_vehicle`  (
  `vehicle_id` bigint(20) UNSIGNED NOT NULL,
  `vehicle_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆编号',
  `vehicle_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '车辆名称',
  `vehicle_brand` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '型号',
  `vehicle_mfr` bigint(20) UNSIGNED NOT NULL COMMENT '厂商外键\r\n',
  `vehicle_type` bigint(20) UNSIGNED NOT NULL COMMENT '车辆类型',
  `vehicle_price` int(11) NOT NULL COMMENT '租金/每日',
  `is_usable` tinyint(4) NOT NULL COMMENT '0：不可用，1：可用',
  `opt_user` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '操作时间',
  PRIMARY KEY (`vehicle_id`) USING BTREE,
  INDEX `vehicle_code`(`vehicle_code`) USING BTREE,
  INDEX `fk_bv_vehiclemfr`(`vehicle_mfr`) USING BTREE,
  INDEX `fk_bv_vtid`(`vehicle_type`) USING BTREE,
  CONSTRAINT `fk_bv_vehiclemfr` FOREIGN KEY (`vehicle_mfr`) REFERENCES `base_manufacturer` (`mfr_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_bv_vtid` FOREIGN KEY (`vehicle_type`) REFERENCES `util_vehicle_type` (`vt_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_vehicle
-- ----------------------------
INSERT INTO `base_vehicle` VALUES (402818952088137728, 'v000001', '桑塔纳01', '桑塔纳', 402756456702685184, 1, 100, 1, 1, '2020-01-17 13:38:42');

-- ----------------------------
-- Table structure for data_vehicle_oil
-- ----------------------------
DROP TABLE IF EXISTS `data_vehicle_oil`;
CREATE TABLE `data_vehicle_oil`  (
  `v_o_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `vehicle_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `oil_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `oil_cost` double NOT NULL,
  `opt_user` int(10) UNSIGNED NULL DEFAULT NULL,
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`v_o_id`) USING BTREE,
  INDEX `fk_svo_vehicle_code`(`vehicle_code`) USING BTREE,
  INDEX `fk_svo_oil_code`(`oil_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for data_vehicle_repair
-- ----------------------------
DROP TABLE IF EXISTS `data_vehicle_repair`;
CREATE TABLE `data_vehicle_repair`  (
  `repair_id` bigint(20) UNSIGNED NOT NULL,
  `vehicle_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `begin_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `repair_cost` double NOT NULL,
  `opt_user` int(10) UNSIGNED NULL DEFAULT NULL,
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`repair_id`) USING BTREE,
  INDEX `fk_svr_vehicle_code`(`vehicle_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_access_oil
-- ----------------------------
DROP TABLE IF EXISTS `system_access_oil`;
CREATE TABLE `system_access_oil`  (
  `access_oil_id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一ID',
  `authority_id` bigint(20) UNSIGNED NOT NULL COMMENT '权限组ID',
  `oil_id` bigint(20) UNSIGNED NOT NULL COMMENT '油卡ID',
  `opt_user` bigint(20) NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`access_oil_id`) USING BTREE,
  UNIQUE INDEX `un_sat_authorityid`(`authority_id`, `oil_id`) USING BTREE,
  INDEX `in_sat_oilid`(`oil_id`) USING BTREE,
  CONSTRAINT `fk_sao_authorityid` FOREIGN KEY (`authority_id`) REFERENCES `system_authority` (`authority_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sao_oilid` FOREIGN KEY (`oil_id`) REFERENCES `base_oil` (`oil_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_access_oil
-- ----------------------------
INSERT INTO `system_access_oil` VALUES (402759687063351296, 2, 402757325947023360, 1, '2020-01-17 09:43:12');
INSERT INTO `system_access_oil` VALUES (402776824863666176, 1, 402757325947023360, 1, '2020-01-17 10:51:18');
INSERT INTO `system_access_oil` VALUES (402776825346011136, 1, 402758182243545088, 1, '2020-01-17 10:51:18');
INSERT INTO `system_access_oil` VALUES (402776825488617472, 1, 402763966952124416, 1, '2020-01-17 10:51:18');

-- ----------------------------
-- Table structure for system_access_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `system_access_vehicle`;
CREATE TABLE `system_access_vehicle`  (
  `access_vehicle_id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一ID',
  `authority_id` bigint(20) UNSIGNED NOT NULL COMMENT '权限组ID',
  `vehicle_id` bigint(20) UNSIGNED NOT NULL COMMENT '设备ID',
  `opt_user` bigint(20) NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操纵时间',
  PRIMARY KEY (`access_vehicle_id`) USING BTREE,
  UNIQUE INDEX `un_sad_authorityid`(`authority_id`, `vehicle_id`) USING BTREE,
  INDEX `in_sad_deviceid`(`vehicle_id`) USING BTREE,
  CONSTRAINT `fk_sav_authorityid` FOREIGN KEY (`authority_id`) REFERENCES `system_authority` (`authority_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sav_vehicleid` FOREIGN KEY (`vehicle_id`) REFERENCES `base_vehicle` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_access_vehicle
-- ----------------------------
INSERT INTO `system_access_vehicle` VALUES (402819852382908416, 1, 402818952088137728, 1, '2020-01-17 13:42:16');

-- ----------------------------
-- Table structure for system_authority
-- ----------------------------
DROP TABLE IF EXISTS `system_authority`;
CREATE TABLE `system_authority`  (
  `authority_id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一ID',
  `authority_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限组编号',
  `authority_name` varchar(150) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '权限组名称',
  `authority_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限组备注',
  `opt_user` bigint(20) NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`authority_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_authority
-- ----------------------------
INSERT INTO `system_authority` VALUES (1, 'administrator', '管理员', '管理员权限组', NULL, '2019-07-29 11:43:59');
INSERT INTO `system_authority` VALUES (2, 'test', '测试组', '测试组', NULL, '2019-07-30 10:07:37');

-- ----------------------------
-- Table structure for system_authority_detail
-- ----------------------------
DROP TABLE IF EXISTS `system_authority_detail`;
CREATE TABLE `system_authority_detail`  (
  `authority_detail_id` bigint(11) UNSIGNED NOT NULL COMMENT '唯一id',
  `authority_id` bigint(20) UNSIGNED NOT NULL COMMENT '权限组编号',
  `control_id` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '页面ID',
  `deal_type` int(4) UNSIGNED NOT NULL DEFAULT 1 COMMENT '处理方式：1：可用；2：不可用',
  `check_status` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '勾选状态',
  `opt_user` bigint(20) NULL DEFAULT NULL,
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`authority_detail_id`) USING BTREE,
  INDEX `fk_saud_authorityid`(`authority_id`) USING BTREE,
  CONSTRAINT `fk_saud_authorityid` FOREIGN KEY (`authority_id`) REFERENCES `system_authority` (`authority_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_authority_detail
-- ----------------------------
INSERT INTO `system_authority_detail` VALUES (1, 1, 1, 1, 'checked', 1, '2019-07-29 11:44:34');
INSERT INTO `system_authority_detail` VALUES (2, 1, 7, 1, 'checked', 1, '2019-07-30 10:06:43');
INSERT INTO `system_authority_detail` VALUES (3, 2, 1, 1, 'checked', 343424007791583232, '2019-07-30 10:08:10');
INSERT INTO `system_authority_detail` VALUES (4, 1, 2, 1, 'checked', 1, '2019-07-30 14:55:40');
INSERT INTO `system_authority_detail` VALUES (5, 1, 3, 1, 'checked', 1, '2019-07-30 14:56:18');
INSERT INTO `system_authority_detail` VALUES (6, 1, 4, 1, 'checked', 1, '2019-07-30 14:56:31');
INSERT INTO `system_authority_detail` VALUES (7, 1, 5, 1, 'checked', 1, '2019-07-30 14:56:45');
INSERT INTO `system_authority_detail` VALUES (8, 1, 6, 1, 'checked', 1, '2019-07-30 14:56:54');
INSERT INTO `system_authority_detail` VALUES (9, 1, 8, 1, 'checked', 1, '2019-07-30 14:57:02');
INSERT INTO `system_authority_detail` VALUES (10, 1, 9, 1, 'checked', 1, '2019-07-31 09:22:54');
INSERT INTO `system_authority_detail` VALUES (11, 1, 10, 1, 'checked', 1, '2019-07-31 11:36:59');
INSERT INTO `system_authority_detail` VALUES (343334767309234176, 2, 2, 2, 'checked', 343424007791583232, '2019-08-06 10:07:45');
INSERT INTO `system_authority_detail` VALUES (343334767594446848, 2, 3, 2, 'checked', 343424007791583232, '2019-08-06 10:07:45');
INSERT INTO `system_authority_detail` VALUES (343334767816744960, 2, 4, 2, 'checked', 343424007791583232, '2019-08-06 10:07:45');
INSERT INTO `system_authority_detail` VALUES (343334768093569024, 2, 5, 2, 'checked', 343424007791583232, '2019-08-06 10:07:45');
INSERT INTO `system_authority_detail` VALUES (343334768265535488, 2, 6, 2, 'checked', 343424007791583232, '2019-08-06 10:07:45');
INSERT INTO `system_authority_detail` VALUES (343334768471056384, 2, 7, 2, 'checked', 343424007791583232, '2019-08-06 10:07:45');
INSERT INTO `system_authority_detail` VALUES (343755626003963904, 2, 9, 2, 'checked', 1, '2019-08-07 14:00:05');
INSERT INTO `system_authority_detail` VALUES (343775646813659136, 2, 10, 2, 'checked', 343424007791583232, '2019-08-07 15:19:38');
INSERT INTO `system_authority_detail` VALUES (345517415377018880, 1, 11, 1, 'checked', 1, '2019-08-12 10:40:47');
INSERT INTO `system_authority_detail` VALUES (353099944871206912, 1, 12, 1, 'checked', 1, '2019-09-02 08:50:48');

-- ----------------------------
-- Table structure for system_authority_user
-- ----------------------------
DROP TABLE IF EXISTS `system_authority_user`;
CREATE TABLE `system_authority_user`  (
  `authority_user_id` bigint(11) UNSIGNED NOT NULL COMMENT '唯一ID',
  `authority_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联权限组CODE',
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '关联用户ID',
  `opt_user` bigint(20) NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`authority_user_id`) USING BTREE,
  UNIQUE INDEX `un_sau_authorityid`(`authority_id`, `user_id`) USING BTREE,
  INDEX `in_sau_userid`(`user_id`) USING BTREE,
  CONSTRAINT `fk_sau_authorityid` FOREIGN KEY (`authority_id`) REFERENCES `system_authority` (`authority_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_sau_userid` FOREIGN KEY (`user_id`) REFERENCES `system_users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_authority_user
-- ----------------------------
INSERT INTO `system_authority_user` VALUES (356403687859560448, 1, 1, 343424007791583232, '2019-09-11 11:38:37');

-- ----------------------------
-- Table structure for system_page
-- ----------------------------
DROP TABLE IF EXISTS `system_page`;
CREATE TABLE `system_page`  (
  `page_id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一id',
  `page_name_en` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '英文名称',
  `page_name_cn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '中文名称',
  `page_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面备注',
  `page_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '页面路径',
  `page_icon` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '页面图标',
  PRIMARY KEY (`page_id`) USING BTREE,
  UNIQUE INDEX `un_sp_moduleid`(`page_name_en`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_page
-- ----------------------------
INSERT INTO `system_page` VALUES (1, 'HomePage', '首页', NULL, 'main.html', '&#xe68e;');
INSERT INTO `system_page` VALUES (2, 'ChartPage', '数据展示', NULL, 'page/data/dataChart.html', '&#xe667;');
INSERT INTO `system_page` VALUES (3, 'OilManagement', '油卡管理', NULL, 'page/oil/oilList.html', '&#xe62c;');
INSERT INTO `system_page` VALUES (4, 'VehicleManagement', '车辆管理', NULL, 'page/vehicle/vehicleList.html', '&#xe631;');
INSERT INTO `system_page` VALUES (5, 'VehicleTypeManagement', '车辆类型', NULL, 'page/vehicleType/vehicleTypeList.html', '&#xe632;');
INSERT INTO `system_page` VALUES (6, 'DriverManagement', '驾驶员管理', NULL, 'page/driver/driverList.html', '&#xe620;');
INSERT INTO `system_page` VALUES (7, 'UserManagement', '用户管理', NULL, 'page/user/userList.html', '&#xe770;');
INSERT INTO `system_page` VALUES (8, 'ManufacturerManagement', '厂商管理', NULL, 'page/manufacturer/manufacturerList.html', '&#xe614;');
INSERT INTO `system_page` VALUES (9, 'AuthorityManagement', '权限管理', NULL, 'page/authority/authorityList.html', '&#xe672;');
INSERT INTO `system_page` VALUES (10, 'PowerConfig', '权限配置', '', 'page/power/powerConfig.html', '&#xe679;');
INSERT INTO `system_page` VALUES (11, 'NewsPage', '公告编辑', NULL, 'page/news/newsList.html', '&#xe667;');

-- ----------------------------
-- Table structure for system_users
-- ----------------------------
DROP TABLE IF EXISTS `system_users`;
CREATE TABLE `system_users`  (
  `user_id` bigint(20) UNSIGNED NOT NULL COMMENT '唯一ID',
  `user_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编码',
  `user_password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `user_tel` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `user_email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮件地址',
  `user_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',
  `user_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户备注',
  `super_flag` tinyint(1) UNSIGNED NULL DEFAULT 0 COMMENT '管理用户(0:否;1:是)',
  `opt_user` bigint(20) NULL DEFAULT NULL COMMENT '操作者',
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_users
-- ----------------------------
INSERT INTO `system_users` VALUES (1, 'admin', 'd033e22ae348aeb5660fc2140aec35850c4da997', '管理员', '', '', 0, NULL, 0, 0, '2019-08-06 16:05:55');
INSERT INTO `system_users` VALUES (402824756484780032, 'test', '3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d', 'test', '', '', 1, 'test stop', 0, 1, '2020-01-17 14:01:46');

-- ----------------------------
-- Table structure for util_vehicle_driver
-- ----------------------------
DROP TABLE IF EXISTS `util_vehicle_driver`;
CREATE TABLE `util_vehicle_driver`  (
  `d_v_id` bigint(20) UNSIGNED NOT NULL,
  `driver_id` bigint(20) UNSIGNED NOT NULL,
  `vehicle_id` bigint(20) UNSIGNED NOT NULL,
  `begin_time` datetime(0) NOT NULL,
  `end_time` datetime(0) NOT NULL,
  `total_cost` double NOT NULL,
  `opt_user` int(11) UNSIGNED NULL DEFAULT NULL,
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`d_v_id`) USING BTREE,
  INDEX `fk_uvd_driverid`(`driver_id`) USING BTREE,
  INDEX `fk_uvd_vehicleid`(`vehicle_id`) USING BTREE,
  CONSTRAINT `fk_uvd_driverid` FOREIGN KEY (`driver_id`) REFERENCES `base_driver` (`driver_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_uvd_vehicleid` FOREIGN KEY (`vehicle_id`) REFERENCES `base_vehicle` (`vehicle_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for util_vehicle_type
-- ----------------------------
DROP TABLE IF EXISTS `util_vehicle_type`;
CREATE TABLE `util_vehicle_type`  (
  `vt_id` bigint(20) UNSIGNED NOT NULL,
  `vt_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型编码',
  `vt_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类型名称',
  `vt_type` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `opt_user` bigint(20) UNSIGNED NULL DEFAULT NULL,
  `opt_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`vt_id`) USING BTREE,
  INDEX `type_code`(`vt_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of util_vehicle_type
-- ----------------------------
INSERT INTO `util_vehicle_type` VALUES (1, 'vt000001', '小型车', 0, 1, '2020-01-17 11:38:52');
INSERT INTO `util_vehicle_type` VALUES (402790331491430400, 'vt000002', '中型车', 0, 1, '2020-01-17 11:44:58');
INSERT INTO `util_vehicle_type` VALUES (402791318348247040, 'vt000003', '大型车', 0, 1, '2020-01-17 11:48:53');

-- ----------------------------
-- Procedure structure for prStatisticsDeviceDay
-- ----------------------------
DROP PROCEDURE IF EXISTS `prStatisticsDeviceDay`;
delimiter ;;
CREATE DEFINER=`root`@`loalhost` PROCEDURE `prStatisticsDeviceDay`()
BEGIN
	#遍历标识
	DECLARE devicecode VARCHAR(100);
	#遍历标识
	DECLARE thingcode VARCHAR(100);
  -- 遍历数据结束标志
  DECLARE done INT DEFAULT FALSE;
	
	-- 设备游标
	DECLARE _device CURSOR FOR
		(SELECT DISTINCT bd.device_code FROM base_device bd
				WHERE TIMESTAMPDIFF(SECOND,
														(SELECT param_value FROM util_parameter WHERE param_code='stadayupd' LIMIT 0,1),
														NOW())>=0);
	
	-- 监测物游标
	DECLARE _thing CURSOR FOR
		(SELECT thing_code FROM base_thing WHERE statistic_flag = 1);
		
	-- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET done = FALSE;-- FALSE
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;-- TRUE
	

	-- 设置时间
	SET @systime = (SELECT param_value FROM util_parameter WHERE param_code='stadayupd' LIMIT 0,1);
	SET @begintime = CONCAT(DATE(@systime),' ','00:00:00');
	SET @endtime = DATE_ADD(@begintime,INTERVAL 1 DAY);
	SET @nowtime = NOW();

	OPEN _device;
		FETCH NEXT FROM _device INTO devicecode;
		REPEAT
			IF done = FAlSE THEN
				OPEN _thing;
				FETCH NEXT FROM _thing INTO thingcode;
				REPEAT
					SET @existsql = CONCAT('SET @exist = (SELECT COUNT(0) FROM data_statistics_day_',LOWER(thingcode),
											 ' tms WHERE tms.data_type=''2031''
												 AND tms.device_code = ''',devicecode,'''
												 AND DATE(tms.begin_time) = DATE(''',@begintime,'''));');
					PREPARE stmt_exist FROM @existsql;
					EXECUTE stmt_exist;
					DEALLOCATE PREPARE stmt_exist;
					IF done = FAlSE AND @exist=0 THEN
						SET @insertsql = CONCAT('INSERT DELAYED INTO data_statistics_day_',LOWER(thingcode),' (
																							device_code,
																							thing_code,
																							thing_avg,
																							thing_max,
																							thing_min,
																							thing_cou,
																							data_type,
																							update_time,
																							begin_time,
																							end_time,
																							thing_zsavg,
																							thing_zsmax,
																							thing_zsmin,
																							thing_zscou,
																							street_id
																						)
																						SELECT
																							tms.device_code,
																							tms.thing_code,
																							AVG(tms.thing_rtd) AS thing_avg,
																							MAX(tms.thing_rtd) AS thing_max,
																							MIN(tms.thing_rtd) AS thing_min,
																							SUM(tms.thing_cou) AS thing_cou,
																							''2031'' AS data_type,
																							''',@nowtime,''' AS update_time,
																							''',@begintime,''' AS begin_time,
																							''',@endtime,''' AS end_time,
																							AVG(tms.thing_zsrtd) AS thing_zsavg,
																							MAX(tms.thing_zsrtd) AS thing_zsmax,
																							MIN(tms.thing_zsrtd) AS thing_zsmin,
																							SUM(tms.thing_zscou) AS thing_zscou,
																							tms.street_id
																						FROM
																							',devicecode,' tms
																						WHERE
																						tms.data_type = ''',2011,'''
																						AND thing_code = ''',thingcode,'''
																						AND	DATE(tms.rtd_time) = DATE(''',@begintime,''')
																						AND tms.invalid = 0
																						GROUP BY tms.thing_code,tms.street_id');
						PREPARE stmt_insert FROM @insertsql;
						EXECUTE stmt_insert;
						DEALLOCATE PREPARE stmt_insert;
					END IF;
				FETCH NEXT FROM _thing INTO thingcode;
				UNTIL done END REPEAT;
				CLOSE _thing;
				SET done = FALSE;
			END IF;
		FETCH NEXT FROM _device INTO devicecode;
		UNTIL done END REPEAT;
	CLOSE _device;
	
	IF DATE_ADD(CONCAT(DATE(@nowtime),' ','00:00:00'),INTERVAL 1 DAY)>@endtime THEN
		UPDATE util_parameter SET param_value = @endtime WHERE param_code='stadayupd';
	END IF;
		
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for prStatisticsDeviceHour
-- ----------------------------
DROP PROCEDURE IF EXISTS `prStatisticsDeviceHour`;
delimiter ;;
CREATE DEFINER=`root`@`loalhost` PROCEDURE `prStatisticsDeviceHour`()
BEGIN
	#遍历标识
	DECLARE devicecode VARCHAR(100);
	#遍历标识
	DECLARE thingcode VARCHAR(100);
  -- 遍历数据结束标志
  DECLARE done INT DEFAULT FALSE;

	DECLARE _device CURSOR FOR
		(SELECT DISTINCT bd.device_code FROM base_device bd
				WHERE TIMESTAMPDIFF(SECOND,
														(SELECT param_value FROM util_parameter WHERE param_code='stahourupd' LIMIT 0,1),
														NOW())>=0);
														
	-- 监测物游标
	DECLARE _thing CURSOR FOR
		(SELECT thing_code FROM base_thing WHERE statistic_flag = 1);

  -- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET done = FALSE;-- FALSE
  DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;-- TRUE

	-- 设置时间
	SET @systime = (SELECT param_value FROM util_parameter WHERE param_code='stahourupd' LIMIT 0,1);
	SET @begintime = CONCAT(DATE(@systime),' ',LPAD(HOUR(@systime),2,0),':00:00');
	SET @endtime = DATE_ADD(@begintime,INTERVAL 1 HOUR);
	SET @nowtime = NOW();

	OPEN _device;
		FETCH NEXT FROM _device INTO devicecode;
		REPEAT
			IF done = FAlSE THEN
				OPEN _thing;
					FETCH NEXT FROM _thing INTO thingcode;
					REPEAT
						SET @existsql = CONCAT('SET @exist = (SELECT COUNT(0) FROM data_statistics_hour_',LOWER(thingcode),
						 ' tms WHERE tms.data_type=''2061''
							 AND tms.device_code = ''',devicecode,'''
							 AND DATE(tms.begin_time) = DATE(''',@begintime,'''));');
						PREPARE stmt_exist FROM @existsql;
						EXECUTE stmt_exist;
						DEALLOCATE PREPARE stmt_exist;
						IF done = FAlSE AND @exist=0 THEN
							SET @insertsql = CONCAT('INSERT DELAYED INTO data_statistics_hour_',LOWER(thingcode),' (
																								device_code,
																								thing_code,
																								thing_avg,
																								thing_max,
																								thing_min,
																								thing_cou,
																								data_type,
																								update_time,
																								begin_time,
																								end_time,
																								thing_zsavg,
																								thing_zsmax,
																								thing_zsmin,
																								thing_zscou,
																								street_id
																							)
																							SELECT
																								tms.device_code,
																								tms.thing_code,
																								AVG(tms.thing_rtd) AS thing_avg,
																								MAX(tms.thing_rtd) AS thing_max,
																								MIN(tms.thing_rtd) AS thing_min,
																								SUM(tms.thing_cou) AS thing_cou,
																								''2031'' AS data_type,
																								''',@nowtime,''' AS update_time,
																								''',@begintime,''' AS begin_time,
																								''',@endtime,''' AS end_time,
																								AVG(tms.thing_zsrtd) AS thing_zsavg,
																								MAX(tms.thing_zsrtd) AS thing_zsmax,
																								MIN(tms.thing_zsrtd) AS thing_zsmin,
																								SUM(tms.thing_zscou) AS thing_zscou,
																								tms.street_id
																							FROM
																								',devicecode,' tms
																							WHERE
																							tms.data_type = ''',2011,'''
																							AND thing_code = ''',thingcode,'''
																							AND	DATE(tms.rtd_time) = DATE(''',@begintime,''')
																							AND HOUR(tms.rtd_time) = HOUR(''',@begintime,''')
																							AND tms.invalid = 0
																							GROUP BY tms.thing_code,tms.street_id');
							PREPARE stmt_insert FROM @insertsql;
							EXECUTE stmt_insert;
							DEALLOCATE PREPARE stmt_insert;
						END IF;
					FETCH NEXT FROM _thing INTO thingcode;
					UNTIL done END REPEAT;
					CLOSE _thing;
					SET done = FALSE;
			END IF;
		FETCH NEXT FROM _device INTO devicecode;
		UNTIL done END REPEAT;
	CLOSE _device;
	
	IF DATE_ADD(CONCAT(DATE(@nowtime),' ',LPAD(HOUR(@nowtime),2,0),':00:00'),INTERVAL 1 HOUR)>@endtime THEN
		UPDATE util_parameter SET param_value = @endtime WHERE param_code='stahourupd';
	END IF;
		
END
;;
delimiter ;

-- ----------------------------
-- Event structure for evStatisticsDeviceDay
-- ----------------------------
DROP EVENT IF EXISTS `evStatisticsDeviceDay`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` EVENT `evStatisticsDeviceDay`
ON SCHEDULE
EVERY '1' DAY STARTS '2019-08-06 01:00:00'
DO CALL prStatisticsDeviceDay()
;;
delimiter ;

-- ----------------------------
-- Event structure for evStatisticsDeviceHour
-- ----------------------------
DROP EVENT IF EXISTS `evStatisticsDeviceHour`;
delimiter ;;
CREATE DEFINER = `root`@`localhost` EVENT `evStatisticsDeviceHour`
ON SCHEDULE
EVERY '1' HOUR STARTS '2019-08-06 18:15:00'
DO CALL prStatisticsDeviceHour()
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
