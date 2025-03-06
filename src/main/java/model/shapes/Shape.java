package model.shapes;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Абстрактный класс, представляющий геометрическую фигуру.
 * Реализует интерфейс Cloneable для поддержки клонирования.
 * @author Илья Чекрыгин
 * @version 1.0
 */
public abstract class Shape implements Cloneable {
    protected Color color = Color.BLACK;
    protected double x, y;

    /**
     * Абстрактный метод для отрисовки фигуры на холсте.
     *
     * @param gc Контекст графики для отрисовки.
     */
    public abstract void draw(GraphicsContext gc);

    /**
     * Проверяет, попадает ли точка (x, y) в область фигуры.
     *
     * @param x Координата X точки.
     * @param y Координата Y точки.
     * @return true, если точка находится внутри фигуры, иначе false.
     */
    public abstract boolean contains(double x, double y);

    /**
     * Устанавливает цвет фигуры.
     *
     * @param color Цвет фигуры.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Устанавливает позицию фигуры.
     *
     * @param x Координата X.
     * @param y Координата Y.
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает координату X фигуры.
     *
     * @return Координата X.
     */
    public double getX() {
        return x;
    }

    /**
     * Создает и возвращает копию текущей фигуры.
     * Реализует метод clone() для поддержки клонирования.
     *
     * @return Копия текущей фигуры.
     * @throws RuntimeException если клонирование не удалось.
     */
    @Override
    public Shape clone() {
        try {
            Shape cloned = (Shape) super.clone();
            cloned.color = color;
            cloned.x = x;
            cloned.y = y;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Failed to clone shape", e);
        }
    }
}