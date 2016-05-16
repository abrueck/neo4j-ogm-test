package de.brueckcomputer.pia.business.types.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by andreas on 30.03.16.
 */
@RelationshipEntity(type = "IMPLEMENTS")
public class Implements implements Comparable<Implements> {

    @GraphId
    private Long id;

    @StartNode
    private ItemClass itemClass;

    @EndNode
    private FieldGroup group;

    private Integer pos;

    public Implements() {
    }

    public Implements(ItemClass itemClass, FieldGroup group, Integer pos) {
        this.itemClass = itemClass;
        this.group = group;
        this.pos = pos;
    }


    public Long getId() {
        return id;
    }

    public ItemClass getItemClass() {
        return itemClass;
    }

    public void setItemClass(ItemClass itemClass) {
        this.itemClass = itemClass;
    }

    public FieldGroup getGroup() {
        return group;
    }

    public void setGroup(FieldGroup group) {
        this.group = group;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    @Override
    public int compareTo(Implements o) {
        return Integer.compare(this.pos, o.pos);
    }


}
