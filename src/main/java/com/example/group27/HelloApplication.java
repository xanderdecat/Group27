package com.example.group27;

import APPLICATION.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
public static User userMain;
    @Override
    public void start(Stage stage) throws IOException {
        userMain = new User();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("WelcomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 630, 400);
        stage.setTitle("Muzer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}