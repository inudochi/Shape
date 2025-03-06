package main;

import java.io.IOException;
import java.util.logging.*;

/**
 * Класс для настройки и демонстрации логирования.
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class Log {
    private static final Logger logger = Logger.getLogger(Log.class.getName());

    /**
     * Настраивает логирование для приложения.
     */
    public static void setupLogger() {
        try {
            // Создаем FileHandler для записи логов в файл
            FileHandler fileHandler = new FileHandler("%t/GraphEditor.log", true);
            fileHandler.setFormatter(new SimpleFormatter()); // Используем простой текстовый формат
            logger.addHandler(fileHandler);

            // Устанавливаем уровень логирования
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up logging", e);
        }
    }
}
