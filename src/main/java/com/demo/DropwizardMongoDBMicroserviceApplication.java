/**
 * File: DropwizardMongoDBMicroserviceApplication.java
 * User: Rich Lopez
 * Date: 13, Oct 2018
 */
package com.demo;

import com.demo.db.MongoDBFactoryConnection;
import com.demo.db.daos.DonutDAO;
import com.demo.db.MongoDBManaged;
import com.demo.health.DropwizardMongoDBHealthCheck;
import com.demo.resources.DonutResource;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class start application.
 *
 * @author Rich Lopez
 * @version 1.0.0
 * @since 1.0.0
 */
public class DropwizardMongoDBMicroserviceApplication extends Application<DropwizardMongoDBMicroserviceConfiguration> {

    /**
     * Logger class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DropwizardMongoDBMicroserviceApplication.class);

    /**
     * Entry point for start Application.
     *
     * @param args the args.
     * @throws Exception when the app can not start.
     */
    public static void main(final String[] args) throws Exception {
        LOGGER.info("Start application.");
        new DropwizardMongoDBMicroserviceApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardMongoDBMicroservice";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardMongoDBMicroserviceConfiguration> bootstrap) {
        bootstrap.addBundle(new SwaggerBundle<DropwizardMongoDBMicroserviceConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                        final DropwizardMongoDBMicroserviceConfiguration dropwizardMongoDBMicroserviceConfiguration) {
                return dropwizardMongoDBMicroserviceConfiguration.getSwaggerBundleConfiguration();
            }
        });
    }

    @Override
    public void run(final DropwizardMongoDBMicroserviceConfiguration configuration,
                    final Environment environment) {

        final MongoDBFactoryConnection mongoDBManagerConn = new MongoDBFactoryConnection(configuration.getMongoDBConnection());

        final MongoDBManaged mongoDBManaged = new MongoDBManaged(mongoDBManagerConn.getClient());

        final DonutDAO donutDAO = new DonutDAO(mongoDBManagerConn.getClient()
                .getDatabase(configuration.getMongoDBConnection().getDatabase())
                .getCollection("donuts"));

        environment.lifecycle().manage(mongoDBManaged);
        environment.jersey().register(new DonutResource(donutDAO));
        environment.healthChecks().register("DropwizardMongoDBHealthCheck",
                new DropwizardMongoDBHealthCheck(mongoDBManagerConn.getClient()));
    }

}
