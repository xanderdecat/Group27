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

    public static Event requestedEvent;
    public static Event upcomingEvent;
    public static Event previousEvent;
    public static Transaction requestedTransaction;


    public void initialize() {
        artistNameToSet.setText(HelloApplication.providerMain.getArtistName());

        if (Provider.calculateAverageScoreForProvider(HelloApplication.providerMain) == 0)
            scoreToSet.setText("-");
        else
            scoreToSet.setText(String.valueOf(Provider.calculateAverageScoreForProvider(HelloApplication.providerMain)));

        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == HelloApplication.providerMain.getProviderNumber()) {
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
    }

    public void replyOnRequest(ActionEvent actionEvent) {
        String selected = requestedEvents.getSelectionModel().getSelectedItem();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == HelloApplication.providerMain.getProviderNumber() && (EventDAO.getEvent(transaction.getEventNumber()).getEventName() + " - " + EventDAO.getEvent(transaction.getEventNumber()).getCity()).equals(selected)) {
                requestedTransaction = transaction;
                requestedEvent = EventDAO.getEvent(transaction.getEventNumber());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("RequestedEvents.fxml"));
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
        }
    }

    public void viewUpcomingEvent(ActionEvent actionEvent) {
        String selected = upcomingEvents.getSelectionModel().getSelectedItem();
        for (Event event : EventDAO.getEvents())
            if (selected.equals(event.getEventName() + " - " + event.getCity())) {
                upcomingEvent = event;
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("EventInfoProvider.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                    Stage stage = new Stage();
                    stage.setTitle("Muzer");
                    stage.setScene(scene);
                    stage.show();
                    stage.setResizable(false);
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                }

            }
    }

    public void leaveAReview(ActionEvent actionEvent) {
        String selected = previousEvents.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                previousEvent = EventDAO.getEvent(ev.getEventNumber());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ReviewPageProvider.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                    Stage stage = new Stage();
                    stage.setTitle("Muzer");
                    stage.setScene(scene);
                    stage.show();
                    stage.setResizable(false);
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                }
            }
    }

    public void goToUserPage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    public void seeArtistInformation(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PersonalPageProvider.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    public void logOut(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WelcomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }

    public void seeReviews(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ReviewOverviewProvider.fxml"));
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

    public void seeTransactions(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("TransactionPageProvider.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }
}
