package com.tengen;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class RemoveLowestHW31 {

    /**
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {

        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("school");
        DBCollection collection = database.getCollection("students");
        System.out.println(collection.count());

        for (DBObject student : collection.find()) {
            @SuppressWarnings("unchecked")
            List<DBObject> list = (ArrayList<DBObject>) student.get("scores");
            DBObject minHWObject = null;
            Double minScore = -1.00;
            for (DBObject score : list) {
                if (score.get("type").equals("homework")) {
                    Double intScore = (Double) score.get("score");
                    if (minScore.equals(-1.00)) {
                        minScore = intScore;
                        minHWObject = score;
                    } else {
                        if (intScore < minScore) {
                            minScore = intScore;
                            minHWObject = score;
                        }
                    }

                }
            }
            if (minHWObject != null) {
                list.remove(minHWObject);
            }
            student.put("scores", list);
            collection.update(new BasicDBObject("_id", student.get("_id")), student, false, false);
            System.out.println(student);
        }

    }
}
