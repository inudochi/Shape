package model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Класс, представляющий круг.
 * @author Илья Чекрыгин
 * @version 1.0
 */
public class Circle extends Shape {
    private static final double RADIUS = 30;

    /**
     * Отрисовывает круг на холсте.
     * @param gc Контекст графики для отрисовки.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }

    /**
     * Проверяет, попадает ли точка (x, y) в область круга.
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка находится внутри круга, иначе false.
     */
    @Override
    public boolean contains(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy) <= RADIUS;
    }

    /**
     * Возвращает строковое представление круга.
     * @return Строка "Круг".
     */
    @Override
    public String toString() {
        return "Круг";
    }
}
