package logging;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AppLogger {

    private static final String LOG_FILE = "logs/integration.log";

    public static void info(String message) {
        log("INFO", message);
    }

    public static void warning(String message) {
        log("WARNING", message);
    }

    public static void error(String message) {
        log("ERROR", message);
    }

    private static void log(String level, String message) {
        String entry = LocalDateTime.now() + " [" + level + "] " + message;
        System.out.println(entry);
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            pw.println(entry);
        } catch (IOException e) {
            System.out.println("Kunde inte skriva till loggfil: " + e.getMessage());
        }
    }
}