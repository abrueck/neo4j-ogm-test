package de.brueckconputer.pia.business;

import org.neo4j.ogm.config.Configuration;

/**
 * Created by andreas on 16.05.16.
 */
public class BoltGraphDatabase extends GraphDatabase {


    @Override
    protected Configuration getConfiguration() {
        Configuration configuration = new Configuration();

        configuration.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setEncryptionLevel("NONE")
                .setTrustStrategy("TRUST_ON_FIRST_USE")
                .setTrustCertFile("file:///tmp/cert")
                .setURI("bolt://neo4j:secret@localhost");

        return configuration;
    }


}
