/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50533
Source Host           : localhost:3306
Source Database       : cachet

Target Server Type    : MYSQL
Target Server Version : 50533
File Encoding         : 65001

Date: 2017-11-17 16:38:52
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cert
-- ----------------------------
DROP TABLE IF EXISTS `cert`;
CREATE TABLE `cert` (
  `CERT_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CERT_NAME` varchar(20) CHARACTER SET gb2312 DEFAULT NULL COMMENT '证件名称',
  `CERT_TYPE` varchar(20) DEFAULT NULL COMMENT '证件类型（1：身份证，2：警官证，3：电子签名，4：公章）',
  `CERT_URL1` varchar(255) DEFAULT NULL COMMENT '证件存放位置1',
  `CERT_URL2` varchar(255) DEFAULT NULL COMMENT '证件存放位置2',
  `CREATE_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `SCBZ` varchar(10) DEFAULT NULL COMMENT '删除标识',
  PRIMARY KEY (`CERT_ID`),
  KEY `IDXgl5w5590l2shr9hukcy6nvgy7` (`CERT_NAME`),
  KEY `IDXa37xrwq2lds3gwf3oehd6ruul` (`CERT_TYPE`),
  KEY `IDXmp9vebaghod325nhp3cvi3373` (`CERT_URL1`),
  KEY `IDXeno5itmutjf8tqtgn5k9f06p3` (`CERT_URL2`),
  KEY `IDXkadh6iajg8ygjtjks8s3j20m9` (`CREATE_TIME`),
  KEY `IDXks2wpawcio3qbxk4ot4dov95l` (`SCBZ`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cert
-- ----------------------------
INSERT INTO `cert` VALUES ('11', '111', '1', '/upload/20171117162938/2017111716293918679236280056', '/upload/20171117162938/2017111716293911215180584541', '2017-11-17 16:29:39', '0');
