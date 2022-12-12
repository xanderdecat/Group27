package com.example.group27;

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
    private Label artistNameToSet;

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
    }

    public void seeDoneTransaction(ActionEvent actionEvent) {
    }
}
