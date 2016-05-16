package de.brueckcomputer.pia.business.items.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

/**
 * Created by andreas on 21.03.16.
 */
@NodeEntity
public class OrganizationalUnit {

    @GraphId
    private Long id;

    @Property(name="client_id")
    private int clientId;

    private String name;

    public OrganizationalUnit() {
    }

    public OrganizationalUnit(int clientId, String name) {
        this.clientId = clientId;
        this.name = name;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "OrganizationalUnit{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", name='" + name + '\'' +
                '}';
    }
}
