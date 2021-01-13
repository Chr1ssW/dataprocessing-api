create TABLE IF NOT EXISTS amazon_main (
    id INT NOT NULL PRIMARY KEY,
    name_of_the_show VARCHAR(255) NOT NULL,
    year_of_release INT NOT NULL,
    number_of_seasons INT NOT NULL,
    available_language VARCHAR(255) NOT NULL,
    genres VARCHAR(255) NOT NULL,
    imdb_rating DOUBLE NOT NULL,
    age_of_viewers VARCHAR(255) NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;