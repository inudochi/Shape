package model.momento;

import model.shapes.Shape;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Класс для сохранения и восстановления состояния фигуры.
 * Реализует паттерн "Хранитель" (Memento).
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class Momento {
    private static final Logger logger = Logger.getLogger(Momento.class.getName());
    private final Shape shapeSnapshot;

    /**
     * Создает снимок состояния фигуры.
     * @param shape Фигура, состояние которой сохраняется.
     */
    public Momento(Shape shape) {
        try {
            this.shapeSnapshot = (Shape) shape.clone();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error creating shape state snapshot", e);
            throw new RuntimeException("Failed to create state snapshot", e);
        }
    }

    /**
     * Возвращает сохраненное состояние фигуры.
     * @return Копия фигуры на момент создания снимка.
     */
    public Shape getState() {
        try {
            return (Shape) shapeSnapshot.clone();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error restoring shape state", e);
            throw new RuntimeException("Failed to restore state", e);
        }
    }
}
