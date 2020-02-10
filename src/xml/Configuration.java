package xml;

import java.awt.Point;
import java.util.List;
import java.util.Map;

/**
 * @author Robert Chen
 * Purpose: Specify which fields are important and hold the information that can be accessed with getter methods
 * Assumptions: Assumes that the names of the XML tags will correspond to the strings in SIZE_FIELDS and COORDINATE_FIELD
 * Dependencies: None
 * Example: Get a Configuration object from the XML parser and use the getter methods to access the fields.
 */
public class Configuration {
    public static final List<String> SIZE_FIELDS = List.of(
            "type",
            "width",
            "height",
            "percentage"
    );
    public static final String COORDINATE_FIELD = "coordinate";

    private String myType;
    private String myWidth;
    private String myHeight;
    private String myPercentage;
    private Map<Point, Integer> myCellCoordinates;

    /**
     * Initializes instance variables to parameter values.
     * @param type - simulation name
     * @param width
     * @param height
     * @param percentage - varies based on simulation, like burn chance in Fire, or probability in Wator
     * @param coordinates
     */
    public Configuration (String type, String width, String height, String percentage, Map<Point, Integer> coordinates) {
        myType = type;
        myWidth = width;
        myHeight = height;
        myPercentage = percentage;
        myCellCoordinates = coordinates;
    }

    /**
     * Calls the constructor above with data extracted from the two maps that are passed in.
     * @param dataValues - holds type, width, height, and percentage
     * @param coordinates - holds the coordinates
     */
    public Configuration (Map<String, String> dataValues, Map<Point, Integer> coordinates) {
        this(dataValues.get(SIZE_FIELDS.get(0)),
                dataValues.get(SIZE_FIELDS.get(1)),
                dataValues.get(SIZE_FIELDS.get(2)),
                dataValues.get(SIZE_FIELDS.get(3)),
                coordinates);
    }

    /**
     * Get the integer value of the width of the Grid.
     * @return width
     */
    public int getWidth() {
        return Integer.parseInt(myWidth);
    }

    /**
     * Get the integer value of the height of the Grid.
     * @return height
     */
    public int getHeight() {
        return Integer.parseInt(myHeight);
    }

    /**
     * Get the double value of the percentage
     * @return percentage
     */
    public double getPercentage() { return Double.parseDouble(myPercentage); }

    /**
     * Get the simulation type/name
     * @return type
     */
    public String getType() {
        return myType;
    }

    /**
     * Get a map that holds the coordinates and what type each coordinate is
     * @return myCellCoordinates
     */
    public Map<Point, Integer> getCellCoordinates() {
        return myCellCoordinates;
    }
}
