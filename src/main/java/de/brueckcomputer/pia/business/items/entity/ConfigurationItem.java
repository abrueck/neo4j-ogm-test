package de.brueckcomputer.pia.business.items.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.brueckcomputer.pia.business.types.entity.ItemClass;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andreas Brück <andreas@brueck-computer.de> on 21.03.16.
 */
@NodeEntity
public class ConfigurationItem {

    @GraphId
    private Long id;

    private String name;

    @JsonIgnore
    @Relationship(type = "LOCATED_AT", direction = Relationship.OUTGOING)
    private Location location;

    @Relationship(type = "PART_OF", direction = Relationship.OUTGOING)
    private Container container;

    @Relationship(type = "PART_OF", direction = Relationship.OUTGOING)
    private OrganizationalUnit organizationalUnit;

    @Relationship(type = "LINKED", direction = Relationship.UNDIRECTED)
    private Set<Link> links;

    @Relationship(type = "HAS", direction = Relationship.OUTGOING)
    private Set<FieldValue> fields = new HashSet<>();

    @Relationship(type = "OF_TYPE", direction = Relationship.OUTGOING)
    private ItemClass itemClass;


    public ConfigurationItem() {
    }

    public ConfigurationItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public OrganizationalUnit getOrganizationalUnit() {
        return organizationalUnit;
    }

    public void setOrganizationalUnit(OrganizationalUnit organizationalUnit) {
        this.organizationalUnit = organizationalUnit;
    }

    public Long getId() {
        return id;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public Set<FieldValue> getFields() {
        return fields;
    }

    public void setFields(Set<FieldValue> fields) {
        this.fields = fields;
    }

    public void addField(FieldValue field) {
        this.fields.add(field);
    }

    public ItemClass getItemClass() {
        return itemClass;
    }

    public void setItemClass(ItemClass itemClass) {
        this.itemClass = itemClass;
    }

    @Override
    public String toString() {
        return "ConfigurationItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
