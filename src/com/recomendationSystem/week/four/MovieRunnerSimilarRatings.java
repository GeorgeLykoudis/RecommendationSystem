package com.recomendationSystem.week.four;

import com.recomendationSystem.database.MovieDatabase;
import com.recomendationSystem.database.RaterDatabase;
import com.recomendationSystem.models.Filter.*;
import com.recomendationSystem.models.Rating;

import java.util.Collections;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 06/12/2020
 */
public class MovieRunnerSimilarRatings
{
    FourthRatings fourthRatings;

    public MovieRunnerSimilarRatings(String movieFile, String raterFile) {
        fourthRatings = new FourthRatings(raterFile);
        System.out.println("read data for: " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize(movieFile);
        System.out.println("read data for: " + MovieDatabase.getSize() + " movies");
    }

    public void printAverageRatings(int minimalRaters)
    {
        System.out.println("printAverageRatings");
        List<Rating> ratings = fourthRatings.getAverageRatings(minimalRaters);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            System.out.println("Movie " + MovieDatabase.getTitle(rating.getItem()) +
                    " with rating " + rating.getValue());
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre)
    {
        System.out.println("printAverageRatingsByYearAfterAndGenre");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new YearsAfterFilter(year));
        List<Rating> ratings = fourthRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            String id = rating.getItem();
            System.out.println("Movie " + MovieDatabase.getTitle(id) +
                    " year " + MovieDatabase.getYear(id) +
                    " with rating " + rating.getValue() +
                    "\n\tgenre " + MovieDatabase.getGenres(id));
        }
    }

    /**
     * Call getSimilarRatings function from FourthRatings, using
     * a rater's id, a number for the top number of similar raters,
     * and a number of minimal rateSimilarRatings.
     *
     * Prints from the list that is returned the title of the
     * recommended movie.
     *
     * @param id
     * @param numSimilarRaters
     * @param minimalRaters
     */
    public void printSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        System.out.println("\nprintSimilarRatings");
        List<Rating> ratings = fourthRatings.getSimilarRatings(id, numSimilarRaters, minimalRaters);
        if(ratings.size() > 0) {
            System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        }
    }

    /**
     * Call getSimilarRatingsByFilter function from FourthRatings, using
     * a rater's id, a number for the top number of similar raters,
     * and a number of minimal rateSimilarRatings.C hoose similar
     * movies based on GenreFilter.
     *
     * Prints from the list that is returned the title of the
     * recommended movie.
     *
     * @param id
     * @param numSimilarRaters
     * @param minimalRaters
     */
    public void printSimilarRatingsByGenre(String id, int numSimilarRaters, int minimalRaters, String genre)
    {
        System.out.println("\nprintSimilarRatingsByGenre");
        Filter f = new GenreFilter(genre);
        List<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        if(ratings.size() > 0) {
            System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        }
    }

    /**
     * Call getSimilarRatingsByFilter function from FourthRatings, using
     * a rater's id, a number for the top number of similar raters,
     * and a number of minimal rateSimilarRatings. Choose similar
     * movies based on DirectorFilter.
     *
     * Prints from the list that is returned the title of the
     * recommended movie.
     *
     * @param id
     * @param numSimilarRaters
     * @param minimalRaters
     */
    public void printSimilarRatingsByDirector(String id, int numSimilarRaters, int minimalRaters, String director)
    {
        System.out.println("\nprintSimilarRatingsByDirector");
        Filter f = new DirectorsFilter(director);
        List<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        if(ratings.size() > 0) {
            System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        }
    }

    /**
     * Call getSimilarRatingsByFilter function from FourthRatings, using
     * a rater's id, a number for the top number of similar raters,
     * and a number of minimal rateSimilarRatings. Choose similar
     * movies based on GenreFilter and MinutesFilter.
     *
     * Prints from the list that is returned the title of the
     * recommended movie.
     *
     * @param id
     * @param numSimilarRaters
     * @param minimalRaters
     */
    public void printSimilarRatingsByGenreAndMinutes(String id, int numSimilarRaters, int minimalRaters, String genre, int minMinutes, int maxMinutes)
    {
        System.out.println("\nprintSimilarRatingsByGenreAndMinutes");
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter(genre));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        List<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        if(ratings.size() > 0) {
            System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        }
    }

    /**
     * Call getSimilarRatingsByFilter function from FourthRatings, using
     * a rater's id, a number for the top number of similar raters,
     * and a number of minimal rateSimilarRatings. Choose similar
     * movies based on GenreFilter and MinutesFilter.
     *
     * Prints from the list that is returned the title of the
     * recommended movie.
     *
     * @param id
     * @param numSimilarRaters
     * @param minimalRaters
     */
    public void printSimilarRatingsByYearAfterAndMinutes(String id, int numSimilarRaters, int minimalRaters, int year, int minMinutes, int maxMinutes)
    {
        System.out.println("\nprintSimilarRatingsByYearAfterAndMinutes");
        AllFilters f = new AllFilters();
        f.addFilter(new YearsAfterFilter(year));
        f.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        List<Rating> ratings = fourthRatings.getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, f);
        if(ratings.size() > 0) {
            System.out.println(MovieDatabase.getTitle(ratings.get(0).getItem()));
        }
    }
}
