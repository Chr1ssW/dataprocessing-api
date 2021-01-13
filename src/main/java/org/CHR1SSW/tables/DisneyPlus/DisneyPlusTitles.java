package org.CHR1SSW.tables.DisneyPlus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "disneyplus")
public class DisneyPlusTitles
{
    @Id
    private String imdbId;

    @Column
    private String title;

    @Column
    private String plot;

    @Column
    private String filmType;

    @Column
    private String rated;

    @Column
    private int createdYear;

    @Column
    private String releasedAt;

    @Column
    private String addedAt;

    @Column
    private String runtime;

    @Column
    private String genre;

    @Column
    private String director;

    @Column
    private String writer;

    @Column
    private String actors;

    @Column
    private String languages;

    @Column
    private String country;

    @Column
    private String awards;

    @Column
    private String metascore;

    @Column
    private double imdbRating;

    @Column
    private String imdbVotes;

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdb_id) {
        this.imdbId = imdb_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getFilmType() {
        return filmType;
    }

    public void setFilmType(String type) {
        this.filmType = type;
    }

    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public int getCreatedYear() {
        return createdYear;
    }

    public void setCreatedYear(int year) {
        this.createdYear = year;
    }

    public String getReleasedAt() {
        return releasedAt;
    }

    public void setReleasedAt(String released_at) {
        this.releasedAt = released_at;
    }

    public String getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(String added_at) {
        this.addedAt = added_at;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String language) {
        this.languages = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdb_rating) {
        this.imdbRating = imdb_rating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdb_votes) {
        this.imdbVotes = imdb_votes;
    }
}
