package com.example.group27;

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

public class HomeScreenPageController {

    @FXML
    private Button continueButton;

    @FXML
    private Button upgradeButton;
    @FXML
    private Label namefield;

    @FXML
    private ListView<String> previousEventsChooser;

    @FXML
    private Label score;

    @FXML
    private ListView<String> upcomingEventsChooser;

    public static Event upcomingEvent;
    public static Event previousEvent;


    public void initialize() {
        continueButton.setVisible(false);
        upgradeButton.setVisible(true);
        namefield.setText(HelloApplication.userMain.getName());
        namefield.setVisible(true);
        score.setText(String.valueOf(User.calculateAverageScore(HelloApplication.userMain)));
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
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }

    public void goToCreateNewEvent(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddEvent.fxml"));
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

    public void goToAddProviderPage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("AddProvider.fxml"));
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

    public void continueAsProvider(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ProviderPage.fxml"));
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

    public void bookAnArtist(ActionEvent actionEvent) {
        String selected = upcomingEventsChooser.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                upcomingEvent = EventDAO.getEvent(ev.getEventNumber());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("EventPage.fxml"));
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

    public void viewEvent(ActionEvent actionEvent) {
        String selected = upcomingEventsChooser.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                upcomingEvent = EventDAO.getEvent(ev.getEventNumber());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("EventPageRequested.fxml"));
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

    public void viewPreviousEvent(ActionEvent actionEvent) {
        String selected = previousEventsChooser.getSelectionModel().getSelectedItem();
        for (Event ev : EventDAO.getEvents())
            if (ev.getEventName().equals(selected.substring(0, selected.indexOf("-") - 1))) {
                previousEvent = EventDAO.getEvent(ev.getEventNumber());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("ReviewPageUser.fxml"));
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


    public void seePersonalInformation(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("PersonalPageUser.fxml"));
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
            fxmlLoader.setLocation(getClass().getResource("TransactionPageUser.fxml"));
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

    public void goToReviews(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("ReviewOverview.fxml"));
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
