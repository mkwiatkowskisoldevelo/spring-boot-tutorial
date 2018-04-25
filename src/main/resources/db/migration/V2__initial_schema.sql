CREATE TABLE receipts (
	id INT PRIMARY KEY AUTO_INCREMENT,
	buyer VARCHAR(256) NOT NULL,
  date timestamp NOT NULL
);

CREATE TABLE products (
	id INT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(256) NOT NULL UNIQUE,
	price DOUBLE NOT NULL,
	receipt_id INT,
	FOREIGN KEY (receipt_id)
	    REFERENCES receipts(id)
	    ON UPDATE CASCADE
	    ON DELETE CASCADE
);

CREATE TABLE receipts2 (
	id INT PRIMARY KEY AUTO_INCREMENT,
	buyer VARCHAR(256) NOT NULL,
  date timestamp NOT NULL
);

CREATE TABLE receipt_products (
	receipt_id INT NOT NULL,
	product_id INT NOT NULL,
	FOREIGN KEY (receipt_id)
	    REFERENCES receipts2(id),
  FOREIGN KEY (product_id)
	    REFERENCES products(id)
);