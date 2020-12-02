package com.recomendationSystem.week.one;

import com.recomendationSystem.week.one.models.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George Lykoudis
 * @version 02/12/2020
 */
public class FirstRatings
{
    List<Movie>          movies;
    Map<String, Integer> moviesPerDirector;

    public FirstRatings()
    {
        movies = new ArrayList<Movie>();
    }

    private void initialize()
    {
        if(movies == null) {
            movies = new ArrayList<Movie>();
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
        movies = loadMovies(fileName);
        System.out.println("There are " + movies.size() + " in file " + fileName);
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
}
