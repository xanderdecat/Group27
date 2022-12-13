package GUI;

import APPLICATION.Provider;
import APPLICATION.Review;
import APPLICATION.Transaction;
import DB.ProviderDAO;
import DB.ReviewDAO;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ReviewPageUserController {

    @FXML
    private ListView<String> artistToChoose;

    @FXML
    private Label cityToSet;

    @FXML
    private TextField descriptionInput;

    @FXML
    private Label endDateToSet;

    @FXML
    private Label eventNameToSet;

    @FXML
    private ChoiceBox<String> scoreOn10;

    @FXML
    private Label startDateToSet;

    @FXML
    private TextField subjectInput;

    public void initialize() {
        scoreOn10.getItems().addAll("1/10", "2/10", "3/10", "4/10", "5/10", "6/10", "7/10", "8/10", "9/10", "10/10");
        eventNameToSet.setText(HomeScreenPageController.previousEvent.getEventName());
        startDateToSet.setText(HomeScreenPageController.previousEvent.getStartDate().toString());
        endDateToSet.setText(HomeScreenPageController.previousEvent.getEndDate().toString());
        cityToSet.setText(HomeScreenPageController.previousEvent.getCity());
            for (Transaction transaction : TransactionDAO.getTransactions()) {
                if (transaction.getUserNumber() == HelloApplication.userMain.getUserNumber()) {
                    for (Provider provider : ProviderDAO.getProviders()) {
                        if (provider.getProviderNumber() == transaction.getProviderNumber()) {
                            String s = provider.getArtistName();
                            artistToChoose.getItems().add(s);
                        }
                    }
                }
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
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }

    public void submitReview(ActionEvent actionEvent) {
        if(artistToChoose !=null && descriptionInput != null && scoreOn10 != null && subjectInput != null) {
            int providerNumber = 0;
            String artist = artistToChoose.getSelectionModel().getSelectedItem();
            for(Provider provider : ProviderDAO.getProviders()){
            if(artist.equals(provider.getArtistName())){
                providerNumber = provider.getProviderNumber();
            }}
            String subject = subjectInput.getText();
            int score = Integer.parseInt(scoreOn10.getValue().toString().substring(0,scoreOn10.getValue().indexOf("/")));
            String description = descriptionInput.getText();
            Review review = new Review(HomeScreenPageController.previousEvent.getEventNumber(), true, HomeScreenPageController.previousEvent.getEventUserNumber(), providerNumber, subject, score, description);
            ReviewDAO.save(review);


        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
             }
}}
