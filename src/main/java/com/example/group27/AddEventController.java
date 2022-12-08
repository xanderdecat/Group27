package com.example.group27;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEventController {

    @FXML
    private TextField cityInput;

    @FXML
    private TextField countryInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private DatePicker eventDateInput;

    @FXML
    private TextField eventNameInput;

    @FXML
    private TextField houseNumberInput;

    @FXML
    private TextField linkToPageInput;

    @FXML
    private Button makeNewEvent;

    @FXML
    private DatePicker startDateInput;

    @FXML
    private TextField streetNameInput;

    @FXML
    private Label testLbl1;

    @FXML
    private Label testLbl11;

    @FXML
    private TextField zipCodeInput;
    public void makeNewEvent(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddEventChooser.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 600);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }

    }

    public void goToHomePage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 700, 500);
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
