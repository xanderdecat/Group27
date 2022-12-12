package com.example.group27;

import APPLICATION.Event;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ProviderPageController {

    public static Event upcomingEvent;
    public static Event previousEvent;


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
    public static Transaction requestedTransaction;


    public void initialize() {
        artistNameToSet.setText(HelloApplication.providerMain.getArtistName());

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
        }


    }

    public void viewUpcomingEvent(ActionEvent actionEvent) {
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
}
