package com.recomendationSystem.week.one;

public class Main
{
    public static final String path = "D:\\workspace\\github\\RecommendationSystem\\src\\com\\recomendationSystem\\week\\one\\data\\";
    public static final String moviesShortFile = path + "ratedmovies_short.csv";
    public static final String moviesFullFile = path + "ratedmoviesfull.csv";
    public static final String ratersShortFile = path + "ratings_short.csv";
    public static final String ratersFullFile = path + "ratings.csv";

    public static void main(String[] args)
    {
        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.TestLoadMovies(moviesShortFile);
        firstRatings.TestLoadRaters(ratersShortFile);
    }

}
