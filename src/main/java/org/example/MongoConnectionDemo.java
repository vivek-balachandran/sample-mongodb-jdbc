package org.example;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public class MongoConnectionDemo {
    public static void main(String[] args) {
        // 1. Connection String (Default local MongoDB instance)
        String uri = "mongodb://localhost:27017";

        // 2. Connect to the Client
        try (MongoClient mongoClient = MongoClients.create(uri)) {

            // 3. Access the Database (creates it if it doesn't exist)
            MongoDatabase database = mongoClient.getDatabase("JFSD_Demo");

            // 4. Access the Collection (creates it if it doesn't exist)
            MongoCollection<Document> collection = database.getCollection("Students");

            // 5. Create a new Document (JSON-like structure)
            Document student = new Document("name", "Alice")
                    .append("department", "CSE")
                    .append("gpa", 8.5);

            // 6. Insert the Document
            collection.insertOne(student);
            System.out.println("Document inserted successfully!");

            // 7. Retrieve the Document
            Document result = collection.find().first();
            System.out.println("Retrieved Student: " + result.toJson());

            collection.find().forEach(System.out::println);

        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}