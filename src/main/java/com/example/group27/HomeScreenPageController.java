package com.example.group27;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreenPageController {
    public void logOut(ActionEvent actionEvent) {
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

    public void goToCreateNewEvent(ActionEvent actionEvent) {
    }

    public void goToSeePreviousEvents(ActionEvent actionEvent) {
    }

    // gaat naar nieuwe provider pagina toevoegingenen
    public void goToAddProviderPage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddProvider.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 900);
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
