package xml;

import java.util.List;
import java.util.Map;

public class Configuration {
    public static final List<String> DATA_FIELDS = List.of(
            "width",
            "height"
    );

    private String myWidth;
    private String myHeight;

    public Configuration (String width, String height) {
        myWidth = width;
        myHeight = height;
    }

    public Configuration (Map<String, String> dataValues) {
        this(dataValues.get(DATA_FIELDS.get(0)),
                dataValues.get(DATA_FIELDS.get(1)));
    }

    public String getWidth() {
        return myWidth;
    }

    public String getHeight() {
        return myHeight;
    }
}
