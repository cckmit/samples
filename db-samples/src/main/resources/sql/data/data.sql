-- 先删除
DELETE FROM t_user;
-- 再插入
INSERT INTO t_user (username, password, enabled)
VALUES ('username_1', 'password_1', true),
       ('username_2', 'password_2', true);
-- 先删除
DELETE FROM t_role;
-- 再插入
INSERT INTO t_role (username, role)
VALUES ('username_1','role_1'),
       ('username_2','role_2');

-- 先删除
DELETE FROM t_permission;
-- 再插入
INSERT INTO t_permission (role, resource, action)
VALUES ('role_1','/**','*'),
       ('role_2','/order','*');

-- 先删除
DELETE FROM t_order_0;
-- 再插入
INSERT INTO t_order_0 (id, type, state)
VALUES (2,1,0);
-- 先删除
DELETE FROM t_order_1;
-- 再插入
INSERT INTO t_order_1 (id, type, state)
VALUES (1,1,1);
