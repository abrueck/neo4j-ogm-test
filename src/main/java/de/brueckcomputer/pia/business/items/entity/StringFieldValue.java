package de.brueckcomputer.pia.business.items.entity;

import de.brueckcomputer.pia.business.types.entity.Field;
import org.neo4j.ogm.annotation.NodeEntity;

/**
 * Created by andreas on 30.03.16.
 */
@NodeEntity(label = "String")
public class StringFieldValue extends FieldValue<String> {

    private String value;

    public StringFieldValue() {
    }

    public StringFieldValue(Field field, String value) {
        super(field);
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }
}
