package com.recomendationSystem.week.three;

import com.recomendationSystem.database.MovieDatabase;
import com.recomendationSystem.models.Filter.Filter;
import com.recomendationSystem.models.Filter.TrueFilter;
import com.recomendationSystem.models.Movie;
import com.recomendationSystem.models.Rater;
import com.recomendationSystem.models.Rating;
import com.recomendationSystem.week.one.FirstRatings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class ThirdRatings
{
    private List<Rater> myRaters;

    public static final String path = "D:\\workspace\\github\\preparation\\Java\\RecomendationSystem\\src\\com\\recomendationSystem\\data\\";

    public ThirdRatings() {
        // default constructor
        this(path + "ratings.csv");
    }

    /**
     * Constructor that uses FirstRatings loadRaters functions.
     *
     *
     * @param ratingsFile
     */
    public ThirdRatings(String ratingsFile)
    {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    /**
     *
     * @return the size of myRaters list.
     */
    public int getRaterSize() {
        return myRaters.size();
    }

    /**
     *
     * @param id a movie id
     * @param minimalRaters
     * @return a double representing the average movie rating for
     *         this ID if there are at least minimalRaters ratings.
     */
    public double getAverageByID(String id, Integer minimalRaters)
    {
        double average = 0.0;

        double total_rating = 0.0;
        int raters = 0;
        for(Rater rater : myRaters)
        {
            double rating = rater.getRating(id);
            if(rating == -1) {
                continue;
            }
            total_rating += rating;
            raters++;
        }

        if(raters < minimalRaters) {
            return average;
        }
        average = total_rating / raters;
        return average;
    }

    /**
     * Find the average rating for every movie that has been
     * rated by at least minimalRaters raters. Store each
     * such rating in a Rating object in which the movie ID
     * and the average rating are used in creating the Rating
     * object.
     *
     * @param minimalRaters
     * @return
     */
    public List<Rating> getAverageRatings(Integer minimalRaters)
    {
        List<Rating> avgRatings = new ArrayList<>();
        List<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id : movies)
        {
            double rating = getAverageByID(id, minimalRaters);
            if(rating > 0.0) {
                avgRatings.add(new Rating(id, rating));
            }
        }

        return avgRatings;
    }

    /**
     * Create and return an List of type Rating of all the movies
     * that have at least minimalRaters ratings and satisfies the
     * filter criteria.
     *
     * @param minimalRaters the minimum number of ratings a movie must have
     * @param filterCriteria
     * @return
     */
    public List<Rating> getAverageRatingsByFilter(Integer minimalRaters, Filter filterCriteria)
    {
        List<Rating> avgRatings = new ArrayList<>();
        List<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for(String id : myMovies)
        {
            double rating = getAverageByID(id, minimalRaters);
            if(rating > 0.0) {
                avgRatings.add(new Rating(id, rating));
            }
        }
        return avgRatings;
    }
}
