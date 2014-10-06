INSERT INTO security_group (name) VALUES ("buyer");
INSERT INTO security_group (name) VALUES ("seller");
INSERT INTO security_group (name) VALUES ("admin");
INSERT INTO security_group_authority (authority, security_group_id) VALUES ("ROLE_BUYER", 1);
INSERT INTO security_group_authority (authority, security_group_id) VALUES ("ROLE_SELLER", 2);
INSERT INTO security_group_authority (authority, security_group_id) VALUES ("ROLE_ADMIN", 3);
INSERT INTO category_tree(id,category_name) VALUES (1, "Root");
INSERT INTO `bizbuzz`.`user_login` (
`id` ,
`created_at` ,
`enabled` ,
`password_hash` ,
`updated_at`
)
VALUES (
'+919099043920', NULL , b '0000', 'admin9099043920', NULL
);
INSERT INTO `bizbuzz`.`user_login_security_group` (`user_login_id`, `security_group_id`) VALUES ('+919099043920', '3');
