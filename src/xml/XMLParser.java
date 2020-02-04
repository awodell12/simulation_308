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


public class XMLParser {
    private final DocumentBuilder DOCUMENT_BUILDER;

    public XMLParser () {
        DOCUMENT_BUILDER = getDocumentBuilder();
    }

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

                // TODO: refactor this when I get a chance to
                int intType;
                if (stringType.equals("typeOne")) {
                    intType = 1;
                }
                else if (stringType.equals("typeTwo")) {
                    intType = 2;
                }
                else if (stringType.equals("typeZero")){
                    intType = 0;
                }
                else intType=0;
                coordinates.put(stringToPoint(coordinate), intType);
            }
        }

        return new Configuration(config, coordinates);
    }

    public Element getRootElement (File xmlFile) {
        try {
            DOCUMENT_BUILDER.reset();
            Document xmlDocument = DOCUMENT_BUILDER.parse(xmlFile);
            return xmlDocument.getDocumentElement();
        }
        catch (SAXException | IOException e) {
            throw new XMLException(e);
        }
    }

    public String getTextValue (Element e, String tagName) {
        NodeList nodeList = e.getElementsByTagName(tagName);
        if (nodeList != null && nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        else {
            // didn't find any text
            return "";
        }
    }

    public Point stringToPoint (String coordinate) {
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
