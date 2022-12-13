package GUI;

import APPLICATION.Transaction;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class EventPageProviderController {

    @FXML
    private Label ZIPToSet;
    @FXML
    private Label cityToSet;
    @FXML
    private Label countryToSet;
    @FXML
    private Label descriptionToSet;
    @FXML
    private Label endDateToSet;
    @FXML
    private Label eventNameToSet;
    @FXML
    private Hyperlink linkToSet;
    @FXML
    private Label startDateToSet;
    @FXML
    private Label streetNameToSet;

    public void initialize() {
        eventNameToSet.setText(ProviderPageController.requestedEvent.getEventName());
        startDateToSet.setText(ProviderPageController.requestedEvent.getStartDate().toString());
        endDateToSet.setText(ProviderPageController.requestedEvent.getEndDate().toString());
        streetNameToSet.setText(ProviderPageController.requestedEvent.getStreetName() + " " + ProviderPageController.requestedEvent.getHouseNumber());
        ZIPToSet.setText(String.valueOf(ProviderPageController.requestedEvent.getZIP()));
        cityToSet.setText(ProviderPageController.requestedEvent.getCity());
        countryToSet.setText(ProviderPageController.requestedEvent.getCountry());
        linkToSet.setText(String.valueOf(ProviderPageController.requestedEvent.getLinkToPage()));
        descriptionToSet.setText(ProviderPageController.requestedEvent.getDescription());
    }


    public void acceptRequest(ActionEvent actionEvent) {
        ProviderPageController.requestedTransaction.setStatus(Transaction.status.Accepted);
        TransactionDAO.saveTransaction(ProviderPageController.requestedTransaction);
        HelloApplication.loadPage("ProviderPage.fxml", actionEvent);
    }

    public void declineRequest(ActionEvent actionEvent) {
        ProviderPageController.requestedTransaction.setStatus(Transaction.status.NotAccepted);
        TransactionDAO.saveTransaction(ProviderPageController.requestedTransaction);
        HelloApplication.loadPage("ProviderPage.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) {
        HelloApplication.loadPage("ProviderPage.fxml", actionEvent);
    }
}
