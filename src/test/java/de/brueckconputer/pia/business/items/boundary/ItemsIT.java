package de.brueckconputer.pia.business.items.boundary;

import de.brueckcomputer.pia.business.items.entity.*;
import de.brueckcomputer.pia.business.types.entity.*;
import de.brueckconputer.pia.business.BoltGraphDatabase;
import de.brueckconputer.pia.business.GraphDatabase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.neo4j.ogm.cypher.ComparisonOperator;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.session.Session;

import java.util.*;
import java.util.stream.Collectors;

import static de.brueckconputer.pia.business.GraphDatabase.DEPTH_ENTITY;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Andreas Brück <andreas@brueck-computer.de> on 21.03.16.
 */
public class ItemsIT {

    private static GraphDatabase gd = new BoltGraphDatabase();
    private static Map<String, ConfigurationItem> linkItems;

    @BeforeClass
    public static void init() {
        gd.createSession().purgeDatabase(); // delete database
        createData();
    }


    private static ConfigurationItem createLinkItem(String name) {
        ConfigurationItem item = new ConfigurationItem(name);
        gd.createSession().save(item);
        linkItems.put(name, item);
        return item;
    }

    private static Link createLink(ConfigurationItem start, ConfigurationItem end, String type) {
        return new Link(start, end, type);
    }

    private static void createData() {
        System.out.println("Create data");
        Session session = gd.createSession();
        linkItems = new HashMap<>();

        // Group "Commmon"
        FieldGroup common = new FieldGroup("Common", "Allgemein");
        common.setSystem(true);
        common.setCommon(true);
        Field stringField = new Field("StringField", "StringField", FieldType.STRING);
        Field doubleField = new Field("DoubleField", "DoubleField",FieldType.DOUBLE);
        Field integerField = new Field("IntegerField", "IntegerField",FieldType.INTEGER);
        Field dateField = new Field("DateField", "DateField",FieldType.DATE);
        common.addField(new Declares(common, stringField, 1));
        common.addField(new Declares(common, doubleField, 2));
        common.addField(new Declares(common, integerField, 3));
        common.addField(new Declares(common, dateField, 4));

        // ItemClass "Workstation"
        ItemClass workstationClass = new ItemClass("Workstation");
        workstationClass.addFieldGroup(new Implements(workstationClass, common, 1));
        session.save(workstationClass);

        // Items for linking
        createLinkItem("Computech GmbH");
        createLinkItem("Rechnung 2015-0001");
        createLinkItem("Rechnung 2015-0002");
        createLinkItem("Andreas Brück");
        createLinkItem("Christoph Schröder");
        createLinkItem("Martin Roth");
        createLinkItem("Patrick Brede");
        createLinkItem("config.txt");

        session.save(createLink(linkItems.get("Computech GmbH"), linkItems.get("Rechnung 2015-0001"), "address"), DEPTH_ENTITY);
        session.save(createLink(linkItems.get("Computech GmbH"), linkItems.get("Rechnung 2015-0002"), "address"), DEPTH_ENTITY);


        // OrganizationalUnits
        OrganizationalUnit stammhaus = new OrganizationalUnit(2, "Stammhaus");
        session.save(stammhaus, DEPTH_ENTITY);
        OrganizationalUnit ou = new OrganizationalUnit(0, "Büro Wicker");
        session.save(ou, DEPTH_ENTITY);

        // Büro Wicker
        Location building = new Location("Büro Wicker");
        building.setOrganizationalUnit(ou);
        session.save(building, DEPTH_ENTITY);
        Location floor = new Location("Erdgeschoss");
        floor.setLocation(building);
        session.save(floor);

        Location room;
        Location workplace;
        ConfigurationItem pc;

        // Workplace Brück
        room = new Location("Büro Brück");
        room.setLocation(floor);
        session.save(room, DEPTH_ENTITY);
        workplace = new Location("Arbeitsplatz Brück");
        workplace.setLocation(room);
        session.save(workplace, DEPTH_ENTITY);
        pc = new ConfigurationItem("WCKHV190");
        pc.setItemClass(workstationClass);
        pc.setLocation(workplace);
        pc.addField(new StringFieldValue(stringField, "HT9876"));
        pc.addField(new IntegerFieldValue(integerField, 9876));
        pc.addField(new DateFieldValue(dateField, new Date()));
        pc.addField(new DoubleFieldValue(doubleField, 9876.54));
        session.save(pc);
        session.save(createLink(linkItems.get("Rechnung 2015-0001"), pc, "doc"), DEPTH_ENTITY);
        session.save(createLink(linkItems.get("config.txt"), pc, "cfg"), DEPTH_ENTITY);
        pc = new ConfigurationItem("WCKHV191");
        pc.setLocation(workplace);
        pc.setItemClass(workstationClass);
        pc.addField(new StringFieldValue(stringField, "HT1234"));
        pc.addField(new IntegerFieldValue(integerField, 1234));
        pc.addField(new DateFieldValue(dateField, new Date()));
        pc.addField(new DoubleFieldValue(doubleField, 1234.56));
        session.save(pc);

        session.save(createLink(linkItems.get("Andreas Brück"), workplace, "owns"), DEPTH_ENTITY);
        session.save(createLink(linkItems.get("Rechnung 2015-0001"), pc, "doc"), DEPTH_ENTITY);

        // Workplace Schröder
        room = new Location("Büro Schröder");
        room.setLocation(floor);
        session.save(room, DEPTH_ENTITY);
        workplace = new Location("Arbeitsplatz Schröder");
        workplace.setLocation(room);
        session.save(workplace, DEPTH_ENTITY);
        pc = new ConfigurationItem("WCKHV007");
        pc.setItemClass(workstationClass);
        pc.setLocation(workplace);
        session.save(pc, DEPTH_ENTITY);
        session.save(createLink(linkItems.get("Rechnung 2015-0001"), pc, "doc"), DEPTH_ENTITY);

        pc = new ConfigurationItem("WCKST0109");
        pc.setItemClass(workstationClass);
        pc.setLocation(workplace);
        pc.setOrganizationalUnit(stammhaus);
        session.save(pc, DEPTH_ENTITY);

        session.save(createLink(linkItems.get("Christoph Schröder"), workplace, "owns"));


        // Workplace Roth
        room = new Location("Büro Roth");
        room.setLocation(floor);
        session.save(room, DEPTH_ENTITY);
        workplace = new Location("Arbeitsplatz Roth");
        workplace.setLocation(room);
        session.save(workplace, DEPTH_ENTITY);
        pc = new ConfigurationItem("WCKHV109");
        pc.setItemClass(workstationClass);
        pc.setLocation(workplace);
        session.save(pc, DEPTH_ENTITY);
        pc = new ConfigurationItem("WCKHV054");
        pc.setItemClass(workstationClass);
        pc.setLocation(workplace);
        session.save(pc, DEPTH_ENTITY);

        session.save(createLink(linkItems.get("Martin Roth"), workplace, "owns"), DEPTH_ENTITY);
        session.save(createLink(linkItems.get("Rechnung 2015-0002"), pc, "doc"), DEPTH_ENTITY);

        // Workplace Brede
        room = new Location("Büro Brede");
        room.setLocation(floor);
        session.save(room, DEPTH_ENTITY);
        workplace = new Location("Arbeitsplatz Brede");
        workplace.setLocation(room);
        session.save(workplace);
        pc = new ConfigurationItem("WCKHV050");
        pc.setItemClass(workstationClass);
        pc.setLocation(workplace);
        session.save(pc, DEPTH_ENTITY);

        session.save(createLink(linkItems.get("Patrick Brede"), workplace, "owns"), DEPTH_ENTITY);

        System.out.println("Finished creating data");
    }

    private void printItem(ConfigurationItem item, int defaultIdent) {
        String baseIdentStr = Collections.nCopies(defaultIdent, "  ").stream().collect(Collectors.joining(""));
        System.out.println(baseIdentStr+ "=> " +  item);

        if (item.getLinks() != null) {
            item.getLinks().forEach(link -> {
                System.out.println(baseIdentStr + "  <> " +  link);
            });
        }

        Location l = item.getLocation();
        int ident = defaultIdent;
        while (l != null && ident < 20) {
            ident++;
            String identStr = Collections.nCopies(ident, "  ").stream().collect(Collectors.joining(""));
            System.out.println(identStr + "=> " + l);
            l = l.getLocation();
        }
    }

    private void printItem(ConfigurationItem item) {
        printItem(item, 0);
    }

    private Session loadDepth(long itemId, int depth) {
        Session session = gd.createSession();
        System.out.println("Load WCKHV190 with load and id = " +  itemId + " and depth = " +  depth + " and new session");
        printItem(session.load(ConfigurationItem.class, itemId, depth), 1);
        System.out.println("");
        return session;
    }

    private void loadDepth(long itemId, int depth, Session session) {
        System.out.println("Load WCKHV190 with load and id = " +  itemId + " and depth = " +  depth);
        printItem(session.load(ConfigurationItem.class, itemId, depth), 1);
        System.out.println("");
    }



    @Test
    public void loadDepth() {
        Session session = gd.createSession();

        // NOTE If you use -1 as depth for session.load(...) it ends in a continous loop

        System.out.println("Load WCKHV190 with loadAll and filter und neuer Session");
        Filters filters = new Filters().add("name", "WCKHV190");
        ConfigurationItem item = session.loadAll(ConfigurationItem.class, filters)
                .stream().findFirst().get();
        printItem(item, 1);
        System.out.println("");
        long itemId = item.getId();

        session = loadDepth(itemId, 0);
        loadDepth(itemId, 1, session);
        loadDepth(itemId, 5, session);

        session = loadDepth(itemId, 5);
        loadDepth(itemId, 0, session);
        loadDepth(itemId, 1, session);

        loadDepth(itemId, 0);
        loadDepth(itemId, 1);
        loadDepth(itemId, 5);

        loadDepth(itemId, 5);
        loadDepth(itemId, 0);
        loadDepth(itemId, 1);
    }


    @Test
    public void loadAllDepth() {

//        // Load all with depth = 0
//        System.out.println("Load all ConfiguraitonItems with depth = -1");
//        gd.createSession().loadAll(ConfigurationItem.class, -1).forEach(this::printItem);
//        System.out.println("");

        // Load all with depth = 0
        System.out.println("Load all ConfiguraitonItems with depth = 1");
        gd.createSession().loadAll(ConfigurationItem.class, 1).forEach(this::printItem);
        System.out.println("");

        // Load all with depth = 0
        System.out.println("Load all ConfiguraitonItems with depth = 0");
        gd.createSession().loadAll(ConfigurationItem.class, 0).forEach(this::printItem);
        System.out.println("");

    }

    @Test
    public void nearestOrganizationalUnits() {

        // nearest OU with location
        String query = "match (n:ConfigurationItem)-[:LOCATED_AT*]->(:Location)-[:PART_OF]->(ou:OrganizationalUnit)\n" +
                "where n.name = {ci_name}\n" +
                "return n,ou";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ci_name", "WCKHV190");
        OrganizationalUnit matchingOu = gd.createSession().queryForObject(OrganizationalUnit.class, query, parameters);
        assertNotNull(matchingOu);
        assertThat(matchingOu.getName(), is("Büro Wicker"));
        System.out.println("Matching OU: " + matchingOu);

    }


    @Test
    public void usingFilters() {

        System.out.println("Filter for property-value");
        Filters filters = new Filters().add("name", "WCKHV190");
        gd.createSession().loadAll(ConfigurationItem.class, filters, 0).forEach(this::printItem);
        System.out.println("");

        System.out.println("Like filter");
        Filter likeFilter = new Filter("name","WCKHV*");
        likeFilter.setComparisonOperator(ComparisonOperator.LIKE);
        filters = new Filters().add(likeFilter);
        gd.createSession().loadAll(ConfigurationItem.class, filters, 0).forEach(this::printItem);
        System.out.println("");
    }


    @Test
    public void loadNonExisting() {

        System.out.println("Load a non existing item");
        Filters filters = new Filters().add("name", "--------");
        Collection<ConfigurationItem> items = gd.createSession().loadAll(ConfigurationItem.class, filters);
        assertNotNull(items);
        assertThat(items.size(), is(0));

    }

    @Test
    public void loadLinks() {

        System.out.println("Load links");
        gd.createSession().loadAll(Link.class).forEach(System.out::println);

    }

    @Test
    public void addLink() {

        System.out.println("Add link with adding it to an ConfigurationItem");
        Session session = gd.createSession();

        ConfigurationItem rg = createLinkItem("Rechnung 2014-0001");
        ConfigurationItem pc = session.loadAll(ConfigurationItem.class, new Filter("name","WCKST0109")).stream()
                .findFirst()
                .get();

        if (pc.getLinks() == null) {
            pc.setLinks(new HashSet<>());
        }

        pc.getLinks().add(new Link(rg, pc, "doc"));
        session.save(pc);

    }


}
