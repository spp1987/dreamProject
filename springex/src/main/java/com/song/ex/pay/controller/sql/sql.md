```mysql

CREATE TABLE `pay_bank_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `bank_code` varchar(64) NOT NULL DEFAULT '' COMMENT '业务统一银行编码',
  `bank_name` varchar(64) NOT NULL DEFAULT '' COMMENT '业务银行名称',
  `small_logo_url` varchar(255) DEFAULT NULL COMMENT '小logo_url',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '大logo_url',
  `background_img` varchar(255) DEFAULT NULL COMMENT '背景图片',
  `sort` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `status` tinyint(4) NOT NULL DEFAULT '1' COMMENT '状态:1-正常 2-下线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='业务统一银行信息';

##业务统一银行code - 渠道业务银行code的关系表 
CREATE TABLE `pay_bank_info_channel_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `bank_info_id` bigint(20) NOT NULL COMMENT '对应业务统一编码 pay_bank_info.id',
  `way_type` varchar(64) DEFAULT NULL COMMENT '通道类型 BIND_CARD_WAY 绑卡;WITHHOLD_PROTOCOL_WAY 协议扣款;WITHHOLD_WAY 普通代扣',
  `channel_way_id` bigint(20) DEFAULT NULL COMMENT 'pay_channel_way_ref.id',
  `channel_bank_name` varchar(50) DEFAULT NULL COMMENT '渠道银行名称',
  `channel_bank_code` varchar(20) DEFAULT NULL COMMENT '渠道银行编码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_bankid_waycode_busi` (`bank_info_id`,`business_type`,`channel_bank_code`,`channel_way_id`) USING BTREE,
  KEY `ind_channel_way_id` (`channel_way_id`,`bank_info_id`) USING BTREE,
  KEY `ind_bank_code` (`channel_bank_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='渠道银行关系信息表';

CREATE TABLE `pay_channel_way_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_id` bigint(20) NOT NULL COMMENT '支付商渠道id',
  `business_way_code` varchar(50) DEFAULT NULL COMMENT '通道所属类 BINDCARD 绑卡;WITHHOLD 扣款;PAYMENT 付款',
  `way_type` varchar(64) DEFAULT NULL COMMENT '通道类型（代扣 、协议）',
  `way_code` varchar(50) NOT NULL COMMENT '支付通道编码',
  `way_name` varchar(50) NOT NULL COMMENT '通道名称',
  `way_rate` decimal(7,4) NOT NULL COMMENT '费率 百分之',
  `way_status` char(1) NOT NULL COMMENT '通道状态 O：启动  S:禁用',
  `creator` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `updator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_channel_id_way_code` (`channel_id`,`way_code`) USING BTREE,
  KEY `index_channel_id` (`channel_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付商-通道关系表';

CREATE TABLE `pay_channel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `channel_code` varchar(20) NOT NULL COMMENT '渠道编码',
  `channel_name` varchar(20) NOT NULL COMMENT '渠道名称',
  `channel_status` char(1) NOT NULL COMMENT '渠道状态 O:启用 S:禁用',
  `creator` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_channel_code` (`channel_code`) USING BTREE,
  KEY `index_channel_status` (`channel_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付渠道商';

CREATE TABLE `pay_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(20) NOT NULL,
  `product_name` varchar(20) NOT NULL,
  `cmp_name` varchar(64) DEFAULT NULL COMMENT '公司主体名称',
  `product_status` char(1) NOT NULL DEFAULT '' COMMENT '启用状态 O:启用  S:停用',
  `creator` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `updator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_product_code` (`product_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付产品表';

CREATE TABLE `pay_channel_way_ref_product_ref` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `channel_code` varchar(20) DEFAULT NULL,
  `channel_way_id` bigint(20) NOT NULL COMMENT 'pay_channel_way_ref.id',
  `merchant_id` varchar(20) DEFAULT NULL COMMENT '商户号',
  `weight` tinyint(5) NOT NULL COMMENT '权重-优先级',
  `ref_status` char(1) DEFAULT 'O' COMMENT '关系启用状态 O:启用  S:禁用',
  `creator` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_product_id_channel_way_id` (`product_id`,`channel_way_id`) USING BTREE,
  KEY `index_product_id` (`product_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='产品-渠道-通道关系表';

CREATE TABLE `pay_trade` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `trade_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '交易创建时间',
  `trade_no` varchar(50) NOT NULL COMMENT '交易单号',
  `protocol_no` varchar(50) DEFAULT NULL COMMENT '协议号',
  `business_type` varchar(20) NOT NULL DEFAULT '' COMMENT '业务类型',
  `business_no` varchar(50) NOT NULL COMMENT '业务单号',
  `product_code` varchar(20) NOT NULL COMMENT '产品编码',
  `product_name` varchar(20) NOT NULL COMMENT '产品名称',
  `trade_user_name` varchar(100) DEFAULT NULL COMMENT '交易人姓名',
  `trade_mobile` varchar(20) NOT NULL COMMENT '交易手机号',
  `trade_bank_mobile` varchar(20) DEFAULT NULL COMMENT '银行预留手机号',
  `trade_bank_code` varchar(20) NOT NULL,
  `trade_bank_name` varchar(20) NOT NULL,
  `trade_bank_no` varchar(50) DEFAULT NULL COMMENT '交易卡号',
  `trade_amount` bigint(20) NOT NULL COMMENT '交易金额',
  `trade_fee_amount` bigint(20) NOT NULL COMMENT '参考手续费',
  `trade_channel_code` varchar(20) NOT NULL COMMENT '交易渠道编码',
  `trade_channel_name` varchar(20) NOT NULL COMMENT '交易渠道名称',
  `trade_way_code` varchar(50) NOT NULL DEFAULT '' COMMENT '交易通道编码',
  `trade_way_name` varchar(50) NOT NULL DEFAULT '' COMMENT '交易通道名称',
  `share_info` varchar(100) DEFAULT NULL COMMENT '格式，商户1,金额;商户2,金额；',
  `trade_status` char(1) NOT NULL DEFAULT '' COMMENT '交易状态 1- 申请 2-处理中 3-成功 4-失败 ',
  `resp_code` varchar(50) DEFAULT NULL COMMENT '响应码',
  `resp_message` varchar(200) DEFAULT NULL COMMENT '响应消息',
  `creator` varchar(20) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `updator` varchar(20) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`trade_way_name`),
  UNIQUE KEY `uni_trade_no` (`trade_no`) USING BTREE,
  KEY `index_trade_mobile` (`trade_mobile`) USING BTREE,
  KEY `index_business_no` (`business_no`) USING BTREE,
  KEY `index_business_type` (`business_type`) USING BTREE,
  KEY `index_trade_create_time` (`trade_create_time`) USING BTREE,
  KEY `index_trade_status` (`trade_status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付交易表';

CREATE TABLE `pay_bind_card_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_no` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `bank_no` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `user_name` varchar(50) DEFAULT NULL COMMENT '客户姓名',
  `bank_mobile` char(11) DEFAULT NULL COMMENT '银行预留手机号',
  `bank_code` varchar(40) DEFAULT NULL COMMENT '银行编码',
  `unique_code` varchar(100) DEFAULT NULL COMMENT '唯一标识码',
  `protocol_no` varchar(100) DEFAULT NULL COMMENT '协议号',
  `bind_card_status` varchar(20) DEFAULT NULL COMMENT '绑卡状态 init / fail / ready / confirm',
  `request_no` varchar(50) DEFAULT NULL COMMENT '请求编码',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道编码',
  `channel_way_code` varchar(50) DEFAULT NULL COMMENT '通道编码',
  `product_code` varchar(50) DEFAULT NULL COMMENT '产品编码',
  `bind_card_type` varchar(20) DEFAULT NULL COMMENT 'auto-自动绑卡/ manual-手动绑卡',
  `remark` varchar(200) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `updator` varchar(50) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_unique_code` (`unique_code`) USING BTREE,
  UNIQUE KEY `uniq_request_no` (`request_no`),
  KEY `bank_no` (`bank_no`,`channel_code`,`product_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='支付绑卡关系表';
```

