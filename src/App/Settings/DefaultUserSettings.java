package App.Settings;

import java.util.LinkedHashMap;
import java.util.Map;

class DefaultUserSettings {
    static final String PATH = "src/App/app.properties";
    static final Map<String,String> DEFAULT = new LinkedHashMap<>() {{
        put("userName", "User");
        put("userEmail", "");
        put("userPassword", "password");
        put("userTheme", "light");
        put("userLanguage", "en");
        put("userFont", "Arial");
    }};
}
