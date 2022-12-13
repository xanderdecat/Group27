package GUI;

import APPLICATION.Event;
import APPLICATION.Provider;
import APPLICATION.User;
import DB.EventDAO;
import DB.ProviderDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;

public class UserPageController {

    public static Event upcomingEvent;
    public static Event previousEvent;
    @FXML
    private Button continueButton;
    @FXML
    private Button upgradeButton;
    @FXML
    private Label nameToSet;
    @FXML
    private ListView<String> previousEventsChooser;
    @FXML
    private Label scoreToSet;
    @FXML
    private ListView<String> upcomingEventsChooser;

    public void initialize() {
        continueButton.setVisible(false);
        upgradeButton.setVisible(true);
        nameToSet.setText(HelloApplication.userMain.getName());

        if (User.calculateAverageScoreForUser(HelloApplication.userMain) == 0)
            scoreToSet.setText("-");
        else
            scoreToSet.setText(String.valueOf(User.calculateAverageScoreForUser(HelloApplication.userMain)));

        for (Provider provider : ProviderDAO.getProviders()) {
            if (provider.getUserNumber() == HelloApplication.userMain.getUserNumber()) {
                HelloApplication.providerMain = provider;
                continueButton.setVisible(true);
                upgradeButton.setVisible(false);
            }
        }

        for (Event event : EventDAO.getEvents()) {
            if (event.getEventUserNumber() == HelloApplication.userMain.getUserNumber() && event.getEndDate().isAfter(LocalDateTime.now())) {
                String s = event.getEventName() + " - " + event.getCity();
                upcomingEventsChooser.getItems().add(s);
            }
            if (event.getEventUserNumber() == HelloApplication.userMain.getUserNumber() && event.getEndDate().isBefore(LocalDateTime.now())) {
                String s = event.getEventName() + " - " + event.getCity();
                previousEventsChooser.getItems().add(s);
            }
        }
    }

    public void bookAnArtist(ActionEvent actionEvent) {
        String selected = upcomingEventsChooser.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                upcomingEvent = EventDAO.getEvent(ev.getEventNumber());
                HelloApplication.loadPage("EventPageUser.fxml", actionEvent);
            }
    }

    public void viewEvent(ActionEvent actionEvent) {
        String selected = upcomingEventsChooser.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                upcomingEvent = EventDAO.getEvent(ev.getEventNumber());
                HelloApplication.loadPage("EventInfoUser.fxml", actionEvent);
            }

    }

    public void viewPreviousEvent(ActionEvent actionEvent) {
        String selected = previousEventsChooser.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                previousEvent = EventDAO.getEvent(ev.getEventNumber());
                HelloApplication.loadPage("ReviewPageUser.fxml", actionEvent);
            }
    }

    public void logOut(ActionEvent actionEvent) {
        HelloApplication.loadPage("WelcomePage.fxml", actionEvent);
    }

    public void goToCreateNewEvent(ActionEvent actionEvent) {
        HelloApplication.loadPage("AddEventUser.fxml", actionEvent);
    }

    public void goToAddProviderPage(ActionEvent actionEvent) {
        HelloApplication.loadPage("AddProvider.fxml", actionEvent);
    }

    public void continueAsProvider(ActionEvent actionEvent) {
        HelloApplication.loadPage("ProviderPage.fxml", actionEvent);
    }

    public void seePersonalInformation(ActionEvent actionEvent) {
        HelloApplication.loadPage("PersonalPageUser.fxml", actionEvent);
    }

    public void seeTransactions(ActionEvent actionEvent) {
        HelloApplication.loadPage("TransactionPageUser.fxml", actionEvent);
    }

    public void goToReviews(ActionEvent actionEvent) {
        HelloApplication.loadPage("ReviewOverviewUser.fxml", actionEvent);
    }

}
