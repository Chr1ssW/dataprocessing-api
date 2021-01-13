package org.CHR1SSW.tables.Amazon;

import javax.persistence.*;

@Entity
@Table(name = "amazon_main")
public class AmazonTitles {
    @Id
    private int id;
    @Column
    private String nameOfTheShow;
    @Column
    private int yearOfRelease;
    @Column
    private int numberOfSeasons;
    @Column
    private String availableLanguage;
    @Column
    private String genres;
    @Column
    private double imdbRating;
    @Column
    private String ageOfViewers;

    public int getId() {
        return id;
    }

    public void setId(int seriesNumber) {
        this.id = seriesNumber;
    }

    public String getNameOfTheShow() {
        return nameOfTheShow;
    }

    public void setNameOfTheShow(String nameOfTheShow) {
        this.nameOfTheShow = nameOfTheShow;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public void setYearOfRelease(int yearOfRelease) {
        this.yearOfRelease = yearOfRelease;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public String getAvailableLanguage() {
        return availableLanguage;
    }

    public void setAvailableLanguage(String language) {
        this.availableLanguage = language;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getAgeOfViewers() {
        return ageOfViewers;
    }

    public void setAgeOfViewers(String ageOfViewers) {
        this.ageOfViewers = ageOfViewers;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}
