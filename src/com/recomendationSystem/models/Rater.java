package com.recomendationSystem.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public interface Rater
{
    public String getMyID();
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public double getRating(String item);
    public int numRatings();
    public List<String> getItemsRated();
    public ArrayList<Rating> getRatings();
}
