package com.example.group27;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ReviewPageUserController {

    @FXML
    private ListView<String> artistToChoose;

    @FXML
    private Label cityToSet;

    @FXML
    private TextField descriptionInput;

    @FXML
    private Label endDateToSet;

    @FXML
    private Label eventNameToSet;

    @FXML
    private ChoiceBox<String> scoreOn10;

    @FXML
    private Label startDateToSet;

    @FXML
    private TextField subjectInput;

    public void initialize() {
        scoreOn10.getItems().addAll("1/10", "2/10", "3/10", "4/10", "5/10", "6/10", "7/10", "8/10", "9/10", "10/10");
        eventNameToSet.setText(HomeScreenPageController.previousEvent.getEventName());
        startDateToSet.setText(HomeScreenPageController.previousEvent.getStartDate().toString());
        endDateToSet.setText(HomeScreenPageController.previousEvent.getEndDate().toString());
        cityToSet.setText(HomeScreenPageController.previousEvent.getCity());
    }
    public void goBack(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
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

    public void submitReview(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
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
