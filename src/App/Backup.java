package App;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Backup {

    public static final String BACKUP_PATH = "src/backup/";
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yy_HH.mm");



    public static void file(String sourceFilePath) {
        String dateTime = LocalDateTime.now().format(dateTimeFormatter);

        try {
            Path sourcePath = Paths.get(sourceFilePath);
            String FileName = sourcePath.getFileName().toString();
            String prettyPath = "backup_" + dateTime + "_" + FileName;

            Path targetPath = Paths.get(BACKUP_PATH + prettyPath);
            Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Backed up File: " + sourceFilePath + " to " + targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void folder(String FolderPath) {
        System.out.println("Backup folder: " + FolderPath);
    }
}
