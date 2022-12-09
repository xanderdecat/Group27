package com.example.group27;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PreviousEventsController {
    public void logOut2(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WelcomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }

    public void goToProviderScreen2(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProviderPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }

    public void goToLeaveAReview(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ReviewPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }
}
