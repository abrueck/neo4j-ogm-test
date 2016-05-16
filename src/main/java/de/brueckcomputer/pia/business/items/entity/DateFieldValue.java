package de.brueckcomputer.pia.business.items.entity;

import de.brueckcomputer.pia.business.types.entity.Field;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.typeconversion.DateLong;

import java.util.Date;

/**
 * Created by andreas on 30.03.16.
 */
@NodeEntity(label = "Date")
public class DateFieldValue extends FieldValue<Date> {

    @DateLong
    private Date value;

    public DateFieldValue() {
    }

    public DateFieldValue(Field field, Date value) {
        super(field);
        this.value = value;
    }

    @Override
    public Date getValue() {
        return value;
    }

    @Override
    public void setValue(Date value) {
        this.value = value;
    }

}
