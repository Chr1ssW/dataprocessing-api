create TABLE IF NOT EXISTS disneyplus (
    imdb_id VARCHAR(255) NOT NULL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    plot VARCHAR(255) NOT NULL,
    film_type VARCHAR(50) NOT NULL,
    rated VARCHAR(20) NOT NULL,
    created_year INT NOT NULL,
    released_at VARCHAR(255) NOT NULL,
    added_at VARCHAR(255) NOT NULL,
    runtime VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    writer VARCHAR(255) NOT NULL,
    actors VARCHAR(255) NOT NULL,
    languages VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    awards VARCHAR(255) NOT NULL,
    metascore VARCHAR(255) NOT NULL,
    imdb_rating DOUBLE NOT NULL,
    imdb_votes VARCHAR(255) NOT NULL

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;