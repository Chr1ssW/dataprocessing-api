create TABLE IF NOT EXISTS netflix (
    show_id INT NOT NULL PRIMARY KEY,
    movie_type VARCHAR(255) NOT NULL,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    actors VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    date_added VARCHAR(255) NOT NULL,
    release_year VARCHAR(255) NOT NULL,
    rating VARCHAR(255) NOT NULL,
    duration VARCHAR(255) NOT NULL,
    categories VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;