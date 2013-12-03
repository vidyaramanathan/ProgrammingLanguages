package com.tengen;

import java.net.UnknownHostException;
import java.util.List;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class UpdateRemoveTest {

    /**
     * @param args
     * @throws UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("students");
        DBCollection collection = database.getCollection("grades");
        System.out.println(collection.count());

        DBObject dbObj = new BasicDBObject("type", "homework");
        DBObject sortStudent = new BasicDBObject("student_id", 1);
        sortStudent.put("score", 1);
        DBCursor cursor = collection.find(dbObj).sort(sortStudent);
        System.out.println(cursor.count());
        // .sort(sortScoreObj);
        int currStudentId = -1;
        int lastStudentId = -1;
        int removableCount = 0;
        try {
            while (cursor.hasNext()) {
                DBObject object = cursor.next();
                // System.out.println("Object " + object);
                currStudentId = (Integer) object.get("student_id");
                System.out.println("Curr Id -> " + currStudentId + ", Last Id -> " + lastStudentId);
                if (currStudentId != lastStudentId) {
                    System.out.println("Remove -> " + object);
                    removableCount++;
                    collection.remove(object);
                }
                lastStudentId = currStudentId;

            }
        } finally {
            cursor.close();
        }

        System.out.println(collection.count() + ", Removable count " + removableCount);
        // Integer currStudentId = 0;
        // Integer lastStudentId = 0;
        // Integer removableCount = 0;
        // List<DBObject> list = cursor.toArray();
        // for (int i = 0; i < list.size(); i++) {
        //
        // DBObject object = list.get(i);
        // System.out.println("Object -> " + object);
        // currStudentId = (Integer) object.get("student_id");
        // if (i != 0) {
        // System.out.println("Curr ID -> " + currStudentId + ", lastStudentId -> " + lastStudentId);
        // if (currStudentId != lastStudentId) {
        // list.remove(i);
        // System.out.println("Removed -> " + object);
        // removableCount++;
        // }
        // }
        // lastStudentId = currStudentId;
        //
        // }
        // System.out.println(list.size() + ", Removable count " + removableCount);
        // cursor.close();
    }

}
