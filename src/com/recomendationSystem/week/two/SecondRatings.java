package com.recomendationSystem.week.two;

import com.recomendationSystem.week.one.FirstRatings;
import com.recomendationSystem.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 03/12/2020
 */
public class SecondRatings
{
    private List<Movie> myMovies;
    private List<PlainRater> myRaters;

    public static final String path = "D:\\workspace\\github\\preparation\\Java\\RecomendationSystem\\src\\com\\recomendationSystem\\data\\";

    public SecondRatings() {
        // default constructor
        this(path + "ratedmoviesfull.csv", path + "ratings.csv");
    }

    /**
     * Constructor that uses FirstRatings loadMovies
     * and loadRaters functions.
     *
     * @param movieFile
     * @param ratingsFile
     */
    public SecondRatings(String movieFile, String ratingsFile)
    {
        FirstRatings firstRatings = new FirstRatings();
        myMovies = firstRatings.loadMovies(movieFile);
        myRaters = firstRatings.loadRaters(ratingsFile);
    }

    /**
     *
     * @return the size of myMovies list.
     */
    public int getMovieSize() {
        return myMovies.size();
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
        for(PlainRater rater : myRaters)
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
        for(Movie movie : myMovies)
        {
            String id = movie.getId();
            double rating = getAverageByID(id, minimalRaters);
            if(rating > 0.0) {
                avgRatings.add(new Rating(id, rating));
            }
        }

        return avgRatings;
    }

    /**
     *
     * @param id of a movie
     * @return the title of this movie.
     */
    public String getTitle(String id)
    {
        String title = "NO SUCH ID";
        for(Movie movie : myMovies) {
            if(movie.getId().equals(id)) {
                title = movie.getTitle();
                break;
            }
        }
        return title;
    }

    /**
     *
     * @param title of a movie
     * @return the id of this movie.
     */
    public String getID(String title)
    {
        String id = "NO SUCH TITLE";
        for(Movie movie : myMovies)
        {
            String curTitle = movie.getTitle().trim();
            if(curTitle.equals(title)) {
                id = movie.getId();
                break;
            }
        }
        return id;
    }
}
