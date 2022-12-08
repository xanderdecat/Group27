package com.example.group27;

import APPLICATION.Provider;
import DB.ProviderDAO;
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
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddProviderController {

    @FXML
    private TextField VATNumberInput;

    @FXML
    private TextField ZIPCodeInput;

    @FXML
    private TextField accountNumberInput;

    @FXML
    private DatePicker activityDateInput;

    @FXML
    private TextField artistNameInput;

    @FXML
    private TextField cityInput;

    @FXML
    private TextField conditionsInput;

    @FXML
    private TextField countryInput;

    @FXML
    private TextField descriptionInput;

    @FXML
    private TextField houseNumberInput;

    @FXML
    private TextField linkToPageInput;

    @FXML
    private TextField linkToSetInput;

    @FXML
    private TextField maxHoursInput;

    @FXML
    private TextField minHoursInput;

    @FXML
    private TextField priceHourInput;

    @FXML
    private Button registerMP;

    @FXML
    private TextField streetNameInput;

    @FXML
    private Label testLbl;

    @FXML
    private Label testLbl1;

    @FXML
    private Label testLbl11;

    @FXML
    private Label testLbl111;

    @FXML
    private Label testLbl1111;

    @FXML
    private Label testLbl11111;

    @FXML
    private Label testLbl1112;

    @FXML
    private Label testLbl1113;

    @FXML
    private Label testLbl11131;

    // gaat terug naar HOMESCREEN
    public void goToHomeScreen(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }


    public void goToProviderPage(ActionEvent actionEvent) {         //op een of andere manier nog voorwaarden opleggen aan de input
        if (VATNumberInput.getText() != null && accountNumberInput.getText() != null && ZIPCodeInput.getText() != null && activityDateInput.getValue() != null && artistNameInput.getText() != null && cityInput.getText() != null && countryInput.getText() != null && descriptionInput.getText() != null && houseNumberInput.getText() != null && linkToSetInput.getText() != null && maxHoursInput.getText() != null && minHoursInput.getText() != null && priceHourInput.getText() != null && streetNameInput.getText() != null) {
            try {
                String VATNumber = VATNumberInput.getText();
                String accountNumber = accountNumberInput.getText();
                int ZIPCode = Integer.parseInt(ZIPCodeInput.getText());
                LocalDate ld = activityDateInput.getValue();
                Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
                Date activityDate = Date.from(instant);
                String artistName = artistNameInput.getText();
                String city = cityInput.getText();
                String conditions = conditionsInput.getText();
                String country = countryInput.getText();
                String description = descriptionInput.getText();
                int houseNumber = Integer.parseInt(houseNumberInput.getText());
                URL linkToPage = new URL(linkToPageInput.getText());
                URL linkToSet = new URL(linkToSetInput.getText());
                double maxHours = Double.parseDouble(maxHoursInput.getText());
                double minHours = Double.parseDouble(minHoursInput.getText());
                double priceHour = Double.parseDouble(priceHourInput.getText());
                String streetName = streetNameInput.getText();
                Provider provider = new Provider(HelloApplication.userMain.getUserNumber(), HelloApplication.userMain.getFirstName(), HelloApplication.userMain.getLastName(), HelloApplication.userMain.getDateOfBirth(), HelloApplication.userMain.getAge(), HelloApplication.userMain.getEmail(), HelloApplication.userMain.getPhoneNumber(), HelloApplication.userMain.getWachtwoord(), VATNumber, accountNumber, streetName, houseNumber, ZIPCode, city, country, artistName,
                        Provider.genres.Acoustic, activityDate, priceHour, minHours, maxHours, conditions, description, linkToSet, linkToPage);
                ProviderDAO.saveProvider(provider);
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ProviderPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 630, 400);
                    Stage stage = new Stage();
                    stage.setTitle("Muzer");
                    stage.setScene(scene);
                    stage.show();
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                }
            } catch (MalformedURLException e) {
            }
        }


    }
}