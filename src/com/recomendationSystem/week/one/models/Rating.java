package com.recomendationSystem.week.one.models;

/**
 * @author George Lykoudis
 * @version 02/12/2020
 */
public class Rating implements Comparable<Rating>
{
    private String item;
    private double value;

    public Rating(String item, double value)
    {
        this.item = item;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "item='" + item + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public int compareTo(Rating r) {
        if(this.value < r.value) { return -1; }
        if(this.value > r.value) { return 1; }
        return 0;
    }
}
