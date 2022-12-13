package GUI;

import APPLICATION.Transaction;
import DB.ProviderDAO;
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

public class EventInfoUserController {

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
    private Label startDateToSet;

    @FXML
    private Label streetNameToSet;

    @FXML
    private ListView<String> bookedArtists;

    public void initialize() {
        eventNameToSet.setText(HomeScreenPageController.upcomingEvent.getEventName());
        startDateToSet.setText(HomeScreenPageController.upcomingEvent.getStartDate().toString());
        endDateToSet.setText(HomeScreenPageController.upcomingEvent.getEndDate().toString());
        cityToSet.setText(HomeScreenPageController.upcomingEvent.getCity());
        ZIPToSet.setText(String.valueOf(HomeScreenPageController.upcomingEvent.getZIP()));
        countryToSet.setText(HomeScreenPageController.upcomingEvent.getCountry());
        streetNameToSet.setText(HomeScreenPageController.upcomingEvent.getStreetName() + " " + HomeScreenPageController.upcomingEvent.getHouseNumber());

        for (Transaction transaction : TransactionDAO.getTransactions())
            if (transaction.getEventNumber() == HomeScreenPageController.upcomingEvent.getEventNumber()) {
                String s = "(" + transaction.getStatus().toString() + ") " + ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName() + " - €" + transaction.getTotalAmount();
                bookedArtists.getItems().add(s);
            }
    }

    public void goBack(ActionEvent actionEvent) {
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
}
