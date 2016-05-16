package de.brueckcomputer.pia.business.items.entity;

import de.brueckcomputer.pia.business.types.entity.Field;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by andreas on 30.03.16.
 */
@NodeEntity(label = "Integer")
public class IntegerFieldValue extends FieldValue<Integer> {

    private Integer value;

    public IntegerFieldValue() {
    }

    public IntegerFieldValue(Field field, Integer value) {
        super(field);
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public void setValue(Integer value) {
        this.value = value;
    }
}
