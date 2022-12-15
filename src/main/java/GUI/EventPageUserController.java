package GUI;

import APPLICATION.Provider;
import APPLICATION.Transaction;
import DB.ProviderDAO;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class EventPageUserController {

    public static Provider chosenProvider;
    public static Transaction eventTransaction;
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
    @FXML
    private Label enterValidData;
    @FXML
    private Label enterValidDataHour;
    @FXML
    private Label noArtistsFound;
    @FXML
    private Label noRecommendation;

    public void initialize() {
        selectedMinPrice.getItems().addAll("1", "50", "100", "200", "500", "1000");
        selectedMaxPrice.getItems().addAll("1", "50", "100", "200", "500", "1000", "10000");
        selectedGenre.getItems().addAll("Techno", "Rock", "Pop", "Dance", "Blues", "Jazz", "Soul", "Party", "Hiphop", "Acoustic", "Disco", "Funk", "Classic", "Background", "Nineties", "Eighties", "Seventies", "Sixties", "Latin", "Lounge", "Other");
        eventNameToSet.setText(UserPageController.upcomingEvent.getEventName());
        startDateToSet.setText(UserPageController.upcomingEvent.getStartDate().toString());
        endDateToSet.setText(UserPageController.upcomingEvent.getEndDate().toString());

        setAllInvisible();
        showAll();
    }

    public void setAllInvisible () {
        priceHourToSet.setVisible(false);
        minHoursToSet.setVisible(false);
        maxHoursToSet.setVisible(false);
        conditionsToSet.setVisible(false);
        enterValidData.setVisible(false);
        enterValidDataHour.setVisible(false);
        noArtistsFound.setVisible(false);
        noRecommendation.setVisible(false);
        possibleArtists.getItems().clear();
        requestedHours.setText(null);
        chosenProvider = null;
    }

    public void showAll() {
        setAllInvisible();
        if (Main.providerMain != null) {
            for (Provider provider : ProviderDAO.getProviders()) {
                if (provider.getProviderNumber() != Main.providerMain.getProviderNumber() && !Provider.provideTheSameEvent(provider, UserPageController.upcomingEvent)) {
                    String s = provider.getArtistName() + " - " + provider.getGenre().toString() + " - " + provider.getCity();
                    possibleArtists.getItems().add(s);
                }
            }
        }
        if (Main.providerMain == null) {
            for (Provider provider : ProviderDAO.getProviders()) {
                if (!Provider.provideTheSameEvent(provider, UserPageController.upcomingEvent)) {
                    String s = provider.getArtistName() + " - " + provider.getGenre().toString() + " - " + provider.getCity();
                    possibleArtists.getItems().add(s);
                }
            }
        }
    }

    public void showSelection(ActionEvent actionEvent) {
        if (selectedMinPrice.getValue() != null && selectedMaxPrice.getValue() != null && selectedGenre.getValue() != null) {
            double minPrice = Double.parseDouble(selectedMinPrice.getValue());
            double maxPrice = Double.parseDouble(selectedMaxPrice.getValue());
            Provider.genres genre = Provider.genres.valueOf(selectedGenre.getValue());
            if (maxPrice >= minPrice) {
                setAllInvisible();
                if (Main.providerMain  != null) {
                    for (Provider provider : ProviderDAO.getProviders()) {
                        if (provider.getProviderNumber() != Main.providerMain.getProviderNumber() && !Provider.provideTheSameEvent(provider, UserPageController.upcomingEvent)) {
                            if ((minPrice <= provider.getPriceHour()) && (provider.getPriceHour() <= maxPrice) && provider.getGenre() == genre) {
                                String s = provider.getArtistName() + " - " + provider.getGenre().toString() + " - " + provider.getCity();
                                possibleArtists.getItems().add(s);
                            }
                        }
                    }
                }
                if (Main.providerMain  == null) {
                    for (Provider provider : ProviderDAO.getProviders()) {
                        if (!Provider.provideTheSameEvent(provider, UserPageController.upcomingEvent)) {
                            if ((minPrice <= provider.getPriceHour()) && (provider.getPriceHour() <= maxPrice) && provider.getGenre() == genre) {
                                String s = provider.getArtistName() + " - " + provider.getGenre().toString() + " - " + provider.getCity();
                                possibleArtists.getItems().add(s);
                            }
                        }
                    }
                }
            }
            else
                enterValidData.setVisible(true);
        }
        else
            enterValidData.setVisible(true);
        if (possibleArtists.getItems().size() == 0)
            noArtistsFound.setVisible(true);
    }

    public void showAllArtists(ActionEvent actionEvent) {
        selectedMinPrice.setValue(null);
        selectedMaxPrice.setValue(null);
        selectedGenre.setValue(null);
        setAllInvisible();
        showAll();
    }

    public void showRecommendedArtists(ActionEvent actionEvent) {
        selectedMinPrice.setValue(null);
        selectedMaxPrice.setValue(null);
        selectedGenre.setValue(null);
        setAllInvisible();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getUserNumber() == Main.userMain.getUserNumber() && !Provider.provideTheSameEvent(ProviderDAO.getProvider(transaction.getProviderNumber()), UserPageController.upcomingEvent)) {
                String s = ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName() + " - " + ProviderDAO.getProvider(transaction.getProviderNumber()).getGenre().toString() + " - " + ProviderDAO.getProvider(transaction.getProviderNumber()).getCity();
                if (!possibleArtists.getItems().contains(s))
                    possibleArtists.getItems().add(s);
            }
        }
        if (possibleArtists.getItems().size() == 0)
            noRecommendation.setVisible(true);
    }
    
    public void seeArtist(ActionEvent actionEvent) {
        enterValidDataHour.setVisible(false);
        String chosen = possibleArtists.getSelectionModel().getSelectedItem();
        String artist = chosen.substring(0, chosen.indexOf("-") - 1);
        for (Provider provider : ProviderDAO.getProviders()) {
            if (provider.getArtistName().equals(artist)) {
                priceHourToSet.setText("€" + provider.getPriceHour());
                minHoursToSet.setText(provider.getMinHours() + " hours");
                maxHoursToSet.setText(provider.getMaxHours() + " hours");
                if (provider.getConditions().equals(""))
                    conditionsToSet.setText("/");
                else
                    conditionsToSet.setText(provider.getConditions());
                chosenProvider = provider;
                priceHourToSet.setVisible(true);
                minHoursToSet.setVisible(true);
                maxHoursToSet.setVisible(true);
                conditionsToSet.setVisible(true);
            }
        }
    }

    public void chooseArtist(ActionEvent actionEvent) {
        if (!requestedHours.getText().equals("") && chosenProvider != null) {
            double requestedHours1 = Double.parseDouble(requestedHours.getText());
            if (requestedHours1 >= chosenProvider.getMinHours() && requestedHours1 <= chosenProvider.getMaxHours()) {
                double totalamount = requestedHours1 * chosenProvider.getPriceHour();
                eventTransaction = new Transaction(UserPageController.upcomingEvent.getEventNumber(), Main.userMain.getUserNumber(), chosenProvider.getProviderNumber(), Transaction.status.Requested, UserPageController.upcomingEvent.getEventName() + " - " + chosenProvider.getArtistName(), totalamount);
                TransactionDAO.saveTransaction(eventTransaction);
                Main.loadPage("UserPage.fxml", actionEvent);
            }
            else
                enterValidDataHour.setVisible(true);
        }
        else
            enterValidDataHour.setVisible(true);
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }
}