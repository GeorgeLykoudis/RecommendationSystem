package com.recomendationSystem.week.one;

import com.recomendationSystem.week.one.models.Movie;
import com.recomendationSystem.week.one.models.Rater;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;

/**
 * @author George Lykoudis
 * @version 02/12/2020
 */
public class FirstRatings
{
    List<Movie>          movies;
    List<Rater>          raters;

    Map<String, Integer> moviesPerDirector;
    Map<String, Integer> ratingsPerRater;
    Map<String, Integer> ratingsPerMovie;

    public FirstRatings()
    {
        movies = new ArrayList<Movie>();
    }

    private void initialize()
    {
        if(movies == null) {
            movies = new ArrayList<Movie>();
        }
        if(raters == null) {
            raters = new ArrayList<Rater>();
        }
    }

    /**
     * Reads a fileName and loads the data from movies
     * in a list with Movie objects.
     *
     * @param fileName
     * @return
     */
    public List<Movie> loadMovies(String fileName)
    {
        try(Reader in = new FileReader(fileName))
        {
            initialize();

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for(CSVRecord record : records)
            {
                String id       = record.get("id");
                String title    = record.get("title");
                String year     = record.get("year");
                String country  = record.get("country");
                String genres   = record.get("genre");
                String director = record.get("director");
                String minutes  = record.get("minutes");
                String poster   = record.get("poster");

                movies.add(new Movie(id, title, year, genres, director, country, minutes, poster));
            }
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return this.movies;
    }

    /**
     * Test for loadMovies function.
     *
     * @param fileName
     */
    public void TestLoadMovies(String fileName)
    {
        List<Movie> testMovies = loadMovies(fileName);
        System.out.println("There are " + testMovies.size() + " in file " + fileName);
        printMovies();
        System.out.println("There are " + countByGenre("Comedy") + " movies that belong to genre Comedy.");
        System.out.println("There are " + countMoviesLongerThanMinutes(150) + " movies longer than 150 minutes.");

        moviesPerDirector = countMoviesPerDirector();
        for(Map.Entry<String,Integer> entry : moviesPerDirector.entrySet()) {
            System.out.println("Director " + entry.getKey() + " has " + entry.getValue() + " movies");
        }
        System.out.println(moviesPerDirector.toString());
        System.out.println("Maximum movies directed : " + maxNumberOfMoviesDirected());
        System.out.println(directorsWithMaximumNumberOfMoviesDirected().toString());
    }

    private void printMovies() {
        for(Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }

    /**
     * Determine how many movies include the "genre" genre.
     *
     * @param genre
     * @return
     */
    public int countByGenre(String genre)
    {
        int count = 0;
        for(Movie movie : movies)
        {
            String[] genres = movie.getGenres().split(",");
            for(String g : genres)
            {
                if(g.trim().equals(genre))
                {
                    count++;
                    break;
                }
            }
        }

        return count;
    }

    /**
     * Determine how many movies are greater than
     * "minutes" minutes in length.
     *
     * @param minutes
     * @return
     */
    public int countMoviesLongerThanMinutes(int minutes)
    {
        int count = 0;
        for(Movie movie : movies)
        {
            if(movie.getMinutes() > minutes) {
                count++;
            }
        }
        return count;
    }

    /**
     * Determine the maximum number of movies by any director,
     * and who the directors are that directed that many movies.
     *
     * @return
     */
    public Map<String,Integer> countMoviesPerDirector()
    {
        Map<String, Integer> moviesPerDirector = new HashMap<>();
        for(Movie movie : movies)
        {
            String[] directors = movie.getDirector().split(",");
            for(String d : directors)
            {
                d = d.trim();
                if(!moviesPerDirector.containsKey(d)) {
                    moviesPerDirector.put(d, 1);
                }
                else {
                    moviesPerDirector.put(d, moviesPerDirector.get(d) + 1);
                }
            }
        }
        return moviesPerDirector;
    }

    /**
     * Determine the maximum number of movies by any director.
     *
     * @return
     */
    private int maxNumberOfMoviesDirected()
    {
        int max = -1;
        for(Integer value : moviesPerDirector.values())
        {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Determine the maximum number of movies by any director,
     * and who the directors are that directed that many movies.
     *
     * @return
     */
    public List<String> directorsWithMaximumNumberOfMoviesDirected()
    {
        int maxMovies = maxNumberOfMoviesDirected();
        if(maxMovies < 1) {
            return new ArrayList<>();
        }
        List<String> directors = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : moviesPerDirector.entrySet())
        {
            if(entry.getValue() == maxMovies) {
                directors.add(entry.getKey());
            }
        }
        return directors;
    }

    /**
     * Reads a fileName and loads the data from raters
     * in a list with Rater objects.
     * @param fileName
     * @return
     */
    public List<Rater> loadRaters(String fileName)
    {
        try(Reader in = new FileReader(fileName))
        {
            initialize();

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for(CSVRecord record : records)
            {
                String rater_id = record.get("rater_id");
                String movie_id = record.get("movie_id");
                double rating   = Double.parseDouble(record.get("rating"));
                String date     = record.get("time");

                Rater rater = new Rater(rater_id);
                int index = raters.indexOf(rater);
                if(index == -1) {
                    rater.addRating(movie_id, rating);
                    raters.add(rater);
                }
                else {
                    raters.get(index).addRating(movie_id, rating);
                }
            }
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return this.raters;
    }

    /**
     * Test for loadRaters function.
     *
     * @param fileName
     */
    public void TestLoadRaters(String fileName)
    {
        List<Rater> raters = loadRaters(fileName);
        System.out.println("There are " + raters.size() + " raters in file " + fileName);
//        printRaters();
        String raterId = "193";
        System.out.println("Rater " + raterId + " has " + numOfRatings(raterId) + " ratings");
        ratingsPerRater = countRatingsPerRaters();
//        System.out.println(ratingsPerRater.toString());
        System.out.println("Maximum ratings " + maximumRatingsMade());
        System.out.println("Rater with maximum ratings " + ratersWithMaximumNumberOfRatings().toString());

        ratingsPerMovie = countRatingsPerMovie();
//        System.out.println(ratingsPerMovie.toString());
        String movie = "1798709";
        System.out.println("Movie " + movie + " has " + numberOfRatingsForMovie(movie) + " ratings");
        System.out.println("Number of distinct movies : " + numberOfDistinctMovies());
    }

    private void printRaters()
    {
        for(Rater rater : raters) {
            System.out.println(rater.toString());
        }
    }

    /**
     * Determine the number of Ratings a Rater has made.
     *
     * @param raterId
     * @return
     */
    public int numOfRatings(String raterId)
    {
        for(Rater rater : raters) {
            if(rater.getMyID().equals(raterId)) {
                return rater.numRatings();
            }
        }
        return 0;
    }

    /**
     * Find the maximum number of ratings all raters.
     *
     * @return
     */
    public int maximumRatingsMade()
    {
        int max = -1;
        for(Map.Entry<String, Integer> entry : ratingsPerRater.entrySet())
        {
            if(entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        return max;
    }

    /**
     * Determine number of ratings each Rater made.
     *
     * @return a hashMap where the key is the Rater
     *         id and the value the number of his/her
     *         ratings.
     */
    public Map<String, Integer> countRatingsPerRaters()
    {
        Map<String, Integer> newRatingsPerRater = new HashMap<>();
        for(Rater rater : raters)
        {
            newRatingsPerRater.put(rater.getMyID(), rater.numRatings());
        }
        return newRatingsPerRater;
    }

    /**
     * Find the raters that had the maximum number
     * of ratings.
     *
     * @return
     */
    public List<String> ratersWithMaximumNumberOfRatings()
    {
        int max = maximumRatingsMade();
        List<String> maxRaters = new ArrayList<>();
        for(Map.Entry<String,Integer> entry : ratingsPerRater.entrySet())
        {
            if(entry.getValue() == max) {
                maxRaters.add(entry.getKey());
            }
        }
        return maxRaters;
    }

    /**
     * Find the number of ratings each movie had.
     *
     * @return a hashmap where the key is the movie
     *         id and the value is the number of
     *         ratings it has.
     */
    public Map<String, Integer> countRatingsPerMovie()
    {
        Map<String, Integer> mewRatingsPerMovie = new HashMap<>();

        for(Rater rater : raters)
        {
            List<String> moviesRated = rater.getItemsRated();
            for(String movie : moviesRated)
            {
                if(!mewRatingsPerMovie.containsKey(movie)) {
                    mewRatingsPerMovie.put(movie, 1);
                }
                else {
                    mewRatingsPerMovie.put(movie, mewRatingsPerMovie.get(movie) + 1);
                }
            }
        }

        return mewRatingsPerMovie;
    }

    /**
     * Get the number of ratings a specified movie has.
     *
     * @param movieId
     * @return
     */
    public int numberOfRatingsForMovie(String movieId)
    {
        return ratingsPerMovie.get(movieId);
    }

    /**
     * Get the number of distinct movies appear
     * in the raters list.
     *
     * @return
     */
    public int numberOfDistinctMovies()
    {
        Set<String> movieSet = new HashSet<>();
        for(String key : ratingsPerMovie.keySet())
        {
            movieSet.add(key);
        }
        return movieSet.size();
    }
}
