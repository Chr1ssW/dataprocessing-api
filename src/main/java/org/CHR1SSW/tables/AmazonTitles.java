package org.CHR1SSW.tables;

import javax.persistence.*;

public class AmazonTitles
{
    @Id
    private int seriesNumber;
    private String nameOfTheShow;
    private int yearOfRelease;
    private int numberOfSeasons;
    private String language;
    private double imdbRating;
    private String ageOfViewers;

    public int getSeriesNumber() {
        return seriesNumber;
    }

    public void setSeriesNumber(int seriesNumber) {
        this.seriesNumber = seriesNumber;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
}
