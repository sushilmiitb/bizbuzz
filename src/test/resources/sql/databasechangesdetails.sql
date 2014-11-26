#######################Add register_request table################################
CREATE TABLE register_request(id bigint(20) NOT NULL AUTO_INCREMENT, from_party_id bigint(20), to_party_phone_number varchar(255), private_group_id bigint(20), INDEX party_ind(from_party_id), INDEX group_ind(private_group_id), PRIMARY KEY(id), FOREIGN KEY(from_party_id) REFERENCES party(id), foreign key(private_group_id) REFERENCES private_group(id));

#########################Adding register device table#######################3
CREATE TABLE register_device(id bigint(20) NOT NULL AUTO_INCREMENT, device_registration_id varchar(255), party_id bigint(20), PRIMARY KEY(id), FOREIGN KEY (party_id) REFERENCES party(id));

########################Changes made by sushil for custom category functionality#######################
ALTER TABLE category_tree add column is_custom bit(1);
ALTER TABLE category_tree add column owner_id bigint(20), add foreign key(owner_id) REFERENCES party(id);
update category_tree set is_custom=false where is_custom is null;
ALTER TABLE category_tree add column has_product bit(1);
