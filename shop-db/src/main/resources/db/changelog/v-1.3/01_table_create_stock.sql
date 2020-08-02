CREATE TABLE `stock`
(
    `id`         INT NOT NULL AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `stock`      INT NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `fk_product_in_stock_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `fk_product_in_stock`
        FOREIGN KEY (`product_id`)
            REFERENCES `product` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
