package com.recomendationSystem.week.one;

import com.recomendationSystem.week.one.models.Movie;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 02/12/2020
 */
public class FirstRatings
{
    List<Movie> movies;

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
    }

    private void printMovies() {
        for(Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }
}
