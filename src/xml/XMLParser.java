package xml;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Robert Chen
 * Purpose: The purpose of this class is to extract the necessary information from the XML files based on the fields
 * specified in Configuration.java, and return a Configuration object that contains all of that information.
 * Assumptions: For the coordinates, we assume that they are labled by type: typeOne and typeTwo.
 * Dependencies: Configuration and XMLException
 * Example: Create a new XMLParser object, call getConfiguration, and use the getter methods in the configuration object
 * to extract the information from the file.
 */
public class XMLParser {
    private final DocumentBuilder DOCUMENT_BUILDER;

    public XMLParser () {
        DOCUMENT_BUILDER = getDocumentBuilder();
    }

    /**
     * Purpose: Returns a Configuration object that contains all of the important information held in the XML file.
     * Assumptions: For the coordinates, we assume that they are labeled by type: typeOne and typeTwo.
     * @param dataFile - the XML file
     * @return Configuration - a Configuration object that contains the information needed.
     */
    public Configuration getConfiguration (File dataFile) {
        Element root = getRootElement(dataFile);
        Map<String, String> config = new HashMap<>();
        Map<Point, Integer> coordinates = new HashMap<>();
        for (String field : Configuration.SIZE_FIELDS) {
            config.put(field, getTextValue(root, field));
        }

        NodeList nList = root.getElementsByTagName(Configuration.COORDINATE_FIELD);

        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                String coordinate = element.getTextContent();

                String stringType = element.getParentNode().getNodeName();
                int intType = 0;
                if (stringType.equals("typeOne")) {
                    intType = 1;
                }
                else if (stringType.equals("typeTwo")) {
                    intType = 2;
                }
                coordinates.put(stringToPoint(coordinate), intType);
            }
        }
        return new Configuration(config, coordinates);
    }

    private Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e);
        }
    }

    private String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // didn't find any text
            return "";
        }
    }

    private Point stringToPoint (String coordinate) {
        String[] coords = coordinate.split(",");
        return new Point(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
    }

    private DocumentBuilder getDocumentBuilder () {
        try {
            return DocumentBuilderFactory.newInstance().newDocumentBuilder();
        }
        catch (ParserConfigurationException e) {
            throw new XMLException(e);
        }
    }
}
