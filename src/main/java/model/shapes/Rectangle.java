package model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Класс, представляющий прямоугольник.
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class Rectangle extends Shape {
    private static final double WIDTH = 60;
    private static final double HEIGHT = 40;

    /**
     * Отрисовывает прямоугольник на холсте.
     * @param gc Контекст графики для отрисовки.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
    }

    /**
     * Проверяет, попадает ли точка (x, y) в область прямоугольника.
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка находится внутри прямоугольника, иначе false.
     */
    @Override
    public boolean contains(double x, double y) {
        double halfWidth = WIDTH / 2;
        double halfHeight = HEIGHT / 2;
        return x >= this.x - halfWidth && x <= this.x + halfWidth &&
                y >= this.y - halfHeight && y <= this.y + halfHeight;
    }

    /**
     * Возвращает строковое представление прямоугольника.
     * @return Строка "Прямоугольник".
     */
    @Override
    public String toString() {
        return "Прямоугольник";
    }
}