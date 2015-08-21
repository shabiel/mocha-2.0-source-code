package gov.va.med.pharmacy.peps.external.common.preencapsulation.utility.drugcheck;

import gov.va.med.pharmacy.peps.common.exception.InterfaceException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Retrieves the Order Check Request Schema file and modifies the simpleType enumerations based upon values retrieved from the
 * FDB database.
 * 
 * NOTE: THIS FILE WAS BACKED OUT AND THE FUNCTIONALITY IS BEING REMOVED FROM THE PECS v3.0 UFT INCREMENT.
 * WE ARE LEAVING THE CLASS IN CLEARCASE SO THAT WE CAN PICK UP THIS FUNCTIONALITY IN A FUTURE INCREMENT.
 * 
 * @author zzzzzzduffg
 * 
 */
public final class SchemaSync implements ApplicationContextAware, InitializingBean {

    private static final Log LOGGER = LogFactory.getLog(SchemaSync.class);

    private static final int BUFFER_SIZE = 32000;
    
    private DataSource dataSource;
    private static ApplicationContext appCtx;
    private CachedSchema cachedSchema = null;
    private Properties mochaServerProperties;
    private static final String EVICTION_DAY_OF_WEEK = "evictionDayOfWeek";
    private static final String EVICTION_HOUR_OF_DAY = "evictionHourOfDay";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    /**
     * Uses Spring to obtain an instance of this class.
     * 
     * @return SchemaSync
     */
    public static SchemaSync getInstance() {
        return (SchemaSync) appCtx.getBean("orderCheckRequestSchemaSync", SchemaSync.class);
    }

    /**
     * Checks the cache to see if the schema has already been generated.  If the schema
     * is not in the cache, it will be generated and written to the cache.
     * 
     * @return InputStream
     */
    public InputStream getSchema() {
        InputStream stream = getCachedElement();
        try {
            stream.reset();
        } catch (IOException e) {
            // Just log the error and return the stream so that the calling app can receive an error response.
            LOGGER.error(e.getMessage(), e);
        }
        return stream;
    }

    /**
     * Checks to see if the schema has been built or if it should be evicted.  If either of
     * these are true, the schema is re-processed and cached.
     * 
     * The cached schema is then returned to the user.
     * 
     * @return InputStream
     */
    private InputStream getCachedElement() {
        if ( cachedSchema == null || shouldEvict() ) {
            InputStream stream = processSchema();
            cachedSchema = new CachedSchema();
            cachedSchema.setInputStream(stream);
        }
        
        return cachedSchema.getInputStream();
    }
    
    /**
     * Checks to see if the CachedSchema object should be evicted based upon calculateWeeklyEvictionDate
     * response.
     * 
     * @return boolean true = evict, false = use current
     */
    private boolean shouldEvict() {
        GregorianCalendar evictionDate = calculateWeeklyEvictionDate();
        GregorianCalendar currentTimestamp = new GregorianCalendar();
        if ( currentTimestamp.after(evictionDate)) {
            if ( LOGGER.isInfoEnabled()) {
                LOGGER.info("Evicting object created on " 
                        + dateFormat.format(cachedSchema.getCreatedTimestamp().getTime()) 
                        + ".  Eviction Date of " + dateFormat.format(evictionDate.getTime()));
            }
            return true;
        }
        return false;
    }
    
    /**
     * Retrieves the Order Check request schema file.
     * 
     * @return InputStream
     */
    private InputStream processSchema() {
        try {
//            InputStream is =
//                  Thread.currentThread().getContextClassLoader()
//                  .getResourceAsStream(DrugCheckXmlUtility.REQUEST_SCHEMA_FILE);
            InputStream is =
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("schema/drugCheckSchemaInput.xsd");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder db = factory.newDocumentBuilder();
            Document doc = db.parse(is);
            XPathFactory xFactory = XPathFactory.newInstance();
            XPath xpath = xFactory.newXPath();
            xpath.setNamespaceContext(MyNamespaceContext.getInstance());

            ProcessSchema process = new ProcessSchema(doc, xpath);
            // dose types
            process.execute("//xsd:simpleType[@name='doseTypeType']/xsd:restriction",
                    "select description1 from fdb_dosetype where description1 is not null", "//xsd:element[@name='doseType']");
            // dose units
            process.execute("//xsd:simpleType[@name='doseUnitType']/xsd:restriction", "select units from fdb_doseunitstype",
                    "//xsd:element[@name='doseUnit']");
            // routes
            process.execute("//xsd:simpleType[@name='routeType']/xsd:restriction", "select description1 from fdb_doseroute",
                    "//xsd:element[@name='route']");

            Source source = new DOMSource(doc);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFFER_SIZE);
            Result result = new StreamResult(baos);
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.transform(source, result);

            return new ByteArrayInputStream(baos.toByteArray());
        } catch (Exception e) {
            throw new InterfaceException(e, InterfaceException.INTERFACE_ERROR, InterfaceException.PRE_ENCAPSULATION);
        }
    }

    /**
     * Adds values to the passed in node.
     * 
     * @param node
     *            a DOM node
     * @param values
     *            the list of values to add to the DOM node
     */
    private void addEnumValues(Node node, List<String> values) {
        for (String val : values) {
            Element el = node.getOwnerDocument().createElement("xsd:enumeration");
            el.setAttribute("value", val);
            node.appendChild(el);
        }

    }

    /**
     * Logs the passed in attributes if the debugging level is set.
     * 
     * @param attributes
     *            the attributes to log
     */
    @SuppressWarnings("unused")
    private void print(NamedNodeMap attributes) {
        if (LOGGER.isDebugEnabled()) {
            for (int i = 0; i < attributes.getLength(); i++) {
                LOGGER.debug(attributes.item(i).getNodeName() + " : " + attributes.item(i).getNodeValue());
            }
        }
    }

    /**
     * Return a usable JDBC Connection from this factory
     * 
     * @return Connection to the database
     * @throws SQLException
     *             trouble with the database
     */
    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Sets the DataSource to use.
     * 
     * @param dataSource
     *            property
     */
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Sets the mochaServerProperties value.
     *
     * @param mochaServerProperties the mochaServerProperties to set
     */
    public void setMochaServerProperties(Properties mochaServerProperties) {
        this.mochaServerProperties = mochaServerProperties;
    }

    /**
     * Called by Spring to set the ApplicationContext.
     * 
     * @param applicationContext
     *            the ApplicationContext
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        appCtx = applicationContext;
    }

    /**
     * A method called by Spring after the class is instantiated.  This method checks to make
     * sure the Spring injections have been performed.
     * 
     * @throws Exception if an injection has not happened
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        if ( dataSource == null ) {
            throw new Exception("DataSource has not been injected in to SchemaSync.");
        }
        if ( mochaServerProperties == null ) {
            throw new Exception("MochaServerProperties has not been injected in to SchemaSync.");
        }
        validateIntegerProperty(EVICTION_DAY_OF_WEEK);
        validateIntegerProperty(EVICTION_HOUR_OF_DAY);
    }

    /**
     * Validates a property to make sure it can be found and that it is a valid Integer value.
     * 
     * @param key the property key
     * @throws Exception if the property is not found or it is not a valid Integer
     */
    private void validateIntegerProperty(String key) throws Exception {
        String propertyValue = mochaServerProperties.getProperty(key);
        if ( propertyValue == null ) {
            String message = "Cannot find the property " + key + " in the mochaServerProperties file.";
            LOGGER.error(message);
            throw new Exception(message);
        }
        try {
            Integer.parseInt(propertyValue);
        } catch( NumberFormatException nfe ) {
            String message = "The value for the property " + key + " is not a valid Integer.";
            LOGGER.error(message);
            throw new Exception(message);
        }
    }
    
    /**
     * Retrieves the EVICTION_DAY_OF_WEEK value from the properties.
     * 
     * @return Integer
     */
    private Integer getEvictionDayOfWeek() {
        return getIntegerProperty(EVICTION_DAY_OF_WEEK);
    }
    
    /**
     * Retrieves the EVICTION_DAY_OF_WEEK value from the properties.
     * 
     * @return Integer
     */
    private Integer getEvictionHourOfDay() {
        return getIntegerProperty(EVICTION_HOUR_OF_DAY);
    }
    
    /**
     * Retrieves the Integer property from mochaServerProperties with the specified key.
     * 
     * @param key String value indicating the key of the property
     * @return Integer
     */
    private Integer getIntegerProperty(String key) {
        return Integer.parseInt(mochaServerProperties.getProperty(key));
    }
    
    /**
     * Calculates the weekly eviction date based upon the createdTimestamp and take in to account
     * beginning of year/end of year issues.
     * 
     * @return GregorianCalendar
     */
    private GregorianCalendar calculateWeeklyEvictionDate() {
        final Integer dayOfWeek = getEvictionDayOfWeek();
        final Integer hourOfDay = getEvictionHourOfDay();
        
        GregorianCalendar createdTimestamp = cachedSchema.getCreatedTimestamp();
        
        GregorianCalendar evictionDate = (GregorianCalendar) createdTimestamp.clone();
        
        int currentDayOfWeek = createdTimestamp.get(GregorianCalendar.DAY_OF_WEEK);
        
        // If the dayOfWeek has already passed, then increase the week of the year.
        if ( currentDayOfWeek > dayOfWeek ) {
            // Use the add method to take care of beginning of year/end of year problems.
            evictionDate.add(GregorianCalendar.WEEK_OF_YEAR, 1);
            evictionDate.set(GregorianCalendar.DAY_OF_WEEK, dayOfWeek);
        }
        
        // If the day of the week is not the current day of the week, add the number of days between the two.
        if ( currentDayOfWeek < dayOfWeek ) {
            evictionDate.add(GregorianCalendar.DAY_OF_WEEK, dayOfWeek - currentDayOfWeek);
        }
        
        // Set the time in the day
        evictionDate.set(GregorianCalendar.HOUR_OF_DAY, hourOfDay);
        evictionDate.set(GregorianCalendar.MINUTE, 0);
        evictionDate.set(GregorianCalendar.SECOND, 0);
        evictionDate.set(GregorianCalendar.MILLISECOND, 0);
        
        return evictionDate;
    }

    /**
     * A private class utilized during the translation of the schema.
     * 
     * @author zzzzzzduffg
     * 
     */
    private class ProcessSchema {
        private Document doc;
        private XPath xpath;

        /**
         * Constructor.
         * 
         * @param doc
         *            the XML document
         * @param xpath
         *            the XPath object
         */
        ProcessSchema(Document doc, XPath xpath) {
            this.doc = doc;
            this.xpath = xpath;
        }

        /**
         * Executes the XPath object.
         * 
         * @param enumXPath
         *            the XPath
         * @param enumSQL
         *            the SQL
         * @param fieldXPath
         *            the Field
         * @throws Exception
         *             if an error occurs
         */
        public void execute(String enumXPath, String enumSQL, String fieldXPath) throws Exception {
            XPathExpression routeType = xpath.compile(enumXPath);
            Node node = (Node) routeType.evaluate(doc, XPathConstants.NODE);
            replaceEnumValues(node, enumSQL);
            changeFieldType(node, fieldXPath);
        }

        /**
         * Changes the field type.
         * 
         * @param enumNode
         *            the node
         * @param fieldXPath
         *            the XPath to the field
         * @throws Exception
         *             if an error occurs
         */
        private void changeFieldType(Node enumNode, String fieldXPath) throws Exception {
            String typeName = enumNode.getParentNode().getAttributes().getNamedItem("name").getNodeValue();
            XPathExpression routeType = xpath.compile(fieldXPath);
            Node fieldNode = (Node) routeType.evaluate(doc, XPathConstants.NODE);
            fieldNode.getAttributes().getNamedItem("type").setNodeValue(typeName);
        }

        /**
         * Replaces enumeration values.
         * 
         * @param node
         *            the Node
         * @param enumSQL
         *            the SQL to execute
         * @throws Exception
         *             if an error occurs
         */
        private void replaceEnumValues(Node node, String enumSQL) throws Exception {
            removeChildren(node, xpath);
            List<String> values = getValues(enumSQL);
            addEnumValues(node, values);
        }

        /**
         * Executes the SQL and returns a list of values retrieved from the database.
         * 
         * @param enumSQL
         *            the SQL
         * @return List<String>
         * @throws Exception
         *             if an error occurs
         */
        private List<String> getValues(String enumSQL) throws Exception {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(enumSQL);
            List<String> res = new ArrayList<String>();
            while (rs.next()) {
                res.add(rs.getString(1));
            }
            conn.close();
            return res;
        }

        /**
         * Removes the children from a particular node.
         * 
         * @param node
         *            the Node
         * @param fieldXPath
         *            the XPath
         * @throws Exception
         *             if an error occurs
         */
        private void removeChildren(Node node, XPath fieldXPath) throws Exception {
            // for some reason trying to remove the child nodes gotten like this
            // doesn't work, but removing what XPath returns does
            // NodeList nodes = node.getChildNodes();
            // System.out.println(nodes.getLength());
            XPathExpression routeTypeEnumeration = fieldXPath.compile("*");
            NodeList nodes = (NodeList) routeTypeEnumeration.evaluate(node, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                Node n = nodes.item(i);
                n.getParentNode().removeChild(n);
            }
            node.normalize();
            node.removeChild(node.getChildNodes().item(0));
        }
    }

    /**
     * Internal class to provide Namespace information.
     * 
     * @author zzzzzzduffg
     *
     */
    public static final class MyNamespaceContext implements NamespaceContext {
        private HashMap<String, String> nameSpaceMappings = new HashMap<String, String>();
        private static MyNamespaceContext instance = new MyNamespaceContext();

        /**
         * Obtains an instance of MyNamespaceContext.
         * 
         * @return MyNamespaceContext
         */
        public static MyNamespaceContext getInstance() {
            return instance;
        }

        /**
         * Private constructor.
         */
        private MyNamespaceContext() {
            nameSpaceMappings.put(XMLConstants.XML_NS_PREFIX, XMLConstants.XML_NS_URI);
            nameSpaceMappings.put("xsd", XMLConstants.W3C_XML_SCHEMA_NS_URI);
        }

        /**
         * Returns a String with the namespace URI.
         * 
         * @param prefix the prefix to lookup in the namespace mappings
         * @return String
         */
        public String getNamespaceURI(String prefix) {
            String namespace = nameSpaceMappings.get(prefix);
            return namespace == null ? XMLConstants.NULL_NS_URI : namespace;
        }

        /**
         * This method isn't necessary for XPath processing.  
         * Throws an UnsupportedOperationsException.
         * 
         * @param uri String
         * @return String
         */
        public String getPrefix(String uri) {
            throw new UnsupportedOperationException();
        }

        /**
         * This method isn't necessary for XPath processing.  
         * Throws an UnsupportedOperationsException.
         * 
         * @param uri String
         * @return Iterator<String>
         */
        public Iterator<String> getPrefixes(String uri) {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * A private class to hold the InputStream as well as the timestamp when the stream was created.
     * 
     * @author zzzzzzduffg
     *
     */
    private final class CachedSchema {
        private GregorianCalendar createdTimestamp = new GregorianCalendar();
        private InputStream inputStream;
       
        /**
         * Returns the timestamp when this object was created.
         * 
         * @return GregorianCalendar
         */
        public GregorianCalendar getCreatedTimestamp() {
            return createdTimestamp;
        }
        
        /**
         * Retrieves the InputStream.
         * 
         * @return InputStream
         */
        public InputStream getInputStream() {
            return inputStream;
        }
        
        /**
         * Sets the InputStream.
         * 
         * @param stream the InputStram
         */
        public void setInputStream(InputStream stream) {
            inputStream = stream;
        }
    }
}
