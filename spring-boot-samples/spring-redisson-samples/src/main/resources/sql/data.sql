insert into t_stock (id, product_id, quantity)
values (1, 1001, 1000),
       (2, 1002, 1001);

insert into t_product (id, name)
values (1, 'iphone 1'),
       (2, 'iphone 2');


insert into t_order (id, product_id, purchase_quantity)
values (1, 1001, 99),
       (2, 1001, 66);