package GUI;

import APPLICATION.Transaction;
import DB.TransactionDAO;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

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
    @FXML
    private Label userToSet;

    public void initialize() {
        eventNameToSet.setText(ProviderPageController.requestedEvent.getEventName());
        userToSet.setText(UserDAO.getUser(ProviderPageController.requestedEvent.getEventUserNumber()).getName());
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
        Main.loadPage("ProviderPage.fxml", actionEvent);
    }

    public void declineRequest(ActionEvent actionEvent) {
        ProviderPageController.requestedTransaction.setStatus(Transaction.status.NotAccepted);
        TransactionDAO.saveTransaction(ProviderPageController.requestedTransaction);
        Main.loadPage("ProviderPage.fxml", actionEvent);
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("ProviderPage.fxml", actionEvent);
    }
}
