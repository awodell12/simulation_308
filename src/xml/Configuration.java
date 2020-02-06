package xml;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class Configuration {
    public static final List<String> SIZE_FIELDS = List.of(
            "width",
            "height",
            "percentage"
    );
    public static final String COORDINATE_FIELD = "coordinate";

    private String myWidth;
    private String myHeight;
    private String myPercentage;
    private Map<Point, Integer> myCellCoordinates;

    public Configuration (String width, String height, String percentage, Map<Point, Integer> coordinates) {
        myWidth = width;
        myHeight = height;
        myPercentage = percentage;
        myCellCoordinates = coordinates;
    }

    public Configuration (Map<String, String> dataValues, Map<Point, Integer> coordinates) {
        this(dataValues.get(SIZE_FIELDS.get(0)),
                dataValues.get(SIZE_FIELDS.get(1)),
                dataValues.get(SIZE_FIELDS.get(2)),
                coordinates);
    }

    public int getWidth() {
        return Integer.parseInt(myWidth);
    }

    public int getHeight() {
        return Integer.parseInt(myHeight);
    }

    public double getPercentage() { return Double.parseDouble(myPercentage); }

    public Map<Point, Integer> getCellCoordinates() {
        return myCellCoordinates;
    }
}
