package com.recomendationSystem.week.one.models;

/**
 * @author George Lykoudis
 * @version 02/12/2020
 */
public class Movie
{
    private String id;
    private String title;
    private int    year;
    private String genres;
    private String director;
    private String country;
    private int    minutes;
    private String poster;

    public Movie(String id, String title, String year, String genres, String director, String country, String minutes, String poster)
    {
        this.id       = id.trim();
        this.title    = title.trim();
        this.year     = Integer.parseInt(year);
        this.genres   = genres.trim();
        this.director = director.trim();
        this.country  = country.trim();
        this.minutes  = Integer.parseInt(minutes);
        this.poster   = poster.trim();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getGenres() {
        return genres;
    }

    public String getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", genres='" + genres + '\'' +
                ", director='" + director + '\'' +
                ", country='" + country + '\'' +
                ", minutes=" + minutes +
                ", poster='" + poster + '\'' +
                '}';
    }
}
