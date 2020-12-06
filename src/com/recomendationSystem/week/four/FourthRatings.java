package com.recomendationSystem.week.four;

import com.recomendationSystem.database.MovieDatabase;
import com.recomendationSystem.database.RaterDatabase;
import com.recomendationSystem.models.Filter.Filter;
import com.recomendationSystem.models.Filter.TrueFilter;
import com.recomendationSystem.models.Rater;
import com.recomendationSystem.models.Rating;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 06/12/2020
 */
public class FourthRatings
{
    public FourthRatings(String fileName) {
        RaterDatabase.addRatings(fileName);
    }

    /**
     *
     * @param id
     * @param minimalRaters
     * @return a double representing the average movie rating for
     *         this ID if there are at least minimalRaters ratings.
     */
    public double getAverageByID(String id, Integer minimalRaters)
    {
        if(minimalRaters == null) {
            return 0.0;
        }

        double total_rating = 0.0;
        int ratersCount = 0;
        for(Rater rater : RaterDatabase.getRaters())
        {
            double rating = rater.getRating(id);
            if(rating == -1) {
                continue;
            }
            total_rating += rating;
            ratersCount++;
        }

        if(ratersCount < minimalRaters) {
            return 0.0;
        }

        return total_rating / ratersCount;
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
