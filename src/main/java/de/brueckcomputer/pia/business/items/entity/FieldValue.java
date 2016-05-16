package de.brueckcomputer.pia.business.items.entity;

import de.brueckcomputer.pia.business.types.entity.Field;
import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

/**
 * Created by andreas on 30.03.16.
 */
@NodeEntity
public abstract class FieldValue<T> {

    @GraphId
    private Long id;

    @Relationship(type = "OF_TYPE", direction = Relationship.OUTGOING)
    private Field field;

    public FieldValue() {
    }

    public FieldValue(Field field) {
        this.field = field;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public abstract T getValue();

    public abstract void setValue(T value);

    @Override
    public String toString() {
        return "FieldValue{" +
                "id=" + id +
                ", name='" + field.getName() + '\'' +
                ", label='" + field.getLabel() + '\'' +
                '}';
    }
}
