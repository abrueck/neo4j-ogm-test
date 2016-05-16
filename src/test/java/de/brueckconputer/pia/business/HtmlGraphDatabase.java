package de.brueckconputer.pia.business;

import de.brueckconputer.pia.business.GraphDatabase;
import org.neo4j.ogm.config.Configuration;

/**
 * Created by andreas on 16.05.16.
 */
public class HtmlGraphDatabase extends GraphDatabase {


    @Override
    protected Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://neo4j:secret@localhost:7474");

        return configuration;
    }


}
