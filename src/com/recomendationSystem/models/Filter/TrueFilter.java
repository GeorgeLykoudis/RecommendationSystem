package com.recomendationSystem.models.Filter;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class TrueFilter implements Filter
{
    @Override
    public boolean satisfies(String id) {
        return true;
    }
}
