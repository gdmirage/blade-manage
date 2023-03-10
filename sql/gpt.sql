CREATE TABLE `gpt_account` (
  `id` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `creator` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(16) COLLATE utf8mb4_bin DEFAULT 'no' COMMENT '删除标识， 1: yes, 2: no',
  `version` int(11) DEFAULT '0' COMMENT '版本，用于乐观锁',
  `account` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '账号',
  `email_password` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱登录密码',
  `gpt_password` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'GPT密码',
  `key` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'GPT账号的key',
  `total_token` int(11) DEFAULT NULL COMMENT '总共的token',
  `used_token` int(11) DEFAULT NULL COMMENT '已经使用的token',
  `enabled` varchar(16) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '启用状态， 1: yes, 2: no',
  PRIMARY KEY (`id`),
  KEY `key_idx` (`key`) USING BTREE,
  KEY `account_idx` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='GPT 账号表';


CREATE TABLE `gpt_api_request_msg` (
  `id` varchar(128) COLLATE utf8mb4_bin NOT NULL COMMENT '主键',
  `creator` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modifier` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_delete` varchar(16) COLLATE utf8mb4_bin DEFAULT 'no' COMMENT '删除标识， 1：yes， 2：no',
  `version` int(11) DEFAULT '0' COMMENT '版本，用于乐观锁',
  `account_key` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'GPT账号的key',
  `api_request` text COLLATE utf8mb4_bin COMMENT 'API请求参数',
  `api_response` text COLLATE utf8mb4_bin COMMENT 'API返回结果',
  `api_result_code` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'API返回结果代码',
  PRIMARY KEY (`id`),
  KEY `account_key_idx` (`account_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='GPT API 请求信息表';