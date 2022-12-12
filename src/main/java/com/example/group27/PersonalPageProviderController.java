package com.example.group27;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class PersonalPageProviderController {

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
    private ChoiceBox<String> genreChooser;

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
    private TextField streetNameInput;

    public void initialize() {
        VATNumberInput.setText(HelloApplication.providerMain.getVATNumber());
        accountNumberInput.setText(HelloApplication.providerMain.getAccountNumber());
        streetNameInput.setText(HelloApplication.providerMain.getAccountNumber());
        houseNumberInput.setText(String.valueOf(HelloApplication.providerMain.getHouseNumber()));
        ZIPCodeInput.setText(String.valueOf(HelloApplication.providerMain.getZIP()));
        cityInput.setText(HelloApplication.providerMain.getCity());
        countryInput.setText(HelloApplication.providerMain.getCountry());
        artistNameInput.setText(HelloApplication.providerMain.getArtistName());
        activityDateInput.setValue(HelloApplication.providerMain.getActivityDate().toLocalDate());
        priceHourInput.setText(String.valueOf(HelloApplication.providerMain.getPriceHour()));
        minHoursInput.setText(String.valueOf(HelloApplication.providerMain.getMinHours()));
        maxHoursInput.setText(String.valueOf(HelloApplication.providerMain.getMaxHours()));
        conditionsInput.setText(HelloApplication.providerMain.getConditions());
        descriptionInput.setText(HelloApplication.providerMain.getDescription());
        linkToSetInput.setText(String.valueOf(HelloApplication.providerMain.getTeaserSet()));
        linkToPageInput.setText(String.valueOf(HelloApplication.providerMain.getLinkToPage()));
        genreChooser.setValue(String.valueOf(HelloApplication.providerMain.getGenre()));
    }
}
