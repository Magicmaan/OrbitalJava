package App.Settings;
import App.Backup;

import java.io.*;
import java.util.Map;
import java.util.Properties;
import java.lang.reflect.Field;

public class Settings {
    private static Settings instance = null;
    private static Properties AppProperties;
    private static Properties UserProperties;

    private static final String USER_SETTINGS_PATH = "src/settings.properties";
    private static final String APP_SETTINGS_PATH = "src/App/app.properties";

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public String getUserSetting(String key) {
        return UserProperties.getProperty(key);
    }
    public void setUserSetting(String key, String value) {
        if (UserProperties.containsKey(key)) {
            UserProperties.setProperty(key, value);
        }
    }

    public String getAppSetting(String key) {
        return AppProperties.getProperty(key);
    }



    public void setAppSetting(String key, String value) {
        if (AppProperties.containsKey(key)) {
            AppProperties.setProperty(key, value);
        }
    }
    void createSetting(String key, String value) {
        if (!UserProperties.containsKey(key)) {
            UserProperties.setProperty(key, value);
        }
    }
    void resetUserToDefault() {
        createDefaultUserSettings();
    }
    void resetAppToDefault() {
        createDefaultAppSettings();
    }
    private Settings() {
        loadSettings();
    }

    private void loadSettings() {
        UserProperties = new Properties();
        AppProperties = new Properties();
        File UserSettingsFile = new File(USER_SETTINGS_PATH);
        File AppSettingsFile = new File(APP_SETTINGS_PATH);

        if (!UserSettingsFile.exists()) {
            createDefaultUserSettings();
        }
        if (!AppSettingsFile.exists()) {
            createDefaultAppSettings();
        }

        //load user settings
        try (InputStream settingsData = new FileInputStream(USER_SETTINGS_PATH)) {
            UserProperties.load(settingsData);
        } catch (IOException io) {
            io.printStackTrace();
        }

        //load app settings
        try (InputStream settingsData = new FileInputStream(APP_SETTINGS_PATH)) {
            AppProperties.load(settingsData);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
    private void saveSettings() {
        try (OutputStream output = new FileOutputStream(USER_SETTINGS_PATH)) {
            UserProperties.store(output, null);
            System.out.println("User Settings saved");
        } catch (IOException io) {
            io.printStackTrace();
        }

        try (OutputStream output = new FileOutputStream(APP_SETTINGS_PATH)) {
            AppProperties.store(output, null);
            System.out.println("App Settings saved");
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    //takes in variable attributes from a class and assigns to properties
    //class must contain only static final String var = "value";
    private void propertiesFromClass(Properties properties, Class className) {
        try {
            Field settings = className.getDeclaredField("DEFAULT");
            Map<String, String> defaultMap = (Map<String, String>) settings.get(null);

            for (Map.Entry<String, String> entry : defaultMap.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue());
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(properties.toString());


        /*Field[] fields = className.getDeclaredFields();
        for (Field field : fields) {
            try {
                properties.setProperty(field.getName(), (String) field.get(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }*/
    }
    private void createDefaultAppSettings() {
        File settingsFile = new File(APP_SETTINGS_PATH);
        //if file exists, backup and delete
        if (settingsFile.exists()) {
            System.out.println("App Settings file already exists");
            Backup.file(APP_SETTINGS_PATH);

            if (!settingsFile.delete()) {
                System.out.println("Failed to delete App Settings file");
                return;
            }
        }
        try {
            settingsFile.createNewFile();
        } catch (IOException io) {
            io.printStackTrace();
        }

        //properties from app settings class
        AppProperties.clear();
        propertiesFromClass(AppProperties, AppSettings.class);

        try (OutputStream output = new FileOutputStream(APP_SETTINGS_PATH)) {
            AppProperties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
    private void createDefaultUserSettings() {
        File settingsFile = new File(USER_SETTINGS_PATH);

        if (settingsFile.exists()) {
            System.out.println("User Settings file already exists");
            Backup.file(USER_SETTINGS_PATH);

            if (!settingsFile.delete()) {
                System.out.println("Failed to delete User Settings file");
                return;
            }
        }
        try {
            settingsFile.createNewFile();
        } catch (IOException io) {
            io.printStackTrace();
        }

        UserProperties.clear();
        propertiesFromClass(UserProperties, DefaultUserSettings.class);


        try (OutputStream output = new FileOutputStream(USER_SETTINGS_PATH)) {
            UserProperties.store(output, null);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
