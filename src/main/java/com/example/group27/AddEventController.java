package com.example.group27;

import APPLICATION.Event;
import APPLICATION.Provider;
import DB.EventDAO;
import DB.ProviderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddEventController {

    public static Event eventMain;

    @FXML
    private TextField cityInput;

    @FXML
    private TextField countryInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private DatePicker endDateInput;

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
        try {
            String eventName1 = eventNameInput.getText();
            String streetName1 = streetNameInput.getText();
            int houseNumber1 = Integer.parseInt(houseNumberInput.getText());
            int ZIPCode1 = Integer.parseInt(zipCodeInput.getText());
            String cityInput1 = cityInput.getText();
            String country1 = countryInput.getText();

            LocalDate startDateLD = startDateInput.getValue();
            String startDateString = startDatePicker.getValue();
            Integer startDateInteger = Integer.valueOf(startDateString.substring(0, 1));
            LocalDateTime startDate1 = LocalDateTime.of(startDateLD.getYear(), startDateLD.getMonth(), startDateLD.getDayOfMonth(), startDateInteger, 00, 00);

            LocalDate endDateLD = endDateInput.getValue();
            String endDateString = endDatePicker.getValue();
            Integer endDateInteger = Integer.valueOf(endDateString.substring(0, 1));
            LocalDateTime endDate1 = LocalDateTime.of(endDateLD.getYear(), endDateLD.getMonth(), endDateLD.getDayOfMonth(), endDateInteger, 00, 00);

            String description1 = descriptionInput.getText();
            URL linkToPage1 = new URL(linkToPageInput.getText());

            if (startDate1.isBefore(endDate1) && eventName1 != null && streetName1 != null && houseNumberInput.getText() != null && zipCodeInput.getText() != null && country1 != null && description1 != null && linkToPage1 != null) {
                eventMain = new Event(HelloApplication.userMain.getUserNumber(), eventName1, streetName1, houseNumber1, ZIPCode1, cityInput1, country1, startDate1, endDate1, description1, linkToPage1);
                EventDAO.saveEvent(eventMain);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("AddEventChooser.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 630, 600);
                    Stage stage = new Stage();
                    stage.setTitle("Muzer");
                    stage.setScene(scene);
                    stage.show();
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                }
            }
        } catch (MalformedURLException e) {
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
