package com.tengen;

import java.io.IOException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class Question8 {

    public static void main(String[] args) throws IOException {
        MongoClient c = new MongoClient(new MongoClientURI("mongodb://localhost"));
        DB db = c.getDB("test");
        DBCollection animals = db.getCollection("animals");

        BasicDBObject animal = new BasicDBObject("animal", "monkey");

        animals.insert(animal);

        System.out.println("Collection 1: " + animals);

        animal.removeField("animal");

        System.out.println("Animal 1 : " + animal);
        System.out.println("Collection 2: " + animals);

        animal.append("animal", "cat");

        System.out.println("Animal 2 : " + animal);
        System.out.println("Collection 3: " + animals);

        animals.insert(animal);

        System.out.println("Animal 3 : " + animal);
        System.out.println("Collection 4: " + animals);

        animal.removeField("animal");

        System.out.println("Animal 4 : " + animal);
        System.out.println("Collection 5: " + animals);

        animal.append("animal", "lion");

        System.out.println("Animal 5 : " + animal);
        System.out.println("Collection 6: " + animals);

        animals.insert(animal);

        System.out.println("Animal 6 : " + animal);
        System.out.println("Collection 7: " + animals);

    }

}
