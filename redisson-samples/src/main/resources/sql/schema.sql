CREATE TABLE IF NOT EXISTS t_stock
(
    id bigint   NOT NULL PRIMARY KEY,
    product_id bigint NOT NULL,
    quantity  bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS t_product
(
    id bigint   NOT NULL PRIMARY KEY,
    name varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS t_order
(
    id bigint   NOT NULL PRIMARY KEY,
    product_id bigint NOT NULL,
    purchase_quantity  bigint NOT NULL
);