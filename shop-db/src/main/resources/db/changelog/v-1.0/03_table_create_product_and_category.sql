CREATE TABLE `category`
(
    `id`   int         NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;

CREATE TABLE `product`
(
    `id`          int            NOT NULL AUTO_INCREMENT,
    `title`       varchar(255) DEFAULT NULL,
    `description` text         DEFAULT NULL,
    `category_id` int          DEFAULT NULL,
    `price`       decimal(19, 2) NOT NULL,
    PRIMARY KEY (`id`),
    KEY `fk_category_in_product_idx` (`category_id`),
    CONSTRAINT `fk_category_in_product` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE SET NULL
) ENGINE = InnoDB;

