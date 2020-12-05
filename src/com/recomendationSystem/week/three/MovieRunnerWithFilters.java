package com.recomendationSystem.week.three;

import com.recomendationSystem.database.MovieDatabase;
import com.recomendationSystem.models.Filter.*;
import com.recomendationSystem.models.Rating;

import java.util.Collections;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class MovieRunnerWithFilters
{
    ThirdRatings thirdRatings;

    public MovieRunnerWithFilters()
    {
        thirdRatings = new ThirdRatings();
    }

    public MovieRunnerWithFilters(String movieFile, String raterFile)
    {
        thirdRatings = new ThirdRatings(raterFile);
        System.out.println("read data for: " + thirdRatings.getRaterSize() + " raters");
        MovieDatabase.initialize(movieFile);
        System.out.println("read data for: " + MovieDatabase.getSize() + " movies");
    }

    public void printAverageRatings(int minimalRaters)
    {
        System.out.println("printAverageRatings");
        List<Rating> ratings = thirdRatings.getAverageRatings(minimalRaters);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            System.out.println("Movie " + MovieDatabase.getTitle(rating.getItem()) +
                    " with rating " + rating.getValue());
        }
    }

    public void printAverageRatingsByYear(int minimalRaters, int year)
    {
        System.out.println("printAverageRatingsByYear");
        List<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, new YearsAfterFilter(year));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            String id = rating.getItem();
            System.out.println("Movie " + MovieDatabase.getTitle(id) +
                    " year " + MovieDatabase.getYear(id) +
                    " with rating " + rating.getValue());
        }
    }

    public void printAverageRatingsByGenre(int minimalRaters, String genre)
    {
        System.out.println("printAverageRatingsByGenre");
        List<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, new GenreFilter(genre));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            String id = rating.getItem();
            System.out.println("Movie " + MovieDatabase.getTitle(id) +
                    " with rating " + rating.getValue() +
                    "\n\tgenre " + MovieDatabase.getGenres(id));
        }
    }

    public void printAverageRatingsByMinutes(int minimalRaters, int minMinutes, int maxMinutes)
    {
        System.out.println("printAverageRatingsByMinutes");
        List<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(minMinutes, maxMinutes));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            String id = rating.getItem();
            System.out.println("Movie " + MovieDatabase.getTitle(id) +
                    " with rating " + rating.getValue() +
                    " Time: " + MovieDatabase.getMinutes(id));
        }
    }

    public void printAverageRatingsByDirector(int minimalRaters, String director)
    {
        System.out.println("printAverageRatingsByDirector");
        List<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter(director));
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            String id = rating.getItem();
            System.out.println("Movie " + MovieDatabase.getTitle(id) +
                    " with rating " + rating.getValue() +
                    " director: " + MovieDatabase.getDirector(id));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(int minimalRaters, int year, String genre)
    {
        System.out.println("printAverageRatingsByYearAfterAndGenre");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new GenreFilter(genre));
        allFilters.addFilter(new YearsAfterFilter(year));
        List<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
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

    public void printAverageRatingsByDirectorsAndMinutes(int minimalRaters, int minMinutes, int maxMinutes, String director)
    {
        System.out.println("printAverageRatingsByDirectorsAndMinutes");
        AllFilters allFilters = new AllFilters();
        allFilters.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        allFilters.addFilter(new DirectorsFilter(director));
        List<Rating> ratings = thirdRatings.getAverageRatingsByFilter(minimalRaters, allFilters);
        System.out.println("found "+ ratings.size() + " movies");
        Collections.sort(ratings);
        for(Rating rating : ratings)
        {
            String id = rating.getItem();
            System.out.println("Movie " + MovieDatabase.getTitle(id) +
                    " Time: " + MovieDatabase.getMinutes(id) +
                    " with rating " + rating.getValue() +
                    "\n\tdirector " + MovieDatabase.getDirector(id));
        }
    }
}
