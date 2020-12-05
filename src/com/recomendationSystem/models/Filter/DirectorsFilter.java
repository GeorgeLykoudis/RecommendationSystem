package com.recomendationSystem.models.Filter;

import com.recomendationSystem.database.MovieDatabase;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class DirectorsFilter implements Filter
{
    String[] directors;

    public DirectorsFilter(String director)
    {
        this.directors = director.split(",");
    }

    private String trimLower(String str) {
        return str.trim().toLowerCase();
    }

    @Override
    public boolean satisfies(String movieId) {
        String[] movieDirectors = MovieDatabase.getDirector(movieId).split(",");
        for(String director : directors)
        {
            for (String movieDirector : movieDirectors)
            {
                if (trimLower(movieDirector).equals(trimLower(director))) {
                    return true;
                }
            }
        }
        return false;
    }
}
