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

    public void initialize() {
        eventNameToSet.setText(UserPageController.upcomingEvent.getEventName());
        startDateToSet.setText(UserPageController.upcomingEvent.getStartDate().toString());
        endDateToSet.setText(UserPageController.upcomingEvent.getEndDate().toString());
        cityToSet.setText(UserPageController.upcomingEvent.getCity());
        ZIPToSet.setText(String.valueOf(UserPageController.upcomingEvent.getZIP()));
        countryToSet.setText(UserPageController.upcomingEvent.getCountry());
        streetNameToSet.setText(UserPageController.upcomingEvent.getStreetName() + " " + UserPageController.upcomingEvent.getHouseNumber());

        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getEventNumber() == UserPageController.upcomingEvent.getEventNumber()) {
                String s = "(" + transaction.getStatus().toString() + ") " + ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName() + " - €" + transaction.getTotalAmount();
                bookedArtists.getItems().add(s);
            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        HelloApplication.loadPage("UserPage.fxml", actionEvent);
    }
}
