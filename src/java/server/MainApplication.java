package server;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import server.config.AppConfiguration;
import server.resources.TimeOfDayResource;
import server.resources.MovieResource;
import server.healthcheck.AppHealthCheck;

/**
 * Main application
 */
public class MainApplication extends Application<AppConfiguration> {
    final static Logger logger = LoggerFactory.getLogger(MainApplication.class);
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public String getName() {
        return "Test Server";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // framework bootstrap initialization
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        try {
            // application initialization goes here
            logger.info("Starting...");
            environment.healthChecks().register("app", new AppHealthCheck());
            //timeOfDay
            final TimeOfDayResource todResource = new TimeOfDayResource();
            environment.jersey().register(todResource);
            //movie
            final MovieResource movResource = new MovieResource();
            environment.jersey().register(movResource);    
        } catch (Exception exc) {
            logger.error("Failed to initialize application, exiting...", exc);
            throw new RuntimeException();
        }

    }
}
