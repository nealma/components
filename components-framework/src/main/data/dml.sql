/**
 * @author neal.ma
 * @date 5/20/2016
 */

CREATE TABLE `t_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `permission` varchar(250) NOT NULL,
  `url` varchar(250) NOT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'create time',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'last edit time',
  `row_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 ok, 1 deleted',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `permission` varchar(250) NOT NULL,
  `url` varchar(250) NOT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'create time',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'last edit time',
  `row_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 ok, 1 deleted',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(250) NOT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'create time',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'last edit time',
  `row_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 ok, 1 deleted',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_user_role_link` (
  `user_id`  bigint(20) NOT NULL,
  `role_id`  bigint(20) NOT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'create time',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'last edit time',
  `row_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 ok, 1 deleted',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `t_user_role_link_fk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `t_user_role_link_fk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `t_role_resource_link` (
  `resource_id`  bigint(20) NOT NULL,
  `role_id`  bigint(20) NOT NULL,
  `last_edit_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'create time',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'last edit time',
  `row_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0 ok, 1 deleted',
  PRIMARY KEY (`resource_id`,`role_id`),
  KEY `resource_id` (`resource_id`),
  CONSTRAINT `t_user_resource_link_fk_1` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`),
  CONSTRAINT `t_user_resource_link_fk_2` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;