package com.example.group27;

import APPLICATION.Transaction;
import DB.EventDAO;
import DB.ProviderDAO;
import DB.TransactionDAO;
import DB.UserDAO;
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

public class TransactionPageProviderController {

    @FXML
    private Label nameToSet;

    @FXML
    private Label confirmationDateToSet;

    @FXML
    private Label eventNameToSet;

    @FXML
    private Label messageToSet;

    @FXML
    private ListView<String> openTransactions;

    @FXML
    private ListView<String> receivedTransactions;

    @FXML
    private Label totalAmountToSet;

    public void initialize() {
        eventNameToSet.setVisible(false);
        nameToSet.setVisible(false);
        messageToSet.setVisible(false);
        totalAmountToSet.setVisible(false);
        confirmationDateToSet.setVisible(false);

        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == HelloApplication.providerMain.getProviderNumber()) {
                if (transaction.getStatus() == Transaction.status.Accepted) {
                    String s = transaction.getMessage() + " (€" + transaction.getTotalAmount() + ")";
                    openTransactions.getItems().add(s);
                }
                if (transaction.getStatus() == Transaction.status.Payed) {
                    String s = transaction.getMessage() + " (€" + transaction.getTotalAmount() + ")";
                    receivedTransactions.getItems().add(s);
                }
            }
        }
    }
    public void goBack(ActionEvent actionEvent) {
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

    public void seeToDoTransaction(ActionEvent actionEvent) {
        String line = openTransactions.getSelectionModel().getSelectedItem();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getMessage().equals(line.substring(0, line.indexOf("(") - 1))) {
                eventNameToSet.setVisible(true);
                nameToSet.setVisible(true);
                messageToSet.setVisible(true);
                totalAmountToSet.setVisible(true);
                confirmationDateToSet.setVisible(true);

                eventNameToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getEventName());
                nameToSet.setText(UserDAO.getUser(transaction.getUserNumber()).getName());
                messageToSet.setText(transaction.getMessage());
                totalAmountToSet.setText(String.valueOf(transaction.getTotalAmount()));
                confirmationDateToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getConfirmationDate().toString());
            }
        }
    }

    public void seeDoneTransaction(ActionEvent actionEvent) {
        String line = receivedTransactions.getSelectionModel().getSelectedItem();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getMessage().equals(line.substring(0, line.indexOf("(") - 1))) {
                eventNameToSet.setVisible(true);
                nameToSet.setVisible(true);
                messageToSet.setVisible(true);
                totalAmountToSet.setVisible(true);
                confirmationDateToSet.setVisible(true);

                eventNameToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getEventName());
                nameToSet.setText(UserDAO.getUser(transaction.getUserNumber()).getName());
                messageToSet.setText(transaction.getMessage());
                totalAmountToSet.setText(String.valueOf(transaction.getTotalAmount()));
                confirmationDateToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getConfirmationDate().toString());
            }
        }
    }
}
