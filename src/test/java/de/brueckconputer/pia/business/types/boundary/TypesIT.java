package de.brueckconputer.pia.business.types.boundary;

import de.brueckcomputer.pia.business.types.entity.*;
import de.brueckconputer.pia.business.BoltGraphDatabase;
import de.brueckconputer.pia.business.GraphDatabase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.session.Session;

import static org.junit.Assert.assertNotNull;

/**
 * Created by andreas on 30.03.16.
 */
public class TypesIT {

    private static GraphDatabase gd = new BoltGraphDatabase();

    @BeforeClass
    public static void init() {
        gd.createSession().purgeDatabase(); // delete database
        createData();
    }

    private static void createData() {
        Session session = gd.createSession();

        // Group "Commmon"
        FieldGroup common = new FieldGroup("Common", "Allgemein");
        common.setSystem(true);
        Field htNumber = new Field("ht-number", "HT-Nummer", FieldType.STRING);
        Field customField = new Field("custom-field", "Benutzerdefiniert",FieldType.STRING);
        common.addField(new Declares(common, htNumber, 1));
        common.addField(new Declares(common, customField, 2));

        // Group "Purchase"
        FieldGroup purchase = new FieldGroup("purchase", "Einkauf");
        Field purchased  = new Field("purchased", "gekauft am", FieldType.DATE);
        Field distributor = new Field("distributor", "Gekauft bei", FieldType.STRING);
        Field price = new Field("price", "Einkaufspreis", FieldType.DOUBLE);
        purchase.addField(new Declares(purchase, purchased, 1));
        purchase.addField(new Declares(purchase, distributor, 2));
        purchase.addField(new Declares(purchase, price, 3));

        // ItemClass "Workstation"
        ItemClass workstation = new ItemClass("Workstation");
        workstation.addFieldGroup(new Implements(workstation, common, 1));
        workstation.addFieldGroup(new Implements(workstation, purchase, 2));
        session.save(workstation);

    }

    @Test
    public void crud() throws Exception {

        // Load all with depth = 0
        System.out.println("Load all ItemClasses with depth = -1");
        gd.createSession().loadAll(ItemClass.class, -1).forEach(this::printItemClass);
        System.out.println("");

        // Delete fieldgroup with delete
        Session session = gd.createSession();
        FieldGroup fieldGroup = session.loadAll(FieldGroup.class, new Filter("name","purchase")).stream()
                .findFirst()
                .orElse(null);
        assertNotNull(fieldGroup);
        printItemClass(fieldGroup);
        fieldGroup.getFields().stream()
                .map(Declares::getField)
                .forEach(session::delete);
        session.delete(fieldGroup);

    }

    private void printItemClass(ItemClass itemClass) {
        System.out.println("=> " + itemClass + " groups is type " + itemClass.getFieldGroups().getClass().toString());
        itemClass.getFieldGroups().forEach(impl -> {
            System.out.println("  => (" + impl.getPos() + ") " + impl.getGroup() + " fields is type " +
                    impl.getGroup().getFields().getClass().toString());
            impl.getGroup().getFields().forEach(decl -> {
                System.out.println("    => (" +  decl.getPos() + ") " + decl.getField());
            });
        });
    }


    private void printItemClass(FieldGroup fieldGroup) {
        System.out.println("=> " + fieldGroup + " groups is type " + fieldGroup.getFields().getClass().toString());
        fieldGroup.getFields().forEach(impl -> {
            System.out.println("  => (" + impl.getPos() + ") " + impl.getField());
        });
    }

}
