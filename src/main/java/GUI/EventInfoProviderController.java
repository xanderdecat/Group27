package GUI;

import APPLICATION.Transaction;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EventInfoProviderController {

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
    private Label payedToSet;
    @FXML
    private Label startDateToSet;
    @FXML
    private Label streetNameToSet;

    public void initialize() {
        eventNameToSet.setText(ProviderPageController.upcomingEvent.getEventName());
        startDateToSet.setText(ProviderPageController.upcomingEvent.getStartDate().toString());
        endDateToSet.setText(ProviderPageController.upcomingEvent.getEndDate().toString());
        streetNameToSet.setText(ProviderPageController.upcomingEvent.getStreetName() + " " + ProviderPageController.upcomingEvent.getHouseNumber());
        ZIPToSet.setText(String.valueOf(ProviderPageController.upcomingEvent.getZIP()));
        cityToSet.setText(ProviderPageController.upcomingEvent.getCity());
        countryToSet.setText(ProviderPageController.upcomingEvent.getCountry());
        payedToSet.setText("No");

        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == HelloApplication.providerMain.getProviderNumber() && transaction.getEventNumber() == ProviderPageController.upcomingEvent.getEventNumber() && transaction.getStatus() == Transaction.status.Payed) {
                payedToSet.setText("yes");
            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        HelloApplication.loadPage("ProviderPage.fxml", actionEvent);
    }
}
