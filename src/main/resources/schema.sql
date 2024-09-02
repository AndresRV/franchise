CREATE TABLE IF NOT EXISTS franchise (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS branch (
    id INT AUTO_INCREMENT PRIMARY KEY,
    franchise_id INT,
    name VARCHAR(255),
    FOREIGN KEY (franchise_id) REFERENCES franchise(id)
);

CREATE TABLE IF NOT EXISTS product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    branch_id INT,
    name VARCHAR(255),
    stock INT,
    FOREIGN KEY (branch_id) REFERENCES product(id)
);