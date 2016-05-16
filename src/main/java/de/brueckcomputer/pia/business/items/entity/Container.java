package de.brueckcomputer.pia.business.items.entity;

/**
 * Created by andreas on 21.03.16.
 */
public class Container extends ConfigurationItem {

    public Container() {
    }

    public Container(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Container{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                '}';
    }

}
