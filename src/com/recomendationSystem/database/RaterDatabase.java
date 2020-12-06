package com.recomendationSystem.database;

import com.recomendationSystem.models.EfficientRater;
import com.recomendationSystem.models.Rater;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author George Lykoudis
 * @version 06/12/2020
 */
public class RaterDatabase
{
    private static Map<String, Rater> ourRaters;

    private static void initialize()
    {
        // this method is only called from addRatings
        if (ourRaters == null) {
            ourRaters = new HashMap<String,Rater>();
        }
    }

    public static void initialize(String filename)
    {
        if (ourRaters == null) {
            ourRaters= new HashMap<String,Rater>();
            addRatings(filename);
        }
    }

    public static void addRatings(String filename)
    {
        initialize();
        try(Reader in = new FileReader(filename)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
            for (CSVRecord record : records) {
                String id = record.get("rater_id");
                String item = record.get("movie_id");
                String rating = record.get("rating");
                addRaterRating(id, item, Double.parseDouble(rating));
            }
        }
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void addRaterRating(String raterID, String movieID, double rating)
    {
        initialize();
        Rater rater =  null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        }
        else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID,rater);
        }
        rater.addRating(movieID,rating);
    }

    public static Rater getRater(String id) {
        initialize();

        return ourRaters.get(id);
    }

    public static List<Rater> getRaters() {
        initialize();
        List<Rater> list = new ArrayList<Rater>(ourRaters.values());

        return list;
    }

    public static int size() {
        return ourRaters.size();
    }
}
