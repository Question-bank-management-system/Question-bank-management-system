CREATE DATABASE online_judge_five;

USE online_judge_five;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here');
INSERT INTO `blog` VALUES ('2', 'test 1', 'test 1');
INSERT INTO `blog` VALUES ('3', 'test 2', 'test 2');
INSERT INTO `blog` VALUES ('4', 'test 3', 'test 3');
INSERT INTO `blog` VALUES ('5', 'test 4', 'test 4');

CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(200) NOT NULL,
	`password` varchar(200) NOT NULL,
	`userlv` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `teacher` (
  `id` int(11) NOT NULL auto_increment,
	`userlv` int(11) NOT NULL,
	`user_id` int(11) NOT NULL,
	`subject_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
	INDEX `fk_user_teacher_id`(`user_id`) USING BTREE,
	CONSTRAINT `fk_user_teacher_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `admin_user` (
  `id` int(11) NOT NULL auto_increment,
	`userlv` int(11) NOT NULL,
	`user_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
	INDEX `fk_user_admin_user_id`(`user_id`) USING BTREE,
	CONSTRAINT `fk_user_admin_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `class` (
  `id` int(11) NOT NULL auto_increment,
	`teacher_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
	INDEX `fk_teacher_class_id`(`teacher_id`) USING BTREE,
	CONSTRAINT `fk_teacher_class_id` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `student` (
  `id` int(11) NOT NULL auto_increment,
	`class_id` int(11) NOT NULL,
	`userlv` int(11) NOT NULL,
	`user_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
	INDEX `fk_user_student_id`(`user_id`) USING BTREE,
	INDEX `fk_class_student_id`(`class_id`) USING BTREE,
  CONSTRAINT `fk_user_student_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT `fk_class_student_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `topic` (
  `id` int(11) NOT NULL auto_increment,
	`score` int(11) NOT NULL,
	`content` varchar(500),
	`answer` varchar(500),
	`subject_id` int(11),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `test_paper` (
  `id` int(11) NOT NULL auto_increment,
	`all_time` int(11) NOT NULL,
	`starttime` datetime NOT NULL,
	`endtime` datetime NOT NULL,
	`full_marks` int(11),
	`subject_id` int(11),
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
