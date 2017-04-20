CREATE TABLE `users` (
  `id`       VARCHAR(255) NOT NULL,
  `avatar`   VARCHAR(255) DEFAULT NULL,
  `username` VARCHAR(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
);
CREATE TABLE `messages` (
  `id`           BIGINT (20) NOT NULL AUTO_INCREMENT,
  `channel_name` VARCHAR(255) NOT NULL,
  `subtype`      VARCHAR(100) NOT NULL,
  `text`         LONGTEXT     NOT NULL,
  `ts`           TINYBLOB     NOT NULL,
  `type`         VARCHAR(100) NOT NULL,
  `user_id`      VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);