package com.recomendationSystem.week.one;

import com.recomendationSystem.week.one.models.Movie;

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

    public List<Movie> loadMovies(String fileName)
    {
        try(Reader in = new FileReader(fileName))
        {
            movies = new ArrayList<>();

            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for(CSVRecord record : records)
            {
                if(record.get(0).equals("id")) {
                    continue;
                }
                String id = record.get(0);
                String title = record.get(1);
                String year = record.get(2);
                String country = record.get(3);
                String genres = record.get(4);
                String director = record.get(5);
                String minutes = record.get(6);
                String sticker = record.get(7);

                movies.add(new Movie(id, title, year, genres, director, country, minutes, sticker));
            }

            return movies;
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return List.of();
    }
}
