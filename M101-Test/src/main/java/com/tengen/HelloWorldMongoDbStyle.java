package com.tengen;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

public class HelloWorldMongoDbStyle {

	public static void main(String args[]) throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(new ServerAddress("localhost", 27017));
		
		DB database = mongoClient.getDB("course");
		
	}
}
