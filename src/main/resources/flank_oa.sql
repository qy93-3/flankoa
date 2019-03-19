/*
Navicat MySQL Data Transfer

Source Server         : mysql5.0（root：root）
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : flank_oa

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2019-03-19 08:53:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for holiday_type
-- ----------------------------
DROP TABLE IF EXISTS `holiday_type`;
CREATE TABLE `holiday_type` (
  `id` int(20) NOT NULL auto_increment,
  `type` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of holiday_type
-- ----------------------------
INSERT INTO `holiday_type` VALUES ('1', '年假');
INSERT INTO `holiday_type` VALUES ('2', '事假');
INSERT INTO `holiday_type` VALUES ('3', '病假');
INSERT INTO `holiday_type` VALUES ('4', '婚假');
INSERT INTO `holiday_type` VALUES ('5', '产假及哺乳假');
INSERT INTO `holiday_type` VALUES ('6', '陪产假');
INSERT INTO `holiday_type` VALUES ('7', '丧假');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(20) NOT NULL auto_increment,
  `content` varchar(255) default NULL,
  `create_time` datetime default NULL,
  `author_id` int(20) default NULL,
  `urgency_id` int(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_message_author` (`author_id`),
  KEY `fk_message_urgency` (`urgency_id`),
  CONSTRAINT `fk_message_author` FOREIGN KEY (`author_id`) REFERENCES `oa_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_message_urgency` FOREIGN KEY (`urgency_id`) REFERENCES `oa_urgency` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '123', '2019-03-13 21:01:30', '2', '1');
INSERT INTO `message` VALUES ('2', '今天晚上开会', '2019-03-11 00:51:00', '2', '3');
INSERT INTO `message` VALUES ('3', '今天晚上开会', '2019-03-11 00:51:00', '2', '2');
INSERT INTO `message` VALUES ('4', '今天晚上开会', '2019-03-11 00:51:00', '2', '3');
INSERT INTO `message` VALUES ('5', '今天晚上开会', '2019-03-11 00:51:00', '2', '1');
INSERT INTO `message` VALUES ('6', '今天晚上开会', '2019-03-11 00:51:00', '2', '3');
INSERT INTO `message` VALUES ('9', '今天晚上开会', '2019-03-11 00:51:00', '2', '2');
INSERT INTO `message` VALUES ('11', '13', '2019-03-13 00:00:00', '1', '1');
INSERT INTO `message` VALUES ('16', '', null, '1', '1');
INSERT INTO `message` VALUES ('17', '', '2019-03-11 00:00:00', '1', '1');

-- ----------------------------
-- Table structure for oa_attends
-- ----------------------------
DROP TABLE IF EXISTS `oa_attends`;
CREATE TABLE `oa_attends` (
  `attends_id` int(20) NOT NULL auto_increment,
  `type` varchar(20) default NULL,
  `time` datetime default NULL,
  `status_id` int(20) default NULL,
  `user_id` int(20) default NULL,
  PRIMARY KEY  (`attends_id`),
  KEY `fk_oa_attends_status` (`status_id`),
  KEY `fk_oa_attends_user_id` (`user_id`),
  CONSTRAINT `fk_oa_attends_status` FOREIGN KEY (`status_id`) REFERENCES `oa_status` (`status_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_oa_attends_user_id` FOREIGN KEY (`user_id`) REFERENCES `oa_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_attends
-- ----------------------------
INSERT INTO `oa_attends` VALUES ('1', '上班', '2019-03-15 00:00:00', '1', '3');
INSERT INTO `oa_attends` VALUES ('2', '下班', '2019-03-11 00:00:00', '2', '3');
INSERT INTO `oa_attends` VALUES ('3', '下班', '2019-03-11 00:00:00', '3', '3');
INSERT INTO `oa_attends` VALUES ('37', '下班', '2019-03-18 18:34:41', '3', '1');

-- ----------------------------
-- Table structure for oa_dept
-- ----------------------------
DROP TABLE IF EXISTS `oa_dept`;
CREATE TABLE `oa_dept` (
  `dept_id` int(20) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `fax` varchar(20) default NULL,
  `phone` varchar(20) default NULL,
  `eamil` varchar(20) default NULL,
  PRIMARY KEY  (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_dept
-- ----------------------------
INSERT INTO `oa_dept` VALUES ('1', '总经办', ' 8659288881111', '625841', 'zonjingban@qq.com');
INSERT INTO `oa_dept` VALUES ('2', '市场部', ' 8659288882222', '625842123', 'shichangbu@qq.com');
INSERT INTO `oa_dept` VALUES ('3', '研发部', ' 8659288883333', '625843', 'caiwubu@qq.com');
INSERT INTO `oa_dept` VALUES ('4', '财务部', ' 8659288884444', '625844', 'yanfabu@qq.com');
INSERT INTO `oa_dept` VALUES ('5', '人力资源部', ' 8659288885555', '625845', 'renshibu@qq.com');
INSERT INTO `oa_dept` VALUES ('6', '123', '8659288889999', '15638509425', '123@qq.com');

-- ----------------------------
-- Table structure for oa_evection
-- ----------------------------
DROP TABLE IF EXISTS `oa_evection`;
CREATE TABLE `oa_evection` (
  `evection_id` int(20) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `end_time` datetime default NULL,
  `start_time` datetime default NULL,
  `file_path` varchar(255) default NULL,
  `evection_cause` varchar(255) default NULL,
  `urgency_id` int(20) default NULL,
  `type_id` int(20) default NULL,
  `status_id` int(20) default NULL,
  `submit_tiem` datetime default NULL,
  `evection_user` int(20) default NULL,
  `look_user_id` int(20) default NULL,
  PRIMARY KEY  (`evection_id`),
  KEY `fk_evection_urgency_id` (`urgency_id`),
  KEY `fk_evection_type_id` (`type_id`),
  KEY `fk_evection_user` (`evection_user`),
  KEY `fk_evection_look_user` (`look_user_id`),
  KEY `fk_evection_through` (`status_id`),
  CONSTRAINT `fk_evection_look_user` FOREIGN KEY (`look_user_id`) REFERENCES `oa_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_evection_through` FOREIGN KEY (`status_id`) REFERENCES `through` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evection_type_id` FOREIGN KEY (`type_id`) REFERENCES `oa_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evection_urgency_id` FOREIGN KEY (`urgency_id`) REFERENCES `oa_urgency` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_evection_user` FOREIGN KEY (`evection_user`) REFERENCES `oa_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_evection
-- ----------------------------
INSERT INTO `oa_evection` VALUES ('1', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('2', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('3', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('4', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('5', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('6', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '2', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('7', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('8', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('9', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '2', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('10', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('11', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('12', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '2', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('13', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('14', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('15', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');
INSERT INTO `oa_evection` VALUES ('16', '考察机器', null, '2019-03-11 08:26:04', null, '因公需到实地考察机器', '1', '3', '1', '2019-03-16 09:07:39', '1', '1');

-- ----------------------------
-- Table structure for oa_holiday
-- ----------------------------
DROP TABLE IF EXISTS `oa_holiday`;
CREATE TABLE `oa_holiday` (
  `holiday_id` int(20) NOT NULL auto_increment,
  `cause` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `start_time` datetime default NULL,
  `days` int(20) default NULL,
  `file_path` varchar(255) default NULL,
  `urgency_id` int(20) default NULL,
  `end_time` datetime default NULL,
  `submit_time` datetime default NULL,
  `holiday_type` int(20) default NULL,
  `status` int(20) default NULL,
  `holiday_user` int(20) default NULL,
  `look_user` int(20) default NULL,
  PRIMARY KEY  (`holiday_id`),
  KEY `fk_holiday_type` (`holiday_type`),
  KEY `fk_holiday_status` (`status`),
  KEY `fk_holiday_user` (`holiday_user`),
  KEY `fk_holiday_look_user` (`look_user`),
  KEY `fk_holiday_urgency` (`urgency_id`),
  CONSTRAINT `fk_holiday_look_user` FOREIGN KEY (`look_user`) REFERENCES `oa_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_holiday_status` FOREIGN KEY (`status`) REFERENCES `through` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_holiday_type` FOREIGN KEY (`holiday_type`) REFERENCES `holiday_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_holiday_urgency` FOREIGN KEY (`urgency_id`) REFERENCES `oa_urgency` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_holiday_user` FOREIGN KEY (`holiday_user`) REFERENCES `oa_user` (`user_id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_holiday
-- ----------------------------
INSERT INTO `oa_holiday` VALUES ('1', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '2', '3', '2');
INSERT INTO `oa_holiday` VALUES ('2', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '2', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('3', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '2', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('4', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '2', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('5', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '2', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '2', '3', '2');
INSERT INTO `oa_holiday` VALUES ('6', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('7', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('8', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '3', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '2', '3', '2');
INSERT INTO `oa_holiday` VALUES ('9', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('10', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('11', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '3', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('12', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('13', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('14', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '3', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '2', '3', '2');
INSERT INTO `oa_holiday` VALUES ('15', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('16', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('17', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('18', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '2', '3', '2');
INSERT INTO `oa_holiday` VALUES ('19', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('20', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '2', '3', '2');
INSERT INTO `oa_holiday` VALUES ('21', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');
INSERT INTO `oa_holiday` VALUES ('22', '回家探亲', '探亲假', '2019-03-11 08:27:39', '2', null, '1', '2019-03-13 08:27:47', '2019-03-11 08:31:31', '2', '1', '3', '2');

-- ----------------------------
-- Table structure for oa_menu
-- ----------------------------
DROP TABLE IF EXISTS `oa_menu`;
CREATE TABLE `oa_menu` (
  `menu_id` int(20) NOT NULL auto_increment,
  `pid` int(20) default NULL,
  `content` varchar(20) default NULL,
  `create_date` datetime default NULL,
  `href` varchar(20) default NULL,
  PRIMARY KEY  (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_menu
-- ----------------------------
INSERT INTO `oa_menu` VALUES ('1', '0', '公告管理', '2019-03-07 12:54:43', null);
INSERT INTO `oa_menu` VALUES ('2', '0', '考勤管理', '2019-03-07 12:54:56', null);
INSERT INTO `oa_menu` VALUES ('3', '0', '个人管理', '2019-03-07 12:55:07', null);
INSERT INTO `oa_menu` VALUES ('4', '0', '流程管理', '2019-03-07 12:55:21', null);
INSERT INTO `oa_menu` VALUES ('5', '0', '系统管理', '2019-03-07 12:55:32', null);
INSERT INTO `oa_menu` VALUES ('6', '1', '通知管理', '2019-03-07 12:56:04', null);
INSERT INTO `oa_menu` VALUES ('7', '1', '通知列表', '2019-03-07 12:56:15', null);
INSERT INTO `oa_menu` VALUES ('8', '2', '我的考勤', '2019-03-07 12:56:28', null);
INSERT INTO `oa_menu` VALUES ('9', '2', '考勤周报表', '2019-03-07 12:56:40', null);
INSERT INTO `oa_menu` VALUES ('10', '2', '考勤月报表', '2019-03-07 12:56:49', null);
INSERT INTO `oa_menu` VALUES ('11', '3', '邮件管理', '2019-03-07 12:57:05', null);
INSERT INTO `oa_menu` VALUES ('12', '3', '我的日历', '2019-03-07 12:57:25', null);
INSERT INTO `oa_menu` VALUES ('13', '3', '通讯录', '2019-03-07 12:57:36', null);
INSERT INTO `oa_menu` VALUES ('14', '4', '新建流程', '2019-03-07 12:57:52', null);
INSERT INTO `oa_menu` VALUES ('15', '4', '我的申请', '2019-03-07 12:58:04', null);
INSERT INTO `oa_menu` VALUES ('16', '4', '流程审核', '2019-03-07 12:58:14', null);
INSERT INTO `oa_menu` VALUES ('17', '5', '菜单管理', '2019-03-07 12:58:28', null);
INSERT INTO `oa_menu` VALUES ('18', '5', '部门管理', '2019-03-07 12:58:38', null);
INSERT INTO `oa_menu` VALUES ('19', '5', '职位管理', '2019-03-07 12:58:47', null);
INSERT INTO `oa_menu` VALUES ('20', '5', '用户管理', '2019-03-07 12:59:01', null);
INSERT INTO `oa_menu` VALUES ('21', '5', '角色管理', '2019-03-07 12:59:11', null);

-- ----------------------------
-- Table structure for oa_phone
-- ----------------------------
DROP TABLE IF EXISTS `oa_phone`;
CREATE TABLE `oa_phone` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  `sex` varchar(20) default NULL,
  `tel` varchar(20) default NULL,
  `email` varchar(20) default NULL,
  `company` varchar(20) default NULL,
  `type` varchar(20) default NULL,
  `user_id` int(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_phone_user` (`user_id`),
  KEY `fk_phone_type123` (`type`),
  CONSTRAINT `fk_phone_user` FOREIGN KEY (`user_id`) REFERENCES `oa_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_phone
-- ----------------------------
INSERT INTO `oa_phone` VALUES ('1', '小明', '男', '15638808975', 'xiaoming@qq.com', '阿里', '外部通讯录', '1');
INSERT INTO `oa_phone` VALUES ('2', '真aa', '女', '15638808351', 'aa@qq,com', '管理员', '内部通讯录', '1');
INSERT INTO `oa_phone` VALUES ('3', '真bb', '男', '15638808352', 'bb@qq.com', '市场部', '内部通讯录', '2');
INSERT INTO `oa_phone` VALUES ('4', '真cc', '女', '15638808353', 'cc@qq.com', '市场部', '内部通讯录', '3');
INSERT INTO `oa_phone` VALUES ('5', '真dd', '女', '15638808353', 'dd@qq.com', '市场部', '内部通讯录', '4');

-- ----------------------------
-- Table structure for oa_role
-- ----------------------------
DROP TABLE IF EXISTS `oa_role`;
CREATE TABLE `oa_role` (
  `role_id` int(20) NOT NULL auto_increment,
  `role_name` varchar(20) default NULL,
  PRIMARY KEY  (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role
-- ----------------------------
INSERT INTO `oa_role` VALUES ('1', '管理员');
INSERT INTO `oa_role` VALUES ('2', '公司高管');
INSERT INTO `oa_role` VALUES ('3', '部门经理');
INSERT INTO `oa_role` VALUES ('4', '公告编辑人员');
INSERT INTO `oa_role` VALUES ('5', '员工');

-- ----------------------------
-- Table structure for oa_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `oa_role_menu`;
CREATE TABLE `oa_role_menu` (
  `role_id` int(20) NOT NULL,
  `menu_id` int(20) NOT NULL,
  PRIMARY KEY  (`role_id`,`menu_id`),
  KEY `fk_oa_menu_id` (`menu_id`),
  CONSTRAINT `fk_oa_menu_id` FOREIGN KEY (`menu_id`) REFERENCES `oa_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_oa_role_id` FOREIGN KEY (`role_id`) REFERENCES `oa_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_role_menu
-- ----------------------------
INSERT INTO `oa_role_menu` VALUES ('1', '1');
INSERT INTO `oa_role_menu` VALUES ('2', '1');
INSERT INTO `oa_role_menu` VALUES ('3', '1');
INSERT INTO `oa_role_menu` VALUES ('4', '1');
INSERT INTO `oa_role_menu` VALUES ('5', '1');
INSERT INTO `oa_role_menu` VALUES ('1', '2');
INSERT INTO `oa_role_menu` VALUES ('2', '2');
INSERT INTO `oa_role_menu` VALUES ('3', '2');
INSERT INTO `oa_role_menu` VALUES ('4', '2');
INSERT INTO `oa_role_menu` VALUES ('5', '2');
INSERT INTO `oa_role_menu` VALUES ('1', '3');
INSERT INTO `oa_role_menu` VALUES ('2', '3');
INSERT INTO `oa_role_menu` VALUES ('3', '3');
INSERT INTO `oa_role_menu` VALUES ('4', '3');
INSERT INTO `oa_role_menu` VALUES ('5', '3');
INSERT INTO `oa_role_menu` VALUES ('1', '4');
INSERT INTO `oa_role_menu` VALUES ('2', '4');
INSERT INTO `oa_role_menu` VALUES ('3', '4');
INSERT INTO `oa_role_menu` VALUES ('4', '4');
INSERT INTO `oa_role_menu` VALUES ('5', '4');
INSERT INTO `oa_role_menu` VALUES ('1', '5');
INSERT INTO `oa_role_menu` VALUES ('2', '5');
INSERT INTO `oa_role_menu` VALUES ('3', '5');
INSERT INTO `oa_role_menu` VALUES ('4', '5');
INSERT INTO `oa_role_menu` VALUES ('5', '5');
INSERT INTO `oa_role_menu` VALUES ('1', '6');
INSERT INTO `oa_role_menu` VALUES ('2', '6');
INSERT INTO `oa_role_menu` VALUES ('3', '6');
INSERT INTO `oa_role_menu` VALUES ('4', '6');
INSERT INTO `oa_role_menu` VALUES ('5', '6');
INSERT INTO `oa_role_menu` VALUES ('1', '7');
INSERT INTO `oa_role_menu` VALUES ('2', '7');
INSERT INTO `oa_role_menu` VALUES ('3', '7');
INSERT INTO `oa_role_menu` VALUES ('4', '7');
INSERT INTO `oa_role_menu` VALUES ('5', '7');
INSERT INTO `oa_role_menu` VALUES ('1', '8');
INSERT INTO `oa_role_menu` VALUES ('2', '8');
INSERT INTO `oa_role_menu` VALUES ('3', '8');
INSERT INTO `oa_role_menu` VALUES ('4', '8');
INSERT INTO `oa_role_menu` VALUES ('5', '8');
INSERT INTO `oa_role_menu` VALUES ('1', '9');
INSERT INTO `oa_role_menu` VALUES ('2', '9');
INSERT INTO `oa_role_menu` VALUES ('3', '9');
INSERT INTO `oa_role_menu` VALUES ('4', '9');
INSERT INTO `oa_role_menu` VALUES ('5', '9');
INSERT INTO `oa_role_menu` VALUES ('1', '10');
INSERT INTO `oa_role_menu` VALUES ('2', '10');
INSERT INTO `oa_role_menu` VALUES ('3', '10');
INSERT INTO `oa_role_menu` VALUES ('4', '10');
INSERT INTO `oa_role_menu` VALUES ('5', '10');
INSERT INTO `oa_role_menu` VALUES ('1', '11');
INSERT INTO `oa_role_menu` VALUES ('2', '11');
INSERT INTO `oa_role_menu` VALUES ('3', '11');
INSERT INTO `oa_role_menu` VALUES ('4', '11');
INSERT INTO `oa_role_menu` VALUES ('5', '11');
INSERT INTO `oa_role_menu` VALUES ('1', '12');
INSERT INTO `oa_role_menu` VALUES ('2', '12');
INSERT INTO `oa_role_menu` VALUES ('3', '12');
INSERT INTO `oa_role_menu` VALUES ('4', '12');
INSERT INTO `oa_role_menu` VALUES ('5', '12');
INSERT INTO `oa_role_menu` VALUES ('1', '13');
INSERT INTO `oa_role_menu` VALUES ('2', '13');
INSERT INTO `oa_role_menu` VALUES ('3', '13');
INSERT INTO `oa_role_menu` VALUES ('4', '13');
INSERT INTO `oa_role_menu` VALUES ('5', '13');
INSERT INTO `oa_role_menu` VALUES ('1', '14');
INSERT INTO `oa_role_menu` VALUES ('2', '14');
INSERT INTO `oa_role_menu` VALUES ('3', '14');
INSERT INTO `oa_role_menu` VALUES ('4', '14');
INSERT INTO `oa_role_menu` VALUES ('5', '14');
INSERT INTO `oa_role_menu` VALUES ('1', '15');
INSERT INTO `oa_role_menu` VALUES ('2', '15');
INSERT INTO `oa_role_menu` VALUES ('3', '15');
INSERT INTO `oa_role_menu` VALUES ('4', '15');
INSERT INTO `oa_role_menu` VALUES ('5', '15');
INSERT INTO `oa_role_menu` VALUES ('1', '16');
INSERT INTO `oa_role_menu` VALUES ('2', '16');
INSERT INTO `oa_role_menu` VALUES ('3', '16');
INSERT INTO `oa_role_menu` VALUES ('4', '16');
INSERT INTO `oa_role_menu` VALUES ('5', '16');
INSERT INTO `oa_role_menu` VALUES ('1', '17');
INSERT INTO `oa_role_menu` VALUES ('2', '17');
INSERT INTO `oa_role_menu` VALUES ('4', '17');
INSERT INTO `oa_role_menu` VALUES ('5', '17');
INSERT INTO `oa_role_menu` VALUES ('1', '18');
INSERT INTO `oa_role_menu` VALUES ('1', '19');
INSERT INTO `oa_role_menu` VALUES ('1', '20');
INSERT INTO `oa_role_menu` VALUES ('1', '21');

-- ----------------------------
-- Table structure for oa_send_email_record
-- ----------------------------
DROP TABLE IF EXISTS `oa_send_email_record`;
CREATE TABLE `oa_send_email_record` (
  `id` int(20) NOT NULL auto_increment,
  `send_id` int(20) default NULL,
  `recipient_id` int(20) default NULL,
  `title` varchar(255) default NULL,
  `send_time` datetime default NULL,
  `appendix_address` varchar(255) default NULL,
  `box` varchar(20) default NULL,
  PRIMARY KEY  (`id`),
  KEY `fk_email_recipient` (`recipient_id`),
  KEY `fk_email_send` (`send_id`),
  CONSTRAINT `fk_email_recipient` FOREIGN KEY (`recipient_id`) REFERENCES `oa_phone` (`id`),
  CONSTRAINT `fk_email_send` FOREIGN KEY (`send_id`) REFERENCES `oa_phone` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_send_email_record
-- ----------------------------
INSERT INTO `oa_send_email_record` VALUES ('1', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', '撒旦法', '收件箱');
INSERT INTO `oa_send_email_record` VALUES ('2', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', '撒旦法', '收件箱');
INSERT INTO `oa_send_email_record` VALUES ('4', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', '撒旦法', '发件箱');
INSERT INTO `oa_send_email_record` VALUES ('5', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', '阿斯顿发大飒飒的沙发', '收件箱');
INSERT INTO `oa_send_email_record` VALUES ('6', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', '阿凡达', '收件箱');
INSERT INTO `oa_send_email_record` VALUES ('8', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', null, '垃圾箱');
INSERT INTO `oa_send_email_record` VALUES ('9', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', null, '垃圾箱');
INSERT INTO `oa_send_email_record` VALUES ('10', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', null, '垃圾箱');
INSERT INTO `oa_send_email_record` VALUES ('11', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', null, '草稿箱');
INSERT INTO `oa_send_email_record` VALUES ('12', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', null, '草稿箱');
INSERT INTO `oa_send_email_record` VALUES ('13', '2', '1', '这周项目进度表', '2019-03-14 15:13:49', null, '草稿箱');

-- ----------------------------
-- Table structure for oa_status
-- ----------------------------
DROP TABLE IF EXISTS `oa_status`;
CREATE TABLE `oa_status` (
  `status_id` int(20) NOT NULL auto_increment,
  `status_name` varchar(20) default NULL,
  PRIMARY KEY  (`status_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_status
-- ----------------------------
INSERT INTO `oa_status` VALUES ('1', '迟到');
INSERT INTO `oa_status` VALUES ('2', '早退');
INSERT INTO `oa_status` VALUES ('3', '准时');

-- ----------------------------
-- Table structure for oa_time
-- ----------------------------
DROP TABLE IF EXISTS `oa_time`;
CREATE TABLE `oa_time` (
  `time_id` int(20) NOT NULL auto_increment,
  `time` time default NULL,
  `type` varchar(20) default NULL,
  `season` varchar(20) default NULL,
  PRIMARY KEY  (`time_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_time
-- ----------------------------
INSERT INTO `oa_time` VALUES ('1', '08:00:00', '上班', '夏季');
INSERT INTO `oa_time` VALUES ('2', '17:00:00', '下班', '夏季');
INSERT INTO `oa_time` VALUES ('3', '09:00:00', '上班', '冬季');
INSERT INTO `oa_time` VALUES ('4', '18:00:00', '下班', '冬季');

-- ----------------------------
-- Table structure for oa_type
-- ----------------------------
DROP TABLE IF EXISTS `oa_type`;
CREATE TABLE `oa_type` (
  `id` int(20) NOT NULL auto_increment,
  `type` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_type
-- ----------------------------
INSERT INTO `oa_type` VALUES ('1', '销售拜访');
INSERT INTO `oa_type` VALUES ('2', '售前支持');
INSERT INTO `oa_type` VALUES ('3', '项目支持');
INSERT INTO `oa_type` VALUES ('4', '其他');
INSERT INTO `oa_type` VALUES ('5', '客服外出');

-- ----------------------------
-- Table structure for oa_urgency
-- ----------------------------
DROP TABLE IF EXISTS `oa_urgency`;
CREATE TABLE `oa_urgency` (
  `id` int(20) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_urgency
-- ----------------------------
INSERT INTO `oa_urgency` VALUES ('1', '正常');
INSERT INTO `oa_urgency` VALUES ('2', '重要');
INSERT INTO `oa_urgency` VALUES ('3', '紧急');

-- ----------------------------
-- Table structure for oa_user
-- ----------------------------
DROP TABLE IF EXISTS `oa_user`;
CREATE TABLE `oa_user` (
  `user_id` int(20) NOT NULL auto_increment,
  `user_name` varchar(20) default NULL,
  `password` varchar(20) default NULL,
  `sex` varchar(20) default NULL,
  `birth` datetime default NULL,
  `join_time` datetime default NULL,
  `phone` varchar(20) default NULL,
  `email` varchar(20) default NULL,
  `dept_id` int(20) default NULL,
  `real_name` varchar(20) default NULL,
  PRIMARY KEY  (`user_id`),
  KEY `fk_oa_dept_id` (`dept_id`),
  CONSTRAINT `fk_oa_dept_id` FOREIGN KEY (`dept_id`) REFERENCES `oa_dept` (`dept_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_user
-- ----------------------------
INSERT INTO `oa_user` VALUES ('1', 'aa', 'aa', '女', '1996-03-11 07:46:25', '2011-07-01 07:46:42', '15638808351', 'aa@qq.com', '1', '真aa');
INSERT INTO `oa_user` VALUES ('2', 'bb', 'bb', '男', '1996-08-01 07:57:33', '2014-03-11 07:57:48', '15638808352', 'bb@qq.com', '3', '真bb');
INSERT INTO `oa_user` VALUES ('3', 'cc', 'cc', '女', '2000-03-11 07:58:39', '2017-03-11 07:58:42', '15638808353', 'cc@qq.com', '2', '真cc');
INSERT INTO `oa_user` VALUES ('4', 'dd', 'dd', '女', '2000-04-11 07:58:39', '2017-03-11 07:59:54', '15638808354', 'dd@qq.com', '2', '真dd');
INSERT INTO `oa_user` VALUES ('5', 'ee', 'ee', '男', '2000-04-12 07:58:39', '2017-04-11 07:59:54', '15638808355', 'ee@qq.com', '3', '真ee');
INSERT INTO `oa_user` VALUES ('6', 'ff', 'ff', '男', '2000-04-13 07:58:39', '2017-04-21 07:59:54', '15638808356', 'ff@qq.com', '3', '真ff');
INSERT INTO `oa_user` VALUES ('7', 'gg', 'gg', '男', '2010-04-13 07:58:39', '2017-04-22 07:59:54', '15638808357', 'gg@qq.com', '4', '真gg');
INSERT INTO `oa_user` VALUES ('8', 'hh', 'hh', '男', '2010-04-14 07:58:39', '2017-04-23 07:59:54', '15638808358', 'hh@qq.com', '4', '真hh');
INSERT INTO `oa_user` VALUES ('9', 'ii', 'ii', '男', '2011-04-13 07:58:39', '2017-04-22 07:59:54', '15638808359', 'ii@qq.com', '5', '真ii');
INSERT INTO `oa_user` VALUES ('10', 'jj', 'jj', '男', '2010-04-16 07:58:39', '2017-04-24 07:59:54', '15638808360', 'jj@qq.com', '3', '真jj');
INSERT INTO `oa_user` VALUES ('11', 'xx', 'xx', '男', '1980-04-16 07:58:39', '2000-04-24 07:59:54', '15638808361', 'xx@qq.com', '1', '真xx');
INSERT INTO `oa_user` VALUES ('12', 'yy', 'yy', '男', '2010-04-16 07:58:39', '2017-04-29 07:59:54', '15638808362', 'yy@qq.com', '1', '真yy');

-- ----------------------------
-- Table structure for oa_user_role
-- ----------------------------
DROP TABLE IF EXISTS `oa_user_role`;
CREATE TABLE `oa_user_role` (
  `uer_id` int(20) NOT NULL,
  `role_id` int(20) NOT NULL,
  PRIMARY KEY  (`uer_id`,`role_id`),
  KEY `fk_oa_user_role_id` (`role_id`),
  CONSTRAINT `fk_oa_user_role_id` FOREIGN KEY (`role_id`) REFERENCES `oa_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_oa_user_user_id` FOREIGN KEY (`uer_id`) REFERENCES `oa_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oa_user_role
-- ----------------------------
INSERT INTO `oa_user_role` VALUES ('1', '1');
INSERT INTO `oa_user_role` VALUES ('11', '2');
INSERT INTO `oa_user_role` VALUES ('2', '3');
INSERT INTO `oa_user_role` VALUES ('5', '3');
INSERT INTO `oa_user_role` VALUES ('7', '3');
INSERT INTO `oa_user_role` VALUES ('9', '3');
INSERT INTO `oa_user_role` VALUES ('12', '4');
INSERT INTO `oa_user_role` VALUES ('3', '5');
INSERT INTO `oa_user_role` VALUES ('4', '5');
INSERT INTO `oa_user_role` VALUES ('6', '5');
INSERT INTO `oa_user_role` VALUES ('8', '5');
INSERT INTO `oa_user_role` VALUES ('10', '5');

-- ----------------------------
-- Table structure for through
-- ----------------------------
DROP TABLE IF EXISTS `through`;
CREATE TABLE `through` (
  `id` int(20) NOT NULL auto_increment,
  `name` varchar(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of through
-- ----------------------------
INSERT INTO `through` VALUES ('1', '通过');
INSERT INTO `through` VALUES ('2', '未通过');
SET FOREIGN_KEY_CHECKS=1;
