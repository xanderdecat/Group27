package com.example.group27;

import APPLICATION.Provider;
import APPLICATION.Transaction;
import DB.ProviderDAO;
import DB.TransactionDAO;
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

    @FXML
    private TextField requestedHours;

    public static Provider chosenProvider;
    public static Transaction eventTransaction;

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
            stage.setResizable(false);
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }


    public void seeSpecificList(ActionEvent actionEvent) {
        possibleArtists.getItems().clear();

        double minPrice = Double.parseDouble(selectedMinPrice.getValue());
        double maxPrice = Double.parseDouble(selectedMaxPrice.getValue());
        Provider.genres genre = Provider.genres.valueOf(selectedGenre.getValue());

        if (minPrice != 0 && maxPrice != 0 && genre != null) {
            for (Provider provider : ProviderDAO.getProviders()) {
                if(provider.getUserNumber() != HelloApplication.userMain.getUserNumber())
                if ((minPrice <= provider.getPriceHour()) && (provider.getPriceHour() <= maxPrice) && provider.getGenre() == genre) {
                    String s = provider.getArtistName() + " - " + provider.getGenre().toString() + " - " + provider.getCity();
                    possibleArtists.getItems().add(s);
                }
            }
        }

    }

    
    public void seeArtist(ActionEvent actionEvent) {
        String chosen = possibleArtists.getSelectionModel().getSelectedItem();
        String artist = chosen.substring(0, chosen.indexOf("-") - 1);
        for (Provider provider : ProviderDAO.getProviders()) {
            if (provider.getArtistName().equals(artist)) {
                priceHourToSet.setText("€" + String.valueOf(provider.getPriceHour()));
                minHoursToSet.setText(String.valueOf(provider.getMinHours()) + " hours");
                maxHoursToSet.setText(String.valueOf(provider.getMaxHours()) + " hours");
                conditionsToSet.setText(provider.getConditions());
                chosenProvider = provider;
            }
        }
    }

    public void chooseArtist(ActionEvent actionEvent) {
        String chosen = possibleArtists.getSelectionModel().getSelectedItem();
        String artist = chosen.substring(0, chosen.indexOf("-") - 1);
        double requestedHours1 = Double.parseDouble(requestedHours.getText());
        if (requestedHours1 >= chosenProvider.getMinHours() && requestedHours1 <= chosenProvider.getMaxHours()) {
            double totalamount = requestedHours1 * chosenProvider.getPriceHour();
            eventTransaction = new Transaction(HomeScreenPageController.upcomingEvent.getEventNumber(), HelloApplication.userMain.getUserNumber(), chosenProvider.getProviderNumber(), Transaction.status.Requested, HomeScreenPageController.upcomingEvent.getEventName() + " - " + chosenProvider.getArtistName(), totalamount);
            TransactionDAO.saveTransaction(eventTransaction);
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                Stage stage = new Stage();
                stage.setTitle("Muzer");
                stage.setScene(scene);
                stage.show();
                stage.setResizable(false);
                ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            }
            catch (IOException e) {
            }        }

    }
}
