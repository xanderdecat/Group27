package GUI;

import APPLICATION.Provider;
import APPLICATION.Review;
import DB.ProviderDAO;
import DB.ReviewDAO;
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

public class ReviewPageProviderController {

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
    @FXML
    private ListView<String> userToChoose;

    // initialize to do!

    public void goBack(ActionEvent actionEvent) {
        HelloApplication.loadPage("ProviderPageController.fxml", actionEvent);
    }


    public void submitReview(ActionEvent actionEvent) {
        if (userToChoose != null && descriptionInput != null && scoreOn10 != null && subjectInput != null) {
            int providerNumber = 0;
            String artist = userToChoose.getSelectionModel().getSelectedItem();
            for (Provider provider : ProviderDAO.getProviders()) {
                if (artist.equals(provider.getArtistName())) {
                    providerNumber = provider.getProviderNumber();
                }
            }
            String subject = subjectInput.getText();
            int score = Integer.parseInt(scoreOn10.getValue().toString().substring(0, scoreOn10.getValue().indexOf("/")));
            String description = descriptionInput.getText();
            Review review = new Review(UserPageController.previousEvent.getEventNumber(), true, UserPageController.previousEvent.getEventUserNumber(), providerNumber, subject, score, description);
            ReviewDAO.save(review);
            HelloApplication.loadPage("UserPage.fxml", actionEvent);
        }
    }
}