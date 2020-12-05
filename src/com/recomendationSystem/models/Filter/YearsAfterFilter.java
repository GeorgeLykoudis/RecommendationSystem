package com.recomendationSystem.models.Filter;

import com.recomendationSystem.database.MovieDatabase;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class YearsAfterFilter implements Filter
{
    private int year;

    public YearsAfterFilter(int year) {
        this.year = year;
    }

    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= year;
    }
}
