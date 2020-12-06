package com.recomendationSystem.week.four;

import com.recomendationSystem.database.MovieDatabase;
import com.recomendationSystem.database.RaterDatabase;
import com.recomendationSystem.models.Filter.AllFilters;
import com.recomendationSystem.models.Filter.GenreFilter;
import com.recomendationSystem.models.Filter.YearsAfterFilter;
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
}
