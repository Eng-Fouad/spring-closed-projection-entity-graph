CREATE TABLE level3 (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT pk_level3 PRIMARY KEY (id)
);

CREATE TABLE level2 (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    level3_id INT NOT NULL,
    CONSTRAINT pk_level2 PRIMARY KEY (id),
    CONSTRAINT fk_level2_level3 FOREIGN KEY (level3_id) REFERENCES level3 (id)
);

CREATE TABLE level1 (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    level2_id INT NOT NULL,
    CONSTRAINT pk_level1 PRIMARY KEY (id),
    CONSTRAINT fk_level1_level2 FOREIGN KEY (level2_id) REFERENCES level2 (id)
);