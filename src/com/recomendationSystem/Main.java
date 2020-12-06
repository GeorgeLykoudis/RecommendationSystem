package com.recomendationSystem;

import com.recomendationSystem.week.four.MovieRunnerSimilarRatings;
import com.recomendationSystem.week.one.FirstRatings;
import com.recomendationSystem.week.three.MovieRunnerWithFilters;
import com.recomendationSystem.week.two.MovieRunnerAverage;
import com.recomendationSystem.week.two.SecondRatings;

public class Main
{
    public static final String path = "D:\\workspace\\github\\RecommendationSystem\\src\\com\\recomendationSystem\\data\\";
    public static final String moviesShortFile = path + "ratedmovies_short.csv";
    public static final String moviesFullFile = path + "ratedmoviesfull.csv";
    public static final String ratersShortFile = path + "ratings_short.csv";
    public static final String ratersFullFile = path + "ratings.csv";

    public static void main(String[] args)
    {
//        FirstRatings firstRatings = new FirstRatings();
//        firstRatings.TestLoadMovies(moviesShortFile);
//        firstRatings.TestLoadRaters(ratersShortFile);

//        MovieRunnerAverage movieRunnerAverage = new MovieRunnerAverage(moviesShortFile, ratersShortFile);
//        movieRunnerAverage.printAverageRatings(2);
//        movieRunnerAverage.getAverageRatingOneMovie("The Godfather", 2);

//        MovieRunnerWithFilters movieRunnerWithFilters = new MovieRunnerWithFilters(moviesFullFile, ratersFullFile);
//        int minimalRaters = 5;
//        int year = 1990;
//        String genre = "Drama";
//        int minMinutes = 105;
//        int maxMinutes = 135;
//        String director="Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack";
//
//        movieRunnerWithFilters.printAverageRatings(minimalRaters);
//        movieRunnerWithFilters.printAverageRatingsByYear(minimalRaters, year);
//        movieRunnerWithFilters.printAverageRatingsByGenre(minimalRaters, genre);
//        movieRunnerWithFilters.printAverageRatingsByMinutes(minimalRaters, minMinutes, maxMinutes);
//        movieRunnerWithFilters.printAverageRatingsByDirector(minimalRaters, director);
//        movieRunnerWithFilters.printAverageRatingsByYearAfterAndGenre(minimalRaters, year, genre);
//        movieRunnerWithFilters.printAverageRatingsByDirectorsAndMinutes(minimalRaters, minMinutes, maxMinutes, director);

        MovieRunnerSimilarRatings movieRunnerSimilarRatings = new MovieRunnerSimilarRatings(moviesFullFile, ratersFullFile);
        movieRunnerSimilarRatings.printAverageRatings(10);
    }

}
