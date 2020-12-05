package com.recomendationSystem.models.Filter;

import com.recomendationSystem.database.MovieDatabase;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class GenreFilter implements Filter
{
    private String genre;

    public GenreFilter(String genre)
    {
        this.genre = genre;
    }

    @Override
    public boolean satisfies(String movieId)
    {
        String[] genres = MovieDatabase.getGenres(movieId).split(",");
        for(String g : genres) {
            if(g.trim().toLowerCase().equals(this.genre.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
