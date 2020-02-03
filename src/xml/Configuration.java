package xml;

import java.awt.Point;
import java.util.List;
import java.util.Map;

public class Configuration {
    public static final List<String> SIZE_FIELDS = List.of(
            "width",
            "height"
    );
    public static final String COORDINATE_FIELD = "coordinate";

    private String myWidth;
    private String myHeight;
    private Map<Point, Integer> myCellCoordinates;

    public Configuration (String width, String height, Map<Point, Integer> coordinates) {
        myWidth = width;
        myHeight = height;
        myCellCoordinates = coordinates;
    }

    public Configuration (Map<String, String> dataValues, Map<Point, Integer> coordinates) {
        this(dataValues.get(SIZE_FIELDS.get(0)),
                dataValues.get(SIZE_FIELDS.get(1)),
                coordinates);
    }

    public int getWidth() {
        return Integer.parseInt(myWidth);
    }

    public int getHeight() {
        return Integer.parseInt(myHeight);
    }

    public Map<Point, Integer> getCellCoordinates() {
        return myCellCoordinates;
    }
}
