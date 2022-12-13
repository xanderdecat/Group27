package GUI;

import APPLICATION.Review;
import DB.ReviewDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReviewOverviewUserController {

    @FXML
    private ListView<Review> allReviews;
    @FXML
    private Label descriptionToSet;
    @FXML
    private Label scoreToSet;
    @FXML
    private Label subjectToSet;

    public void initialize() {
        subjectToSet.setVisible(false);
        scoreToSet.setVisible(false);
        descriptionToSet.setVisible(false);


        for (Review review : ReviewDAO.getReviews()) {
            if (review.getUserNumber() == HelloApplication.userMain.getUserNumber() && !review.isProviderReview()) {
                allReviews.getItems().add(review);
            }
        }
    }

    public void seeFullReview(ActionEvent actionEvent) {
        for (Review review : ReviewDAO.getReviews()) {
            if (review.getReviewNumber() == allReviews.getSelectionModel().getSelectedItem().getReviewNumber()) {
                descriptionToSet.setText(review.getDescription());
                scoreToSet.setText(String.valueOf(review.getScoreOn10()));
                subjectToSet.setText(review.getSubject());
                subjectToSet.setVisible(true);
                scoreToSet.setVisible(true);
                descriptionToSet.setVisible(true);

            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        HelloApplication.loadPage("UserPage.fxml", actionEvent);
    }
}
