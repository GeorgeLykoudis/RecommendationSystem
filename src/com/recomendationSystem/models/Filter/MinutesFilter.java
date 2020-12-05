package com.recomendationSystem.models.Filter;

import com.recomendationSystem.database.MovieDatabase;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class MinutesFilter implements Filter
{
    private int minMinutes;
    private int maxMinutes;

    public MinutesFilter(int min, int max)
    {
        minMinutes = min;
        maxMinutes = max;
    }

    @Override
    public boolean satisfies(String movieId) {
        int mins = MovieDatabase.getMinutes(movieId);
        return (mins >= minMinutes && mins <= maxMinutes) ? true : false;
    }
}
