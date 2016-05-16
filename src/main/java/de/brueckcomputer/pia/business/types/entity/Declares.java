package de.brueckcomputer.pia.business.types.entity;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

/**
 * Created by andreas on 30.03.16.
 */
@RelationshipEntity(type = "DECLARES")
public class Declares implements Comparable<Declares> {

    @GraphId
    private Long id;

    @StartNode
    private FieldGroup group;

    @EndNode
    private Field field;

    private Integer pos;

    public Declares() {
    }


    public Declares(FieldGroup group, Field field, Integer pos) {
        this.group = group;
        this.field = field;
        this.pos = pos;
    }

    public Long getId() {
        return id;
    }

    public FieldGroup getGroup() {
        return group;
    }

    public void setGroup(FieldGroup group) {
        this.group = group;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    @Override
    public int compareTo(Declares o) {
        return Integer.compare(this.pos, o.pos);
    }


}
