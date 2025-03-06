package model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Класс, представляющий круг.
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class Circle extends Shape {
    private final double radius;

    /**
     * Конструктор для создания круга с заданным радиусом.
     *
     * @param radius Радиус круга.
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Отрисовывает круг на холсте.
     *
     * @param gc Контекст графики для отрисовки.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }

    /**
     * Проверяет, находится ли точка внутри круга.
     *
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка внутри круга, иначе false.
     */
    @Override
    public boolean contains(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy) <= radius;
    }

    /**
     * Возвращает строковое представление круга.
     *
     * @return Строка "Круг".
     */
    @Override
    public String toString() {
        return "Круг";
    }
}