package GUI;

import APPLICATION.Transaction;
import DB.EventDAO;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OverviewProviderController {

    @FXML
    private Label acceptedToSet;
    @FXML
    private Label charityToSet;
    @FXML
    private Label declinedToSet;
    @FXML
    private Label earnedToSet;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private Label requestsToSet;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private Label completeOverviewText;
    @FXML
    private Button seeAllButton;

    public void initialize() {
        int acceptedRequests = 0;
        int declinedRequests = 0;
        double totalEarned = 0;
        double totalCharity = 0;
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == Main.providerMain.getProviderNumber() && EventDAO.getEvent(transaction.getEventNumber()).getEndDate().isBefore(LocalDateTime.now())) {
                if (transaction.getStatus() == Transaction.status.Accepted)
                    acceptedRequests = acceptedRequests + 1;
                if (transaction.getStatus() == Transaction.status.Payed) {
                    acceptedRequests = acceptedRequests + 1;
                    totalEarned = totalEarned + transaction.getAmountToProvider();
                    totalCharity = totalCharity + transaction.getAmountToNPO();
                }
                if (transaction.getStatus() == Transaction.status.NotAccepted)
                    declinedRequests = declinedRequests + 1;
            }
        }
        requestsToSet.setText(String.valueOf(acceptedRequests + declinedRequests));
        acceptedToSet.setText(String.valueOf(acceptedRequests));
        declinedToSet.setText(String.valueOf(declinedRequests));
        earnedToSet.setText(String.valueOf(Math.round((totalEarned) * 10) / 10.0));
        charityToSet.setText(String.valueOf(Math.round((totalCharity) * 10) / 10.0));
        completeOverviewText.setVisible(false);
        seeAllButton.setVisible(false);
    }

    public void seeSpecificOverview(ActionEvent actionEvent) {
        LocalDate startDateLD = startDatePicker.getValue();
        LocalDateTime startDate1 = LocalDateTime.of(startDateLD.getYear(), startDateLD.getMonth(), startDateLD.getDayOfMonth(), 00, 00, 00);
        LocalDate endDateLD = endDatePicker.getValue();
        LocalDateTime endDate1 = LocalDateTime.of(endDateLD.getYear(), endDateLD.getMonth(), endDateLD.getDayOfMonth(), 00, 00, 00);

        int acceptedRequests = 0;
        int declinedRequests = 0;
        double totalEarned = 0;
        double totalCharity = 0;

        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == Main.providerMain.getProviderNumber() && EventDAO.getEvent(transaction.getEventNumber()).getEndDate().isAfter(startDate1) && EventDAO.getEvent(transaction.getEventNumber()).getEndDate().isBefore(endDate1)) {
                if (transaction.getStatus() == Transaction.status.Accepted )
                    acceptedRequests = acceptedRequests + 1;
                if (transaction.getStatus() == Transaction.status.Payed) {
                    acceptedRequests = acceptedRequests + 1;
                    totalEarned = totalEarned + transaction.getAmountToProvider();
                    totalCharity = totalCharity + transaction.getAmountToNPO();
                }
                if (transaction.getStatus() == Transaction.status.NotAccepted)
                    declinedRequests = declinedRequests + 1;
            }
        }
        requestsToSet.setText(String.valueOf(acceptedRequests + declinedRequests));
        acceptedToSet.setText(String.valueOf(acceptedRequests));
        declinedToSet.setText(String.valueOf(declinedRequests));
        earnedToSet.setText(String.valueOf(Math.round((totalEarned) * 10) / 10.0));
        charityToSet.setText(String.valueOf(Math.round((totalCharity) * 10) / 10.0));
        completeOverviewText.setVisible(true);
        seeAllButton.setVisible(true);
    }

    public void seeAll(ActionEvent actionEvent) {
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
        initialize();
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("ProviderPage.fxml", actionEvent);
    }
}
