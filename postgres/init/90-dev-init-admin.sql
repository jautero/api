INSERT INTO admin.Admins VALUES ('admin@example.com', true, true, true);
INSERT INTO kansa.People (legal_name, email, membership, member_number) VALUES ('Admin', 'admin@example.com', 'NonMember', NULL);
INSERT INTO kansa.Keys VALUES ('admin@example.com', 'key');

INSERT INTO kansa.People (legal_name, email, membership, member_number, can_hugo_nominate) VALUES ('Hugo Demo #1', 'hugo1', 'NonMember', NULL, true);
INSERT INTO kansa.People (legal_name, email, membership, member_number, can_hugo_nominate) VALUES ('Hugo Demo #2', 'hugo2', 'NonMember', NULL, true);
INSERT INTO kansa.People (legal_name, email, membership, member_number, can_hugo_nominate) VALUES ('Hugo Demo #3', 'hugo3', 'NonMember', NULL, true);
INSERT INTO kansa.Keys VALUES ('hugo1', 'key');
INSERT INTO kansa.Keys VALUES ('hugo2', 'key');
INSERT INTO kansa.Keys VALUES ('hugo3', 'key');
