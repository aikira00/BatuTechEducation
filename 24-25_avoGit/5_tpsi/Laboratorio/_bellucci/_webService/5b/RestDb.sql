DROP DATABASE IF EXISTS RestDb;
CREATE DATABASE RestDb; 

USE RestDb;


CREATE TABLE categories (
  id int(11) NOT NULL AUTO_INCREMENT,
  description varchar(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO categories (description) VALUES
('Computer'),
('Smartphone'),
('modifica'),
('yyyyyy'),
('qqqqqq'),
('qqqqqq'),
('xxxxxxxx'),
('qqqqqq'),
('nuova');


CREATE TABLE  products (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  description varchar(500) DEFAULT NULL,
  price float(5,2) NOT NULL,
  id_category int(11),
  PRIMARY KEY (id),
  FOREIGN KEY (id_category) REFERENCES categories(id)  ON DELETE SET NULL
);


INSERT INTO products (name, description, price, id_category) VALUES
( 'comp1', 'asus', 401.50, 1),
( 'comp2', 'acer', 700.00, 1),
( 'comp3', 'apple', 801.50, 1),
( 'tel1', 'huwawei', 201.50, 2),
( 'tel2', 'samsung', 600.00, 2),
( 'tel3', 'apple', 601.50, 2),
( 'tab1', 'asus', 201.50, 3),
( 'tab2', 'samsung', 600.00, 3),
( 'tab3', 'apple', 601.50, 3);



CREATE TABLE showrooms (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(50) NOT NULL,
  address varchar(100) NOT NULL,
  city varchar(50) NOT NULL,
  manager varchar(50) NOT NULL,
  PRIMARY KEY (id)
);


INSERT INTO showrooms (name, address, city, manager) VALUES
('show1', 'address1', 'city1', 'antonio'),
('show2', 'address2', 'city2', 'arturo'),
('show3', 'address3', 'city3', 'giovanna'),
('show4', 'address4', 'city4', 'michela'),
('show5', 'address5', 'city5', 'angela'),
('show6', 'address6', 'city6', 'giuseppe');

CREATE TABLE productsshowrooms (
  id_product int(11) NOT NULL,
  id_showroom int(11) NOT NULL ,
  PRIMARY KEY (id_product,id_showroom),
  FOREIGN KEY (id_showroom) REFERENCES showrooms(id) ON DELETE CASCADE,
  FOREIGN KEY (id_product) REFERENCES products(id) ON DELETE CASCADE
);

INSERT INTO productsshowrooms (id_product, id_showroom) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 2),
(2, 3),
(2, 4),
(3, 3),
(3, 4),
(3, 5),
(4, 4),
(4, 5),
(4, 6),
(5, 1),
(5, 5),
(5, 6),
(6, 1),
(6, 2),
(6, 6),
(7, 1),
(7, 2),
(7, 3),
(8, 4),
(8, 5),
(8, 6),
(9, 1),
(9, 2),
(9, 3);

