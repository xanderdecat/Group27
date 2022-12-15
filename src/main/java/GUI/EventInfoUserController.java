package GUI;

import APPLICATION.Transaction;
import DB.ProviderDAO;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class EventInfoUserController {

    @FXML
    private Label ZIPToSet;
    @FXML
    private Label cityToSet;
    @FXML
    private Label countryToSet;
    @FXML
    private Label endDateToSet;
    @FXML
    private Label eventNameToSet;
    @FXML
    private Label startDateToSet;
    @FXML
    private Label streetNameToSet;
    @FXML
    private ListView<String> bookedArtists;
    @FXML
    private Label noArtistsFound;

    public void initialize() {
        eventNameToSet.setText(UserPageController.upcomingEvent.getEventName());
        startDateToSet.setText(UserPageController.upcomingEvent.getStartDate().toString());
        endDateToSet.setText(UserPageController.upcomingEvent.getEndDate().toString());
        cityToSet.setText(UserPageController.upcomingEvent.getCity());
        ZIPToSet.setText(String.valueOf(UserPageController.upcomingEvent.getZIP()));
        countryToSet.setText(UserPageController.upcomingEvent.getCountry());
        streetNameToSet.setText(UserPageController.upcomingEvent.getStreetName() + " " + UserPageController.upcomingEvent.getHouseNumber());
        noArtistsFound.setVisible(false);

        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getEventNumber() == UserPageController.upcomingEvent.getEventNumber()) {
                String s = "(" + transaction.getStatus().toString() + ") " + ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName() + " - €" + transaction.getTotalAmount();
                bookedArtists.getItems().add(s);
            }
        }
        if (bookedArtists.getItems().isEmpty())
            noArtistsFound.setVisible(true);
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }
}
