package de.brueckcomputer.pia.business.types.entity;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by andreas on 30.03.16.
 */
@NodeEntity
public class FieldGroup {

    @GraphId
    private Long id;

    private String name;
    private String label;
    private String description;

    @Relationship(type = "DECLARES", direction = Relationship.OUTGOING)
    private SortedSet<Declares> fields = new TreeSet<>();

    private boolean system;
    private boolean common;

    public FieldGroup() {

    }

    public FieldGroup(String name) {
        this.name = name;
    }

    public FieldGroup(String name, String label) {
        this.name = name;
        this.label = label;
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

    public SortedSet<Declares> getFields() {
        return fields;
    }

    public void setFields(SortedSet<Declares> fields) {
        this.fields = fields;
    }

    public void addField(Declares field) {
        fields.add(field);
    }

    public boolean isSystem() {
        return system;
    }

    public void setSystem(boolean system) {
        this.system = system;
    }

    public boolean isCommon() {
        return common;
    }

    public void setCommon(boolean common) {
        this.common = common;
    }

    @Override
    public String toString() {
        return "FieldGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", label='" + label + '\'' +
                '}';
    }
}
