package com.tengen;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class PhotosharingCleanUp {

    /**
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("photoshare");
        DBCollection albumCollection = database.getCollection("albums");
        System.out.println(albumCollection.count());

        DBCollection imageCollection = database.getCollection("images");
        System.out.println(imageCollection.count());

        // AggregationOutput output = albumCollection.aggregate(new BasicDBObject("$unwind", "$images"));

        List<Integer> imagesList = new ArrayList<Integer>();

        for (DBObject object : albumCollection.find()) {

            imagesList.addAll((List<Integer>) object.get("images"));
        }

        for (DBObject object : imageCollection.find()) {
            Integer imageId = (Integer) object.get("_id");
            if (imagesList.contains(imageId)) {

            } else {
                System.out.println("Removing object " + object);
                imageCollection.remove(object);
            }
        }
        System.out.println(imageCollection.count());
    }

}
