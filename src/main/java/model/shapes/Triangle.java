package model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Класс, представляющий треугольник.
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class Triangle extends Shape {
    private final double side;

    /**
     * Конструктор для создания треугольника с заданной стороной.
     *
     * @param side Длина стороны треугольника.
     */
    public Triangle(double side) {
        this.side = side;
    }

    /**
     * Отрисовывает треугольник на холсте.
     *
     * @param gc Контекст графики для отрисовки.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double[] xPoints = { x, x - side / 2, x + side / 2 };
        double[] yPoints = { y - side * Math.sqrt(3) / 2, y + side * Math.sqrt(3) / 6, y + side * Math.sqrt(3) / 6 };
        gc.fillPolygon(xPoints, yPoints, 3);
    }

    /**
     * Проверяет, находится ли точка внутри треугольника.
     *
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка внутри треугольника, иначе false.
     */
    @Override
    public boolean contains(double x, double y) {
        double x1 = this.x;
        double y1 = this.y - side * Math.sqrt(3) / 2;
        double x2 = this.x - side / 2;
        double y2 = this.y + side * Math.sqrt(3) / 6;
        double x3 = this.x + side / 2;
        double y3 = y2;

        boolean b1 = sign(x, y, x1, y1, x2, y2) < 0;
        boolean b2 = sign(x, y, x2, y2, x3, y3) < 0;
        boolean b3 = sign(x, y, x3, y3, x1, y1) < 0;
        return (b1 == b2) && (b2 == b3);
    }

    /**
     * Вычисляет знак для определения положения точки относительно линии.
     *
     * @param x  Координата X точки.
     * @param y  Координата Y точки.
     * @param x1 Координата X первой вершины.
     * @param y1 Координата Y первой вершины.
     * @param x2 Координата X второй вершины.
     * @param y2 Координата Y второй вершины.
     * @return Числовое значение, определяющее положение точки.
     */
    private double sign(double x, double y, double x1, double y1, double x2, double y2) {
        return (x - x2) * (y1 - y2) - (x1 - x2) * (y - y2);
    }

    /**
     * Возвращает строковое представление треугольника.
     *
     * @return Строка "Треугольник".
     */
    @Override
    public String toString() {
        return "Треугольник";
    }
}