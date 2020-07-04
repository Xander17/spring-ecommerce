INSERT INTO `user`(`name`, `password`, `enabled`)
VALUES ('sadmin', '$2y$12$JycmpDPq.bpkgxItmIzzoeMvjt8JGX5WIiq398ExU42zVL3RuWXS.', 1);

INSERT INTO `role`(`name`)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN'),
       ('ROLE_SUPER_ADMIN');

INSERT INTO `user_roles`(`user_id`, `role_id`)
VALUES ((SELECT `id` FROM `user` WHERE `name` = 'sadmin'), (SELECT `id` FROM `role` WHERE `name` = 'ROLE_USER')),
       ((SELECT `id` FROM `user` WHERE `name` = 'sadmin'), (SELECT `id` FROM `role` WHERE `name` = 'ROLE_MANAGER')),
       ((SELECT `id` FROM `user` WHERE `name` = 'sadmin'), (SELECT `id` FROM `role` WHERE `name` = 'ROLE_ADMIN')),
       ((SELECT `id` FROM `user` WHERE `name` = 'sadmin'), (SELECT `id` FROM `role` WHERE `name` = 'ROLE_SUPER_ADMIN'));
