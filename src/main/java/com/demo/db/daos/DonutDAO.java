/**
 * File: DonutDAO.java
 * User: Rich Lopez
 * Date: 14, Oct 2018
 */
package com.demo.db.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.api.Donut;
import com.demo.util.DonutMapper;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Data Access Object for objects of type {@link Donut}.
 *
 * @version 1.0.0
 * @author Rich Lopez
 * @since 1.0.0
 */
public class DonutDAO {

    /**
     * The collection of Donuts
     */
    final MongoCollection<Document> donutCollection;

    /**
     * Constructor.
     *
     * @param donutCollection the collection of donuts.
     */
    public DonutDAO(final MongoCollection<Document> donutCollection) {
        this.donutCollection = donutCollection;
    }

    /**
     * Find all donuts.
     *
     * @return the donuts.
     */
    public List<Donut> getAll() {
        final MongoCursor<Document> donuts = donutCollection.find().iterator();
        final List<Donut> donutsFind = new ArrayList<>();
        try {
            while (donuts.hasNext()) {
                final Document donut = donuts.next();
                donutsFind.add(DonutMapper.map(donut));
            }
        } finally {
            donuts.close();
        }
        return donutsFind;
    }

    /**
     * Get one document find in other case return null.
     *
     * @param id the identifier for find.
     * @return the Donut find.
     */
    public Donut getOne(final ObjectId id) {
        final Optional<Document> donutFind = Optional.ofNullable(donutCollection.find(new Document("_id", id)).first());
        return donutFind.isPresent() ? DonutMapper.map(donutFind.get()) : null;
    }

    public void save(final Donut donut){
        final Document saveDonut =new Document("price", donut.getPrice())
                                      .append("flavor", donut.getFlavor());
        donutCollection.insertOne(saveDonut);
    }


    /**
     * Update a register.
     *
     * @param id the identifier.
     * @param donut the object to update.
     */
    public void update(final ObjectId id, final Donut donut) {
        donutCollection.updateOne(new Document("_id", id),
                new Document("$set", new Document("price", donut.getPrice())
                        .append("flavor", donut.getFlavor()))
        );
    }

    /**
     * Delete a register.
     * @param id    the identifier.
     */
    public void delete(final ObjectId id){
        donutCollection.deleteOne(new Document("_id", id));
    }
}
