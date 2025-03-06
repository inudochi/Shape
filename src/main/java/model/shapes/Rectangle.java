package model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Класс, представляющий прямоугольник.
 * @author Илья Чекрыгни
 * @version 1.1
 */
public class Rectangle extends Shape {
    private final double width;
    private final double height;

    /**
     * Конструктор для создания прямоугольника с заданными размерами.
     *
     * @param width  Ширина прямоугольника.
     * @param height Высота прямоугольника.
     */
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Отрисовывает прямоугольник на холсте.
     *
     * @param gc Контекст графики для отрисовки.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x - width / 2, y - height / 2, width, height);
    }

    /**
     * Проверяет, находится ли точка внутри прямоугольника.
     *
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка внутри прямоугольника, иначе false.
     */
    @Override
    public boolean contains(double x, double y) {
        double halfWidth = width / 2;
        double halfHeight = height / 2;
        return x >= this.x - halfWidth && x <= this.x + halfWidth &&
                y >= this.y - halfHeight && y <= this.y + halfHeight;
    }

    /**
     * Возвращает строковое представление прямоугольника.
     *
     * @return Строка "Прямоугольник".
     */
    @Override
    public String toString() {
        return "Прямоугольник";
    }
}