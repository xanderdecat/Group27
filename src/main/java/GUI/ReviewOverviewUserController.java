package GUI;

import APPLICATION.Review;
import DB.EventDAO;
import DB.ProviderDAO;
import DB.ReviewDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ReviewOverviewUserController {

    @FXML
    private ListView<Review> allReviews;
    @FXML
    private Label subjectToSet;
    @FXML
    private Label dateToSet;
    @FXML
    private Label eventToSet;
    @FXML
    private Label nameToSet;
    @FXML
    private Label scoreToSet;
    @FXML
    private Label descriptionToSet;
    @FXML
    private Label noReviewsReceived;

    public void initialize() {
        subjectToSet.setVisible(false);
        dateToSet.setVisible(false);
        eventToSet.setVisible(false);
        nameToSet.setVisible(false);
        scoreToSet.setVisible(false);
        descriptionToSet.setVisible(false);
        noReviewsReceived.setVisible(false);

        for (Review review : ReviewDAO.getReviews()) {
            if (review.getUserNumber() == Main.userMain.getUserNumber() && !review.isProviderReview()) {
                allReviews.getItems().add(review);
            }
        }

        if (allReviews.getItems().isEmpty())
            noReviewsReceived.setVisible(true);
    }

    public void seeFullReview(ActionEvent actionEvent) {
        if (allReviews.getSelectionModel().getSelectedItem() != null) {
            for (Review review : ReviewDAO.getReviews()) {
                if (review.getReviewNumber() == allReviews.getSelectionModel().getSelectedItem().getReviewNumber()) {
                    subjectToSet.setText(review.getSubject());
                    dateToSet.setText(review.getDateOfReviews().toString());
                    eventToSet.setText(EventDAO.getEvent(review.getEventNumber()).getEventName());
                    nameToSet.setText(ProviderDAO.getProvider(review.getProviderNumber()).getArtistName());
                    scoreToSet.setText(String.valueOf(review.getScoreOn10()));
                    descriptionToSet.setText(review.getDescription());
                    dateToSet.setText(review.getDateOfReviews().toString());
                    subjectToSet.setVisible(true);
                    dateToSet.setVisible(true);
                    eventToSet.setVisible(true);
                    nameToSet.setVisible(true);
                    scoreToSet.setVisible(true);
                    descriptionToSet.setVisible(true);
                }
            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }
}
