/**
 * File: MongoDBManaged.java
 * User: Rich Lopez
 * Date: 20, Oct 2018
 */
package com.demo.db;

import com.mongodb.client.MongoClient;
import io.dropwizard.lifecycle.Managed;

/**
 * This is used for manage the connection to MongoDB.
 * @version 1.0.0
 * @since 1.0.0
 * @author Rich Lopez 
 */
public class MongoDBManaged implements Managed {

    /**
     * The mongoDB client.
     */
    private MongoClient mongoClient;

    /**
     * Constructor.
     * @param mongoClient   the mongoDB client.
     */
    public MongoDBManaged(final MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    public void start() throws Exception {
    }

    @Override
    public void stop() throws Exception {
        mongoClient.close();
    }
}
