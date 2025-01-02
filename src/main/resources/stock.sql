-- ------------------------------------------------------------------------------------
-- - Gestion des droits d'acces
-- ------------------------------------------------------------------------------------
USE stock;

-- ------------------------------------------------------------------------------------
-- - Gestion de la table des Users
-- ------------------------------------------------------------------------------------

CREATE TABLE T_Users (
    username            varchar(25)                 PRIMARY KEY,
    password            varchar(250),
    active              boolean
)ENGINE = InnoDB;

INSERT INTO T_Users (username, password, active) VALUES ("mohamed","$2a$12$i.dbJSPk83K7Su8OTo3IgOpKsInbtdn.Qc1V66k3k8x5MZnf26Z3S", TRUE);
INSERT INTO T_Users (username, password, active) VALUES ("aymene", "$2a$12$i.dbJSPk83K7Su8OTo3IgOpKsInbtdn.Qc1V66k3k8x5MZnf26Z3S", TRUE);

-- ------------------------------------------------------------------------------------
-- - Gestion de la table des Users
-- ------------------------------------------------------------------------------------
CREATE TABLE T_Roles(
    role                varchar(25)                 PRIMARY KEY
)ENGINE = InnoDB;

INSERT INTO T_Roles (role) VALUES ('ADMIN');
INSERT INTO T_Roles (role) VALUES ('USER');

-- ------------------------------------------------------------------------------------
-- - Gestion de la table des Users
-- ------------------------------------------------------------------------------------

CREATE TABLE T_Users_Roles (
    username            varchar(25),
    role                varchar(25),
    PRIMARY KEY(username, role)
)ENGINE = InnoDB;

INSERT INTO T_Users_Roles (username, role) VALUES ('mohamed', 'ADMIN');
INSERT INTO T_Users_Roles (username, role) VALUES ('mohamed', 'USER');
INSERT INTO T_Users_Roles (username, role) VALUES ('aymene', 'USER');

-- ------------------------------------------------------------------------------------
-- -
-- ------------------------------------------------------------------------------------