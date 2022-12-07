module com.example.group27 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.group27 to javafx.fxml;
    exports com.example.group27;
}