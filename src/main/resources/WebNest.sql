CREATE TABLE IF NOT EXISTS `webNest`
(
    `id`          INT PRIMARY KEY AUTO_INCREMENT,
    `favicon`     VARCHAR(255) NOT NULL,
    `title`       VARCHAR(255) NOT NULL,
    `url`         VARCHAR(255) NOT NULL,
    `description` VARCHAR(255),
    `tag`         VARCHAR(255) NOT NULL,
    `status`      INT default 0   NOT NULL,
    `ghm`         INT default 0   NOT NULL ,
    `create_time` DATETIME     NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `passwd`
(
    'id' int primary key  NOT NULL ,
    `role`     VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `Tag`
(
    `id`     int primary key NOT NULL,
    `type` VARCHAR(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;