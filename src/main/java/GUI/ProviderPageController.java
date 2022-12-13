package GUI;

import APPLICATION.Event;
import APPLICATION.Provider;
import APPLICATION.Transaction;
import DB.EventDAO;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class ProviderPageController {

    public static Event requestedEvent;
    public static Event upcomingEvent;
    public static Event previousEvent;
    public static Transaction requestedTransaction;
    @FXML
    private Label artistNameToSet;
    @FXML
    private ListView<String> previousEvents;
    @FXML
    private ListView<String> requestedEvents;
    @FXML
    private ListView<String> upcomingEvents;
    @FXML
    private Label scoreToSet;
    @FXML
    private Label amountToSet;

    public void initialize() {
        artistNameToSet.setText(HelloApplication.providerMain.getArtistName());

        if (Provider.calculateAverageScoreForProvider(HelloApplication.providerMain) == 0)
            scoreToSet.setText("-");
        else
            scoreToSet.setText(String.valueOf(Provider.calculateAverageScoreForProvider(HelloApplication.providerMain)));

        double amountForNPO = 0;
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == HelloApplication.providerMain.getProviderNumber()) {
                amountForNPO = amountForNPO + transaction.getAmountToNPO();
                if (transaction.getStatus() == Transaction.status.Requested && EventDAO.getEvent(transaction.getEventNumber()).getConfirmationDate().isAfter(LocalDateTime.now())) {
                    String s = EventDAO.getEvent(transaction.getEventNumber()).getEventName() + " - " + EventDAO.getEvent(transaction.getEventNumber()).getCity();
                    requestedEvents.getItems().add(s);
                }
                if (transaction.getStatus() == Transaction.status.Accepted && EventDAO.getEvent(transaction.getEventNumber()).getEndDate().isAfter(LocalDateTime.now())) {
                    String s = EventDAO.getEvent(transaction.getEventNumber()).getEventName() + " - " + EventDAO.getEvent(transaction.getEventNumber()).getCity();
                    upcomingEvents.getItems().add(s);
                }
                if (transaction.getStatus() == Transaction.status.Accepted && EventDAO.getEvent(transaction.getEventNumber()).getEndDate().isBefore(LocalDateTime.now())) {
                    String s = EventDAO.getEvent(transaction.getEventNumber()).getEventName() + " - " + EventDAO.getEvent(transaction.getEventNumber()).getCity();
                    previousEvents.getItems().add(s);
                }
            }
        }

        amountToSet.setText("€" + String.valueOf(Math.round((amountForNPO) * 10) / 10.0));

    }

    public void replyOnRequest(ActionEvent actionEvent) {
        String selected = requestedEvents.getSelectionModel().getSelectedItem();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == HelloApplication.providerMain.getProviderNumber() && (EventDAO.getEvent(transaction.getEventNumber()).getEventName() + " - " + EventDAO.getEvent(transaction.getEventNumber()).getCity()).equals(selected)) {
                requestedTransaction = transaction;
                requestedEvent = EventDAO.getEvent(transaction.getEventNumber());
                HelloApplication.loadPage("EventPageProvider.fxml", actionEvent);
            }
        }
    }

    public void viewUpcomingEvent(ActionEvent actionEvent) {
        String selected = upcomingEvents.getSelectionModel().getSelectedItem();
        for (Event event : EventDAO.getEvents())
            if (selected.equals(event.getEventName() + " - " + event.getCity())) {
                upcomingEvent = event;
                HelloApplication.loadPage("EventInfoProvider.fxml", actionEvent);
            }
    }

    public void leaveAReview(ActionEvent actionEvent) {
        String selected = previousEvents.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                previousEvent = EventDAO.getEvent(ev.getEventNumber());
                HelloApplication.loadPage("ReviewPageProvider.fxml", actionEvent);
            }
    }

    public void goToUserPage(ActionEvent actionEvent) {
        HelloApplication.loadPage("UserPage.fxml", actionEvent);
    }

    public void seeArtistInformation(ActionEvent actionEvent) {
        HelloApplication.loadPage("PersonalPageProvider.fxml", actionEvent);
    }

    public void logOut(ActionEvent actionEvent) {
        HelloApplication.loadPage("WelcomePage.fxml", actionEvent);
    }

    public void seeReviews(ActionEvent actionEvent) {
        HelloApplication.loadPage("ReviewOverviewProvider.fxml", actionEvent);
    }

    public void seeTransactions(ActionEvent actionEvent) {
        HelloApplication.loadPage("TransactionPageProvider.fxml", actionEvent);
    }
}
