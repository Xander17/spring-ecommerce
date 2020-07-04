CREATE TABLE `picture`
(
    `id`           int(11)      NOT NULL AUTO_INCREMENT,
    `name`         varchar(255) NOT NULL,
    `content_type` varchar(255) NOT NULL,
    `data_id`      int(11)      NOT NULL,
    `product_id`   int(11)      NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_product_in_picture_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_in_picture`
        FOREIGN KEY (`product_id`)
            REFERENCES `product` (`id`)
) ENGINE = InnoDB;

CREATE TABLE `picture_data`
(
    `id`   int(11)  NOT NULL AUTO_INCREMENT,
    `data` longblob NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
