create TABLE IF NOT EXISTS amazon_titles_categories (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    series_id INT NOT NULL,
    category_id INT NOT NULL,
    INDEX series_id (series_id),
    INDEX category_id (category_id),
    FOREIGN KEY (series_id) REFERENCES amazon_main(id) ON delete CASCADE,
    FOREIGN KEY (category_id) REFERENCES amazon_categories(id) ON delete CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
