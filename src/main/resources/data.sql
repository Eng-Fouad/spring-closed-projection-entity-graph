INSERT INTO level3 (name) VALUES ('level3');
INSERT INTO level2 (name, level3_id) VALUES ('level2', 1);
INSERT INTO level1 (name, level2_id) VALUES ('level1', 1);