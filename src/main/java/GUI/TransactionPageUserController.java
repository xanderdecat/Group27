package GUI;

import APPLICATION.Transaction;
import DB.EventDAO;
import DB.ProviderDAO;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TransactionPageUserController {

    @FXML
    private Label artistNameToSet;
    @FXML
    private Label confirmationDateToSet;
    @FXML
    private Label eventNameToSet;
    @FXML
    private Label messageToSet;
    @FXML
    private Label totalAmountToSet;
    @FXML
    private ListView<String> transactionsDone;
    @FXML
    private ListView<String> transactionsToDo;
    @FXML
    private Label pleaseSelectTransaction;

    public void initialize() {
        setAllInvisible();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getUserNumber() == Main.userMain.getUserNumber()) {
                if (transaction.getStatus() == Transaction.status.Accepted) {
                    String s = transaction.getMessage() + " (€" + transaction.getTotalAmount() + ")";
                    transactionsToDo.getItems().add(s);
                }
                if (transaction.getStatus() == Transaction.status.Payed) {
                    String s = transaction.getMessage() + " (€" + transaction.getTotalAmount() + ")";
                    transactionsDone.getItems().add(s);
                }
            }
        }
    }

    public void setAllInvisible() {
        eventNameToSet.setVisible(false);
        artistNameToSet.setVisible(false);
        messageToSet.setVisible(false);
        totalAmountToSet.setVisible(false);
        confirmationDateToSet.setVisible(false);
        pleaseSelectTransaction.setVisible(false);
    }
    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }

    public void seeToDoTransaction(ActionEvent actionEvent) {
        setAllInvisible();
        if (transactionsToDo.getSelectionModel().getSelectedItem() != null) {
            String line = transactionsToDo.getSelectionModel().getSelectedItem();
            for (Transaction transaction : TransactionDAO.getTransactions()) {
                if (transaction.getMessage().equals(line.substring(0, line.indexOf("(") - 1))) {
                    eventNameToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getEventName());
                    artistNameToSet.setText(ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName());
                    messageToSet.setText(transaction.getMessage());
                    totalAmountToSet.setText("€" + transaction.getTotalAmount());
                    confirmationDateToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getConfirmationDate().toString());
                    eventNameToSet.setVisible(true);
                    artistNameToSet.setVisible(true);
                    messageToSet.setVisible(true);
                    totalAmountToSet.setVisible(true);
                    confirmationDateToSet.setVisible(true);
                }
            }
        }
        else
            pleaseSelectTransaction.setVisible(true);

    }

    public void seeDoneTransaction(ActionEvent actionEvent) {
        setAllInvisible();
        if (transactionsDone.getSelectionModel().getSelectedItem() != null) {
            String line = transactionsDone.getSelectionModel().getSelectedItem();
            for (Transaction transaction : TransactionDAO.getTransactions()) {
                if (transaction.getMessage().equals(line.substring(0, line.indexOf("(") - 1))) {
                    eventNameToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getEventName());
                    artistNameToSet.setText(ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName());
                    messageToSet.setText(transaction.getMessage());
                    totalAmountToSet.setText("€" + transaction.getTotalAmount());
                    confirmationDateToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getConfirmationDate().toString());
                    eventNameToSet.setVisible(true);
                    artistNameToSet.setVisible(true);
                    messageToSet.setVisible(true);
                    totalAmountToSet.setVisible(true);
                    confirmationDateToSet.setVisible(true);
                }
            }
        }
        else
            pleaseSelectTransaction.setVisible(true);
    }

    public void selectPayed(ActionEvent actionEvent) {
        setAllInvisible();
        if (transactionsToDo.getSelectionModel().getSelectedItem() != null) {
            String line = transactionsToDo.getSelectionModel().getSelectedItem();
            for (Transaction transaction : TransactionDAO.getTransactions()) {
                if (transaction.getMessage().equals(line.substring(0, line.indexOf("(") - 1))) {
                    transaction.setStatus(Transaction.status.Payed);
                    TransactionDAO.saveTransaction(transaction);
                }
            }
            transactionsToDo.getItems().clear();
            initialize();
        }
        else
            pleaseSelectTransaction.setVisible(true);
    }
}
