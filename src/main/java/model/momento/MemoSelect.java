package model.momento;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Управление историей состояний для реализации отмены/повтора.
 * @author Илья Чекрыгни
 * @version 1.0
 */
public class MemoSelect {
    private static final Logger logger = Logger.getLogger(MemoSelect.class.getName());
    private final Stack<Momento> history = new Stack<>();

    /**
     * Сохраняет текущее состояние фигуры в истории.
     * @param momento Снимок состояния фигуры.
     */
    public void saveState(Momento momento) {
        try {
            history.push(momento);
            logger.log(Level.FINE, "Shape state saved in history");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error saving shape state", e);
        }
    }

    /**
     * Восстанавливает предыдущее состояние из истории.
     * @return Последнее сохраненное состояние или null, если история пуста.
     */
    public Momento undo() {
        try {
            if (history.isEmpty()) {
                logger.log(Level.WARNING, "State history is empty");
                return null;
            }
            Momento momento = history.pop();
            logger.log(Level.FINE, "Shape state restored from history");
            return momento;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error restoring shape state", e);
            return null;
        }
    }
}
