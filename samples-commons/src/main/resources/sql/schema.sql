CREATE TABLE IF NOT EXISTS t_user
(
    username varchar(50)  NOT NULL PRIMARY KEY,
    password varchar(500) NOT NULL,
    enabled  boolean      NOT NULL DEFAULT true
    );

CREATE TABLE IF NOT EXISTS t_role
(
    username varchar(50) NOT NULL,
    role     varchar(50) NOT NULL,
    constraint uk_username_role UNIQUE (username, role)
    );

CREATE TABLE IF NOT EXISTS t_permission
(
    role     varchar(50)  NOT NULL,
    resource varchar(512) NOT NULL,
    action   varchar(8)   NOT NULL,
    constraint uk_role_permission UNIQUE (role, resource, action)
    );

CREATE TABLE IF NOT EXISTS t_order_0
(
    id bigint(20)  NOT NULL PRIMARY KEY,
    type smallint  NOT NULL,
    state smallint  NOT NULL
    );

CREATE TABLE IF NOT EXISTS t_order_1
(
    id bigint(20)  NOT NULL PRIMARY KEY,
    type smallint  NOT NULL,
    state smallint  NOT NULL
);