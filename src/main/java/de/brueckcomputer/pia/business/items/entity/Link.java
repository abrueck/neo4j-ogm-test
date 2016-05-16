package de.brueckcomputer.pia.business.items.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by andreas on 24.03.16.
 */
@RelationshipEntity(type = "LINKED")
public class Link {

    private Long id;

    @StartNode
    private ConfigurationItem start;

    @EndNode
    private ConfigurationItem end;

    private String type;

    public Link() {
    }

    public Link(ConfigurationItem start, ConfigurationItem end, String type) {
        this.start = start;
        this.end = end;
        this.type = type;
    }

    public Long getId() {
        return id;
    }


    public ConfigurationItem getStart() {
        return start;
    }

    public void setStart(ConfigurationItem start) {
        this.start = start;
    }

    public ConfigurationItem getEnd() {
        return end;
    }

    public void setEnd(ConfigurationItem end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", start=" + start +
                ", end=" + end +
                ", type='" + type + '\'' +
                '}';
    }
}
