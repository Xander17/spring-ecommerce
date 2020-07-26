CREATE TABLE `brand_picture`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `content_type` VARCHAR(45) NOT NULL,
    `data_id`      INT         NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `brand`
(
    `id`         INT         NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(45) NOT NULL,
    `picture_id` INT         NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_picture_in_brand_idx` (`picture_id` ASC) VISIBLE,
    CONSTRAINT `fk_picture_in_brand`
        FOREIGN KEY (`picture_id`)
            REFERENCES `shop_db`.`brand_picture` (`id`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
);
