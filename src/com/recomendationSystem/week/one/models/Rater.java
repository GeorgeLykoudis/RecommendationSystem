package com.recomendationSystem.week.one.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author George Lykoudis
 * @version 02/12/2020
 */
public class Rater
{
    private String       myID;
    private List<Rating> myRatings;

    public Rater(String raterId)
    {
        myRatings = new ArrayList<>();
        this.myID = raterId;
    }

    /**
     * A new Rating is created and added to
     * myRatings list.
     *
     * @param item
     * @param rating
     */
    public void addRating(String item, double rating)
    {
        myID = item;
        myRatings.add(new Rating(item, rating));
    }

    public String getMyID() {
        return myID;
    }

    /**
     *
     * @param item
     * @return true if the "item" is in
     *         myRatings list, and false
     *         otherwise.
     */
    public boolean hasRating(String item)
    {
        for(Rating rating : myRatings)
        {
            if (rating.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param item
     * @return the double rating of the input "item"
     *         if it is inside myRatings list.
     *         Otherwise it returns -1.
     */
    public double getRating(String item)
    {
        for(Rating rating : myRatings) {
            if(rating.getItem().equals(item)) {
                return rating.getValue();
            }
        }
        return -1;
    }

    /**
     *
     * @return the size of the myRatings list.
     */
    public int numRatings() {
        return myRatings.size();
    }

    public List<String> getItemsRated() {
        List<String> list = new ArrayList<>();
        for(Rating rating : myRatings) {
            list.add(rating.getItem());
        }
        return list;
    }

    /**
     *
     * @return  an ArrayList of Strings representing
     *          a list of all the items that have been
     *          rated.
     */
    public ArrayList<String> getRatings()
    {
        ArrayList<String> ratedItems = new ArrayList<>();
        for(Rating rating : myRatings) {
            ratedItems.add(rating.getItem());
        }
        return ratedItems;
    }
}
