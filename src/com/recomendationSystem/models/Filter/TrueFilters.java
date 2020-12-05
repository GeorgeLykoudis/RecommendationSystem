package com.recomendationSystem.models.Filter;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class TrueFilters implements Filter
{
    @Override
    public boolean satisfies(String id) {
        return true;
    }
}
