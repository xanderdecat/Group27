module com.example.group27 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens GUI to javafx.fxml;
    exports GUI;
}