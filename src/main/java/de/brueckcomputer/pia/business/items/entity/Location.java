package de.brueckcomputer.pia.business.items.entity;

/**
 * Created by andreas on 21.03.16.
 */
public class Location extends ConfigurationItem {


    public Location() {
    }

    public Location(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }

}
