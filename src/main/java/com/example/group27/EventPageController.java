package com.example.group27;

import APPLICATION.Provider;
import DB.ProviderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class EventPageController {

    @FXML
    private Label cityToSet;

    @FXML
    private Label endDateToSet;

    @FXML
    private Label eventNameToSet;

    @FXML
    private ListView<String> possibleArtists;

    @FXML
    private ChoiceBox<String> selectedGenre;

    @FXML
    private ChoiceBox<String> selectedMaxPrice;

    @FXML
    private ChoiceBox<String> selectedMinPrice;

    @FXML
    private Label startDateToSet;

    @FXML
    private Label minHoursToSet;

    @FXML
    private Label priceHourToSet;

    @FXML
    private Label conditionsToSet;

    @FXML
    private Label maxHoursToSet;

    public void initialize() {
        selectedMinPrice.getItems().addAll("1", "50", "100", "200", "500", "1000");
        selectedMaxPrice.getItems().addAll("1", "50", "100", "200", "500", "1000", "10000");
        selectedGenre.getItems().addAll("Techno", "Rock", "Pop", "Dance", "Blues", "Jazz", "Soul", "Party", "Hiphop", "Acoustic", "Disco", "Funk", "Classic", "Background", "Nineties", "Eighties", "Seventies", "Sixties", "Latin", "Lounge", "Other");
        eventNameToSet.setText(HomeScreenPageController.upcomingEvent.getEventName());
        startDateToSet.setText(HomeScreenPageController.upcomingEvent.getStartDate().toString());
        endDateToSet.setText(HomeScreenPageController.upcomingEvent.getEndDate().toString());
        cityToSet.setText(HomeScreenPageController.upcomingEvent.getCity());


        for (Provider provider : ProviderDAO.getProviders()) {
            String s = provider.getArtistName() + " - " + provider.getGenre().toString() + " - " + provider.getCity();
            possibleArtists.getItems().add(s);
        }
    }

    public void goBack3(ActionEvent actionEvent) {
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


    public void seeSpecificList(ActionEvent actionEvent) {
        double minPrice = Double.parseDouble(selectedMinPrice.getValue());
        double maxPrice = Double.parseDouble(selectedMaxPrice.getValue());
        Provider.genres genre = Provider.genres.valueOf(selectedGenre.getValue());

    }

    
    public void seeArtist(ActionEvent actionEvent) {
        String chosen = possibleArtists.getSelectionModel().getSelectedItem();
        String artist = chosen.substring(0, chosen.indexOf("-") - 1);
        for (Provider provider : ProviderDAO.getProviders()) {
            if (provider.getArtistName().equals(artist)) {
                priceHourToSet.setText(String.valueOf(provider.getPriceHour()));
                minHoursToSet.setText(String.valueOf(provider.getMinHours()));
                maxHoursToSet.setText(String.valueOf(provider.getMaxHours()));
                conditionsToSet.setText(provider.getConditions());
            }
        }
    }

    public void chooseArtist(ActionEvent actionEvent) {
    }
}
