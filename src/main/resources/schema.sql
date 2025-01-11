CREATE DATABASE IF NOT EXISTS `shop` DEFAULT CHARACTER SET UTF8;
CREATE TABLE IF NOT EXISTS `shop`.`user` (
                                             `id` INT NOT NULL AUTO_INCREMENT COMMENT 'User ID',
                                             `username` VARCHAR(50) NOT NULL COMMENT 'Username',
    `password` VARCHAR(50) NOT NULL COMMENT 'Password (salted and encrypted)',
    `nickname` VARCHAR(50) NOT NULL COMMENT 'Nickname',
    `email` VARCHAR(50) NOT NULL COMMENT 'Registration email',
    `phone` BIGINT(11) DEFAULT NULL COMMENT 'Phone number',
    `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
    `utime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
    `stat` TINYINT NOT NULL DEFAULT 0 COMMENT 'User status, 0 for normal, 1 for unverified, 2 for restricted, 3 for deleted',
    PRIMARY KEY (`id`),
    INDEX `name`(`username`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'User table';

CREATE TABLE IF NOT EXISTS `shop`.`token` (
                                              `uid` INT NOT NULL COMMENT 'User ID',
                                              `token` VARCHAR(36) NOT NULL COMMENT 'Token',
    `expired_time` TIMESTAMP NOT NULL COMMENT 'Expiration time',
    `ip` VARCHAR(15) NOT NULL DEFAULT "" COMMENT 'Visitor IP',
    `device` VARCHAR(150) NOT NULL DEFAULT "" COMMENT 'Login device',
    `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Login time',
    `stat` TINYINT NOT NULL DEFAULT 0 COMMENT '0 for normal, 1 for expired',
    UNIQUE INDEX`token_index`(`token`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Token table';

CREATE TABLE IF NOT EXISTS `shop`.`game` (
                                             `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                             `creater` VARCHAR(50) NOT NULL COMMENT 'Developer',
    `name` VARCHAR(100) NOT NULL COMMENT 'Game name',
    `desc` VARCHAR(500) NOT NULL COMMENT 'Game description',
    `systemcfg` VARCHAR(500) NOT NULL COMMENT 'System requirements',
    `price` DOUBLE NOT NULL COMMENT 'Normal price',
    `discount` DOUBLE DEFAULT NULL COMMENT 'Discounted price',
    `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
    `utime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Shelf time',
    `stat` TINYINT NOT NULL DEFAULT 0 COMMENT 'Status, 0 for not listed, 1 for listed, 2 for delisted',
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Game table';

CREATE TABLE IF NOT EXISTS `shop`.`img` (
                                            `game` INT NOT NULL COMMENT 'Game ID',
                                            `img` VARCHAR(50) NOT NULL COMMENT 'Image path'
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Game image mapping table';

CREATE TABLE IF NOT EXISTS `shop`.`code` (
                                             `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                             `item` INT NOT NULL COMMENT 'Order item ID',
                                             `uid` INT NOT NULL COMMENT 'User ID',
                                             `code` VARCHAR(50) NOT NULL COMMENT 'Activation code',
    `stat` TINYINT NOT NULL DEFAULT 0 COMMENT 'Activation code status, 0 for unused, 1 for used',
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Activation code table';

CREATE TABLE IF NOT EXISTS `shop`.`order` (
                                              `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                              `uid` INT NOT NULL COMMENT 'Purchaser ID',
                                              `price` DOUBLE NOT NULL DEFAULT 0 COMMENT 'Total order price',
                                              `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
                                              `utime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
                                              `stat` TINYINT NOT NULL DEFAULT 0 COMMENT 'Order status, 0 for unpaid, 1 for paid, 2 for cancelled',
                                              PRIMARY KEY (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Order table';

CREATE TABLE IF NOT EXISTS `shop`.`orderitem` (
                                                  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'ID',
                                                  `gid` INT NOT NULL COMMENT 'Game ID',
                                                  `price` DOUBLE NOT NULL COMMENT 'Price at time of purchase',
                                                  `code` INT DEFAULT NULL COMMENT 'Activation code',
                                                  `ctime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Creation time',
                                                  `utime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Update time',
                                                  PRIMARY KEY (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Details of each order item';

CREATE TABLE IF NOT EXISTS `shop`.`ordermapper` (
                                                    `order` INT NOT NULL COMMENT 'Order ID',
                                                    `item` INT NOT NULL COMMENT 'Order detail ID'
) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Order-detail item mapping table';

CREATE TABLE IF NOT EXISTS `shop`.`kind` (
                                             `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Category ID',
                                             `name` VARCHAR(10) NOT NULL COMMENT 'Category name',
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Game category table';

CREATE TABLE IF NOT EXISTS `shop`.`kindmapper` (
                                                   `game` INT NOT NULL COMMENT 'Game ID',
                                                   `kind` INT NOT NULL COMMENT 'Game category ID'
) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Game-category detail item mapping table';

CREATE TABLE IF NOT EXISTS `shop`.`tag` (
                                            `id` INT NOT NULL AUTO_INCREMENT COMMENT 'Tag ID',
                                            `name` VARCHAR(10) NOT NULL COMMENT 'Tag name',
    PRIMARY KEY (`id`)
    ) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Game tag table';

CREATE TABLE IF NOT EXISTS `shop`.`tagmapper` (
                                                  `game` INT NOT NULL COMMENT 'Game ID',
                                                  `tag` INT NOT NULL COMMENT 'Tag ID'
) ENGINE = InnoDB DEFAULT CHARSET = UTF8 COMMENT 'Game-tag mapping table';
