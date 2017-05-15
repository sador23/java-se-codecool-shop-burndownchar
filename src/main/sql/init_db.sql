DROP TABLE IF EXISTS products;

CREATE TABLE productcategories
(
id int PRIMARY KEY,
name varchar,
description varchar
);

DROP TABLE IF EXISTS suppliers;

CREATE TABLE suppliers
(
id int PRIMARY KEY,
name varchar,
description varchar
);

DROP TABLE IF EXISTS products;

CREATE TABLE products
(
id int PRIMARY KEY,
name varchar,
description varchar,
defaultprice varchar,
defaultcurrency varchar,
productcategory int references productcategories(id),
supplier int references suppliers(id)
);