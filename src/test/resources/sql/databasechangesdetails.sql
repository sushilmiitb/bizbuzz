#######################Add register_request table################################
CREATE TABLE register_request(id bigint(20) NOT NULL AUTO_INCREMENT, from_party_id bigint(20), to_party_phone_number varchar(255), private_group_id bigint(20), INDEX party_ind(from_party_id), INDEX group_ind(private_group_id), PRIMARY KEY(id), FOREIGN KEY(from_party_id) REFERENCES party(id), foreign key(private_group_id) REFERENCES private_group(id));

