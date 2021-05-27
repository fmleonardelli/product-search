CREATE TABLE IF NOT EXISTS brand (
    id bigint NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS price (
    id BIGINT NOT NULL PRIMARY KEY,
    brand_id BIGINT NOT NULL,
    start_date DATETIME,
    end_date DATETIME,
    price_list_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    priority INT NOT NULL,
    price FLOAT(6) NOT NULL,
    curr CHAR(3),
    CONSTRAINT brand_id_fk FOREIGN KEY (brand_id) REFERENCES brand(id)
);