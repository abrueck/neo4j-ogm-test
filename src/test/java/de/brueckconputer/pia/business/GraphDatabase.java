package de.brueckconputer.pia.business;

import org.junit.BeforeClass;
import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.service.Components;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 * Created by andreas on 30.03.16.
 */
public abstract class GraphDatabase {

    public static final int DEPTH_ENTITY = 1;
    public static final String ENTITY_PACKAGE = "de.brueckcomputer.pia.business";

    private Session session;
    private SessionFactory sessionFactory;


    public GraphDatabase() {

        // Bolt driver

        sessionFactory = new SessionFactory(getConfiguration(), ENTITY_PACKAGE);
        session = sessionFactory.openSession();
    }

    public void purgeDatabase() {
        session.purgeDatabase();
    }

    public Session createSession() {
        return sessionFactory.openSession();

    }

    protected abstract Configuration getConfiguration();


}
