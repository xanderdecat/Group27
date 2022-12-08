package com.example.group27;

import APPLICATION.Provider;
import DB.ProviderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private ChoiceBox<String> endDatePicker;

    @FXML
    private ChoiceBox<String> startDatePicker;

    public void initialize() {
        startDatePicker.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
        endDatePicker.getItems().addAll("00:00", "01:00", "02:00", "03:00", "04:00", "05:00", "06:00", "07:00", "08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00", "21:00", "22:00", "23:00");
    }

    @FXML
    private TextField zipCodeInput;
    public void makeNewEvent(ActionEvent actionEvent) {
        String eventName1 = eventNameInput.getText();
        String streetName1 = streetNameInput.getText();
        String houseNumber = houseNumberInput.getText();
        String ZIPCode1 = zipCodeInput.getText();
        String country1 = countryInput.getText();
        String
        LocalDate endDate1 = eventDateInput.getValue();
        java.sql.Date endDate11 = java.sql.Date.valueOf(endDate1);
        String description1 = descriptionInput.getText();
        String linkToPage1 = linkToPageInput.getText();

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
