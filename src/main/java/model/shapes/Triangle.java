package model.shapes;

import javafx.scene.canvas.GraphicsContext;

/**
 * Класс, представляющий треугольник.
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class Triangle extends Shape {
    private static final double SIDE = 60;

    /**
     * Отрисовывает треугольник на холсте.
     * @param gc Контекст графики для отрисовки.
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        double[] xPoints = { x, x - SIDE / 2, x + SIDE / 2 };
        double[] yPoints = { y - SIDE * Math.sqrt(3) / 2, y + SIDE * Math.sqrt(3) / 6, y + SIDE * Math.sqrt(3) / 6 };
        gc.fillPolygon(xPoints, yPoints, 3);
    }

    /**
     * Проверяет, попадает ли точка (x, y) в область треугольника.
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка находится внутри треугольника, иначе false.
     */
    @Override
    public boolean contains(double x, double y) {
        double x1 = this.x;
        double y1 = this.y - SIDE * Math.sqrt(3) / 2;
        double x2 = this.x - SIDE / 2;
        double y2 = this.y + SIDE * Math.sqrt(3) / 6;
        double x3 = this.x + SIDE / 2;
        double y3 = y2;

        boolean b1 = sign(x, y, x1, y1, x2, y2) < 0;
        boolean b2 = sign(x, y, x2, y2, x3, y3) < 0;
        boolean b3 = sign(x, y, x3, y3, x1, y1) < 0;
        return (b1 == b2) && (b2 == b3);
    }

    private double sign(double x, double y, double x1, double y1, double x2, double y2) {
        return (x - x2) * (y1 - y2) - (x1 - x2) * (y - y2);
    }

    /**
     * Возвращает строковое представление треугольника.
     * @return Строка "Треугольник".
     */
    @Override
    public String toString() {
        return "Треугольник";
    }
}
