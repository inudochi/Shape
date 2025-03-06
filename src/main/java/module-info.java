module com.shape {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;

    // Открываем пакет com.shape для javafx.fxml, так как FXML-файл находится в этом пакете
    opens com.shape to javafx.fxml;

    // Экспортируем пакеты, чтобы они были доступны другим модулям
    exports main;
    exports model.shapes;
    exports model.momento;

    // Открываем пакет main для javafx.fxml, так как контроллеры могут быть там
    opens main to javafx.fxml;

    // Открываем пакеты model.shapes и model.momento для рефлексии (если она используется)
    opens model.shapes to javafx.fxml;
    opens model.momento to javafx.fxml;
}