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

    public void initialize() {
        eventNameToSet.setVisible(false);
        artistNameToSet.setVisible(false);
        messageToSet.setVisible(false);
        totalAmountToSet.setVisible(false);
        confirmationDateToSet.setVisible(false);

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

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }

    public void seeToDoTransaction(ActionEvent actionEvent) {
        String line = transactionsToDo.getSelectionModel().getSelectedItem();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getMessage().equals(line.substring(0, line.indexOf("(") - 1))) {
                eventNameToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getEventName());
                artistNameToSet.setText(ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName());
                messageToSet.setText(transaction.getMessage());
                totalAmountToSet.setText(String.valueOf(transaction.getTotalAmount()));
                confirmationDateToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getConfirmationDate().toString());
                eventNameToSet.setVisible(true);
                artistNameToSet.setVisible(true);
                messageToSet.setVisible(true);
                totalAmountToSet.setVisible(true);
                confirmationDateToSet.setVisible(true);
            }
        }
    }

    public void seeDoneTransaction(ActionEvent actionEvent) {
        String line = transactionsDone.getSelectionModel().getSelectedItem();
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getMessage().equals(line.substring(0, line.indexOf("(") - 1))) {
                eventNameToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getEventName());
                artistNameToSet.setText(ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName());
                messageToSet.setText(transaction.getMessage());
                totalAmountToSet.setText(String.valueOf(transaction.getTotalAmount()));
                confirmationDateToSet.setText(EventDAO.getEvent(transaction.getEventNumber()).getConfirmationDate().toString());
                eventNameToSet.setVisible(true);
                artistNameToSet.setVisible(true);
                messageToSet.setVisible(true);
                totalAmountToSet.setVisible(true);
                confirmationDateToSet.setVisible(true);
            }
        }
    }

    public void selectPayed(ActionEvent actionEvent) {
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
}
