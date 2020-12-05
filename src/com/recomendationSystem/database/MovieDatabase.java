package com.recomendationSystem.database;

import com.recomendationSystem.models.Filter.Filter;
import com.recomendationSystem.models.Movie;
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
 * @version 05/12/2020
 */
public class MovieDatabase
{
    public static final String path = "D:\\workspace\\github\\RecomendationSystem\\src\\com\\recomendationSystem\\data\\";
    private static Map<String, Movie> ourMovies;

    public static void initialize(String moviefile)
    {
        if(ourMovies == null) {
            ourMovies = new HashMap<>();
            loadMovies(moviefile);
        }
    }

    private static void initialize()
    {
        if(ourMovies == null) {
            ourMovies = new HashMap<>();
            loadMovies(path + "ratedmoviesfull.csv");
        }
    }

    private static void loadMovies(String fileName)
    {
        try(Reader in = new FileReader(fileName))
        {
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

                ourMovies.put(id, new Movie(id, title, year, genres, director, country, minutes, poster));
            }
        }
        catch(FileNotFoundException ex) {
            ex.getStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean containsId(String id)
    {
        initialize();
        return ourMovies.containsKey(id);
    }

    public static Movie getMovie(String id)
    {
        initialize();
        return ourMovies.get(id);
    }

    public static Integer getYear(String id)
    {
        initialize();
        Movie movie = ourMovies.get(id);
        return (movie != null) ? movie.getYear() : null;
    }

    public static String getTitle(String id)
    {
        initialize();
        Movie movie = ourMovies.get(id);
        return (movie != null) ? movie.getTitle() : null;
    }

    public static String getPoster(String id)
    {
        initialize();
        Movie movie = ourMovies.get(id);
        return (movie != null) ? movie.getPoster() : null;
    }

    public static Integer getMinutes(String id)
    {
        initialize();
        Movie movie = ourMovies.get(id);
        return (movie != null) ? movie.getMinutes() : null;
    }

    public static String getCountry(String id)
    {
        initialize();
        Movie movie = ourMovies.get(id);
        return (movie != null) ? movie.getCountry() : null;
    }

    public static String getGenres(String id)
    {
        initialize();
        Movie movie = ourMovies.get(id);
        return (movie != null) ? movie.getGenres() : null;
    }

    public static String getDirector(String id)
    {
        initialize();
        Movie movie = ourMovies.get(id);
        return (movie != null) ? movie.getDirector() : null;
    }

    public static int getSize()
    {
        initialize();
        return ourMovies.size();
    }

    public static List<String> filterBy(Filter f)
    {
        initialize();
        List<String> movies = new ArrayList<>();
        for(String id : ourMovies.keySet())
        {
            if(f.satisfies(id)) {
                movies.add(id);
            }
        }
        return movies;
    }
}
