package de.brueckcomputer.pia.business.items.entity;

import de.brueckcomputer.pia.business.types.entity.Field;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by andreas on 30.03.16.
 */
@NodeEntity(label = "Double")
public class DoubleFieldValue extends FieldValue<Double> {

    private Double value;

    public DoubleFieldValue() {
    }

    public DoubleFieldValue(Field field, Double value) {
        super(field);
        this.value = value;
    }

    @Override
    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(Double value) {
        this.value = value;
    }

}
