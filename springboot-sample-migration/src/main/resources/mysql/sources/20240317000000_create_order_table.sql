CREATE TABLE IF NOT EXISTS `orders` (
    `id`                        INT             NOT NULL AUTO_INCREMENT,
    `created_at`                DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `updated_at`                DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `user_id`                   VARCHAR(255)    NOT NULL,
    `note`                     TEXT,
    INDEX `user_id index` (`user_id`),
    PRIMARY KEY (`id`)
    )