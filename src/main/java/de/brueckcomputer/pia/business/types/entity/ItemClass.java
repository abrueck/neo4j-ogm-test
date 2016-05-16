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
public class ItemClass {

    @GraphId
    private Long id;

    private String name;
    private String label;
    private String description;

    @Relationship(type = "IMPLEMENTS", direction = Relationship.OUTGOING)
    private SortedSet<Implements> fieldGroups = new TreeSet<>();

    public ItemClass() {
    }

    public ItemClass(String name) {
        this.name = name;
    }

    public ItemClass(String name, String label) {
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

    public SortedSet<Implements> getFieldGroups() {
        return fieldGroups;
    }

    public void setFieldGroups(SortedSet<Implements> fieldGroups) {
        this.fieldGroups = fieldGroups;
    }

    public void addFieldGroup(Implements fieldGroup) {
        fieldGroups.add(fieldGroup);
    }

    @Override
    public String toString() {
        return "ItemClass{" +
                "id=" + id +
                ", name =" + name +
                ", label =" + label +
                "} ";
    }
}
