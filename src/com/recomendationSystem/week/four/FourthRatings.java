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

    /**
     * Downscale a list of ratings with 5.
     *
     * @param ratings
     * @return
     */
    private List<Rating> downScaleBy5(List<Rating> ratings)
    {
        List<Rating> newRatings = new ArrayList<>();
        for(Rating r : ratings) {
            newRatings.add(new Rating(r.getItem(), r.getValue() - 5));
        }
        return newRatings;
    }

    /**
     * @param me
     * @param r every Rater other than me
     *
     * @return the dot product
     *
     * Find the dot product between two Raters, concerning
     * their ratings over movies. Before computing the dot
     * product each Rating for both raters are scaled down
     * by the factor of 5.
     */
    private double dotProduct(Rater me, Rater r)
    {
        double similarities = 0.0;
        List<Rating> meRatings = downScaleBy5(me.getRatings());
        List<Rating> rRatings = downScaleBy5(r.getRatings());

        for(Rating meRating : meRatings)
        {
            for(Rating rRating : rRatings)
            {
                if(meRating.getItem().equals(rRating.getItem())) {
                    similarities += meRating.getValue() * rRating.getValue();
                }
            }
        }

        return similarities;
    }

    /**
     *
     * @param id the Rater id that will search for
     *           the similarity with every other
     *           Rater from the RaterDatabase.
     * @return   A list with Ratings where each
     *           item is the Rater-id other than
     *           id and the value is the dot
     *           product of the input Rater id
     *           Ratings and the rest Raters.
     *
     */
    private List<Rating> getSimilarities(String id)
    {
        List<Rating> ratings = new ArrayList<>();
        Rater rater = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters())
        {
            double similarities = 0.0;
            if(!(r.getMyID().equals(rater.getMyID()))) {
                similarities = dotProduct(rater, r);
                if(similarities > 0) {
                    ratings.add(new Rating(r.getMyID(), similarities));
                }
            }
        }
        Collections.sort(ratings, Collections.reverseOrder());

        return ratings;
    }

    /**
     *
     * @param id     Rater ID
     * @param numSimilarRaters
     * @param minimalRaters
     *
     * @return       ArrayList of type Rating, where the  "item" is the id of movie
     *               and as "value" the weighted average ratings from the top
     *               numSimilarRaters with positive ratings. Include only those
     *               movies that have at least minimalRaters ratings from those most
     *               similar raters (not just minimalRaters ratings overall).
     *
     *               For example, if minimalRaters is 3 and a movie has
     *               4 ratings but only 2 of those ratings were made by raters in the top
     *               numSimilarRaters, that movie should not be included. These Rating objects
     *               should be returned in sorted order by weighted average rating from
     *               largest to smallest ratings
     */
    public List<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        List<Rating> ratings        = new ArrayList<>();
        List<Rating> similarRatings = getSimilarities(id);
        List<String> movies         = MovieDatabase.filterBy(new TrueFilter());

        for(String movie : movies)
        {
            int numRaters = 0;
            double ratedWith = 0.0;

            for(int i = 0; i < numSimilarRaters; i++)
            {
                Rating rating = similarRatings.get(i); // the rater id with similar rating
                Rater  rater  = RaterDatabase.getRater(rating.getItem()); // retrieve raters data
                if(rater.hasRating(movie)) // check if this rater has rated this movie
                {
                    numRaters++;
                    ratedWith += rating.getValue() * rater.getRating(movie); // calculate a weighted rating
                }
            }
            if(numRaters >= minimalRaters) {
                ratings.add(new Rating(movie, ratedWith / numRaters));
            }
        }

        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }

    /**
     *
     * @param id     Rater ID
     * @param numSimilarRaters
     * @param minimalRaters
     * @parama filter choose movies based on this filter
     *
     * @return       ArrayList of type Rating, where the  "item" is the id of movie
     *               and as "value" the weighted average ratings from the top
     *               numSimilarRaters with positive ratings. Include only those
     *               movies that have at least minimalRaters ratings from those most
     *               similar raters (not just minimalRaters ratings overall) and match
     *               the filter criteria.
     *
     *               For example, if minimalRaters is 3 and a movie has
     *               4 ratings but only 2 of those ratings were made by raters in the top
     *               numSimilarRaters, that movie should not be included. These Rating objects
     *               should be returned in sorted order by weighted average rating from
     *               largest to smallest ratings
     */
    public List<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filter)
    {
        List<Rating> ratings        = new ArrayList<>();
        List<Rating> similarRatings = getSimilarities(id);
        List<String> movies         = MovieDatabase.filterBy(filter);

        for(String movie : movies)
        {
            int numRaters = 0;
            double ratedWith = 0.0;

            for(int i = 0; i < numSimilarRaters; i++)
            {
                Rating rating = similarRatings.get(i); // the rater id with similar rating
                Rater  rater  = RaterDatabase.getRater(rating.getItem()); // retrieve raters data
                if(rater.hasRating(movie)) // check if this rater has rated this movie
                {
                    numRaters++;
                    ratedWith += rating.getValue() * rater.getRating(movie); // calculate a weighted rating
                }
            }
            if(numRaters >= minimalRaters) {
                ratings.add(new Rating(movie, ratedWith / numRaters));
            }
        }

        Collections.sort(ratings, Collections.reverseOrder());
        return ratings;
    }
}
