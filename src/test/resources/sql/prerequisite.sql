INSERT INTO security_group (name) VALUES ("buyer");
INSERT INTO security_group (name) VALUES ("seller");
INSERT INTO security_group_authority (authority, security_group_id) VALUES ("ROLE_BUYER", 1);
INSERT INTO security_group_authority (authority, security_group_id) VALUES ("ROLE_SELLER", 2);
