package de.brueckcomputer.pia.business.types.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by andreas on 31.03.16.
 */
@NodeEntity
public class Field {

    @GraphId
    private Long id;

    private String name;
    private String label;
    private String description;

    private FieldType type;

    public Field() {
    }

    public Field(String name) {
        this.name = name;
    }

    public Field(String name, String label, FieldType type) {
        this.name = name;
        this.label = label;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Field{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

}
