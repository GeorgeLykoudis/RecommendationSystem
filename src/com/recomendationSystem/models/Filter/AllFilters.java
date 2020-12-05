package com.recomendationSystem.models.Filter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class AllFilters implements Filter
{
    private List<Filter> filters;

    public AllFilters()
    {
        filters = new ArrayList<>();
    }

    public void addFilter(Filter f)
    {
        filters.add(f);
    }

    @Override
    public boolean satisfies(String id) {
        for(Filter f : filters) {
            if(!f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }
}
