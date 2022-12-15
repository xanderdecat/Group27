package GUI;

import APPLICATION.Transaction;
import DB.TransactionDAO;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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
    @FXML
    private Label userToSet;

    public void initialize() {
        eventNameToSet.setText(ProviderPageController.upcomingEvent.getEventName());
        userToSet.setText(UserDAO.getUser(ProviderPageController.upcomingEvent.getEventUserNumber()).getName());
        startDateToSet.setText(ProviderPageController.upcomingEvent.getStartDate().toString());
        endDateToSet.setText(ProviderPageController.upcomingEvent.getEndDate().toString());
        streetNameToSet.setText(ProviderPageController.upcomingEvent.getStreetName() + " " + ProviderPageController.upcomingEvent.getHouseNumber());
        ZIPToSet.setText(String.valueOf(ProviderPageController.upcomingEvent.getZIP()));
        cityToSet.setText(ProviderPageController.upcomingEvent.getCity());
        countryToSet.setText(ProviderPageController.upcomingEvent.getCountry());
        payedToSet.setText("No!");

        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getProviderNumber() == Main.providerMain.getProviderNumber() && transaction.getEventNumber() == ProviderPageController.upcomingEvent.getEventNumber() && transaction.getStatus() == Transaction.status.Payed) {
                payedToSet.setText("Yes!");
            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("ProviderPage.fxml", actionEvent);
    }
}
