package com.recomendationSystem.week.five;

import com.recomendationSystem.database.MovieDatabase;
import com.recomendationSystem.database.RaterDatabase;
import com.recomendationSystem.models.Filter.AllFilters;
import com.recomendationSystem.models.Filter.GenreFilter;
import com.recomendationSystem.models.Filter.MinutesFilter;
import com.recomendationSystem.models.Filter.YearsAfterFilter;
import com.recomendationSystem.models.Rating;
import com.recomendationSystem.week.four.FourthRatings;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 06/12/2020
 */
public class RecommendationRunner implements Recommender
{
    public static final String path = "D:\\workspace\\github\\RecommendationSystem\\src\\com\\recomendationSystem\\data\\";

    @Override
    public ArrayList<String> getItemsToRate() {
        AllFilters af = new AllFilters();
        String genre = "Action";
        int minMinutes = 80;
        int maxMinutes = 100;
        int year = 2015;
        af.addFilter(new GenreFilter(genre));
        af.addFilter(new MinutesFilter(minMinutes, maxMinutes));
        af.addFilter(new YearsAfterFilter(year));

        List<String> movies = MovieDatabase.filterBy(af);

        ArrayList<String> returnedMovies = new ArrayList<>();
        // Prints only the top 20 movies
        for(int i = 0; i < 20; i++)
        {
            returnedMovies.add(movies.get(i));
        }

        return returnedMovies;
    }

    @Override
    public void printRecommendationsFor(String webRaterID) {
        final String ratingsFileName = path + "ratings.csv";
        final String moviesFileName = path + "ratedmoviesfull.csv";
        MovieDatabase.initialize(moviesFileName);
        FourthRatings fourthRatings = new FourthRatings(ratingsFileName);

        System.out.println("<p>Read data for " + Integer.toString(RaterDatabase.size()) + " raters</p>");
        System.out.println("<p>Read data for " + Integer.toString(MovieDatabase.getSize()) + " movies</p>");

        int numSimilarRaters = 20;
        int minimalRaters    = 5;
        List<Rating> ratings = fourthRatings.getSimilarRatings(webRaterID, numSimilarRaters, minimalRaters);

        if(ratings.size() == 0) {
            System.out.println("No matching movies found.");
        }
        else
        {
            System.out.println("there are " + ratings.size() + " similar movies.");
            StringBuilder sb = new StringBuilder();

            final String header = "<table style=\"width:100%\"> <tr>"
                    + "<th>Movie Title</th> "
                    + "<th>Rating Value</th> "
                    + "<th>Genres</th> ";
            final String endOfHeader = "</tr> </header>";

            sb.append(header);
            // Prints only the top 20 movies
            for(int i = 0; i < 20; i++)
            {
                Rating rating = ratings.get(i);
                sb.append("<tr><td>");
                sb.append(MovieDatabase.getTitle(rating.getItem()));
                sb.append("</td><td>");
                sb.append(Double.toString(rating.getValue()));
                sb.append("</td><td>");
                sb.append(MovieDatabase.getGenres(rating.getItem()));
                sb.append("</td></tr>");
            }
            sb.append(endOfHeader);
            System.out.println(sb);
        }

    }
}
