CREATE TABLE `user`
(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `name`     varchar(50)  NOT NULL,
    `password` varchar(255) NOT NULL,
    `email`    varchar(255) DEFAULT NULL,
    `enabled`  bit(1)       NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `role`
(
    `id`   int         NOT NULL AUTO_INCREMENT,
    `name` varchar(30) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `user_roles`
(
    `user_id` int NOT NULL,
    `role_id` int NOT NULL,
    KEY `fk_role_in_user_roles` (`role_id`),
    KEY `fk_user_in_user_roles` (`user_id`),
    CONSTRAINT `fk_user_in_user_roles` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
    CONSTRAINT `fk_role_in_user_roles` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE = InnoDB;