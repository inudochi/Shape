package main;

import model.shapes.Shape;
import model.shapes.Triangle;
import model.shapes.Circle;
import model.shapes.Rectangle;
import model.momento.Momento;
import model.momento.MemoSelect;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Контроллер для управления графическим редактором.
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class Controller implements Initializable {
    private static final Logger logger = Logger.getLogger(Controller.class.getName());

    @FXML
    private ListView<Shape> listView;
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private Button createButton;
    @FXML
    private Button moveButton;

    private ObservableList<Shape> templates;
    private ObservableList<Shape> createdShapes;
    private GraphicsContext gc;
    private MemoSelect memoSelect = new MemoSelect();
    private Shape selectedShape;
    private boolean isMoving = false;
    private boolean isDragging = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Setting up logging
        Log.setupLogger();
        logger.log(Level.INFO, "Controller initialization");

        Circle circle = new Circle();
        Rectangle rectangle = new Rectangle();
        Triangle triangle = new Triangle();

        templates = FXCollections.observableArrayList(circle, rectangle, triangle);
        createdShapes = FXCollections.observableArrayList();

        listView.setItems(templates);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        gc = canvas.getGraphicsContext2D();

        createButton.setOnAction(event -> {
            isMoving = false;
            logger.log(Level.INFO, "Shape creation mode activated");
        });

        moveButton.setOnAction(event -> {
            isMoving = true;
            logger.log(Level.INFO, "Shape moving mode activated");
        });

        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(this::handleMouseReleased);
    }

    @FXML
    public void handleCanvasClick(MouseEvent event) {
        if (!isMoving) {
            createShape(event.getX(), event.getY());
        } else {
            selectedShape = findShapeAtPosition(event.getX(), event.getY());
            if (selectedShape != null) {
                isDragging = true;
                logger.log(Level.INFO, "Shape selected for moving: " + selectedShape);
            }
        }
    }

    private void createShape(double x, double y) {
        int index = listView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            try {
                Shape template = templates.get(index);
                Shape shape = (Shape) template.clone();
                shape.setColor(colorPicker.getValue());
                shape.setPosition(x, y);
                createdShapes.add(shape);
                memoSelect.saveState(new Momento(shape));
                redrawCanvas();
                logger.log(Level.INFO, "New shape created: " + shape);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error creating shape", e);
            }
        } else {
            logger.log(Level.WARNING, "No shape selected for creation");
        }
    }

    private void handleMouseDragged(MouseEvent event) {
        if (isMoving && isDragging && selectedShape != null) {
            try {
                selectedShape.setPosition(event.getX(), event.getY());
                redrawCanvas();
                logger.log(Level.FINE, "Shape moved: " + selectedShape);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error moving shape", e);
            }
        }
    }

    private void handleMouseReleased(MouseEvent event) {
        if (isMoving && isDragging && selectedShape != null) {
            try {
                memoSelect.saveState(new Momento(selectedShape));
                isDragging = false;
                logger.log(Level.INFO, "Shape moved and state saved: " + selectedShape);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error saving shape state", e);
            }
        }
    }

    private Shape findShapeAtPosition(double x, double y) {
        for (Shape shape : createdShapes) {
            if (shape.contains(x, y)) {
                return shape;
            }
        }
        return null;
    }

    private void redrawCanvas() {
        try {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            for (Shape shape : createdShapes) {
                shape.draw(gc);
            }
            logger.log(Level.FINE, "Canvas redrawn");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error redrawing canvas", e);
        }
    }
}