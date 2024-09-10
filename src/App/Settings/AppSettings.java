package App.Settings;

import java.util.LinkedHashMap;
import java.util.Map;

public class AppSettings {
    static final String PATH = "src/App/app.properties";
    static final Map<String,String> DEFAULT = new LinkedHashMap<>() {{
        put("version", "0.1");
        put("width", "800");
        put("height", "600");
        put("title", "App");
        put("icon", "src/resources/GUI/icons/app_icon.png");
        put("customWindow", "true");
        put("titleBarHeight", "25");
    }};

}
