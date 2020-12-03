package com.recomendationSystem.week.two;

import com.recomendationSystem.models.Rating;

import java.util.Collections;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 03/12/2020
 */
public class MovieRunnerAverage
{
    SecondRatings secondRatings;

    public MovieRunnerAverage()
    {
        secondRatings = new SecondRatings();
    }

    public MovieRunnerAverage(String movieFile, String raterFile)
    {
        secondRatings = new SecondRatings(movieFile, raterFile);
    }

    public void printAverageRatings(int minimalRaters)
    {
        System.out.println("Number of movies: " + secondRatings.getMovieSize());
        System.out.println("Number of raters: " + secondRatings.getRaterSize());

        List<Rating> ratings = secondRatings.getAverageRatings(minimalRaters);
        Collections.sort(ratings); // sort movie ratings

        for(Rating rating : ratings)
        {
            System.out.println(rating.getValue() + " " + secondRatings.getTitle(rating.getItem()));
        }
        System.out.println(ratings.size());
    }

    public void getAverageRatingOneMovie(String movieTitle, int minimalRaters)
    {
        String id = secondRatings.getID(movieTitle);
        System.out.println("The movie " + movieTitle + " has id " + id);
        System.out.println("And average rating: " + secondRatings.getAverageByID(id, minimalRaters));
    }
}
