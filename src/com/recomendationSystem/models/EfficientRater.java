package com.recomendationSystem.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George Lykoudis
 * @version 05/12/2020
 */
public class EfficientRater implements Rater
{
    private String              myID;
    private Map<String, Rating> myRatings; // key is the movieId, and value is assosiated with its rating

    public EfficientRater(String raterId)
    {
        myRatings = new HashMap<String, Rating>();
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
        myRatings.put(item, new Rating(item, rating));
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
        return myRatings.containsKey(item);
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
        Rating rating = myRatings.get(item);
        if(rating == null)
            return -1;

        return rating.getValue();
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
        for(Rating rating : myRatings.values()) {
            list.add(rating.getItem());
        }
        return list;
    }

    /**
     *
     * @return  an ArrayList of Ratings representing
     *          all the items that have been rated.
     */
    public ArrayList<Rating> getRatings()
    {
        ArrayList<Rating> ratedItems = new ArrayList<>();
        for(Rating rating : myRatings.values()) {
            ratedItems.add(rating);
        }
        return ratedItems;
    }

    @Override
    public String toString() {
        return "Rater{" +
                "myID='" + myID + '\'' +
                ", myRatings=" + myRatings.toString() +
                '}';
    }

    @Override
    public int hashCode() {
        return myID.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(obj instanceof PlainRater)
        {
            PlainRater rater = (PlainRater) obj;
            return rater.getMyID().equals(this.myID);
        }

        return false;
    }
}
