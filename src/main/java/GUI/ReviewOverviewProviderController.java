package GUI;

import APPLICATION.Review;
import DB.EventDAO;
import DB.ReviewDAO;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ReviewOverviewProviderController {

    @FXML
    private ListView<Review> allReviews;
    @FXML
    private Label descriptionToSet;
    @FXML
    private Label eventToSet;
    @FXML
    private Label nameToSet;
    @FXML
    private Label scoreToSet;
    @FXML
    private Label subjectToSet;
    @FXML
    private Label dateToSet;
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
            if (review.getProviderNumber() == Main.providerMain.getProviderNumber() && review.isProviderReview()) {
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
                    descriptionToSet.setText(review.getDescription());
                    dateToSet.setText(review.getDateOfReviews().toString());
                    eventToSet.setText(EventDAO.getEvent(review.getEventNumber()).getEventName());
                    nameToSet.setText(UserDAO.getUser(review.getUserNumber()).getName());
                    scoreToSet.setText(String.valueOf(review.getScoreOn10()));
                    subjectToSet.setText(review.getSubject());
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
        Main.loadPage("ProviderPage.fxml", actionEvent);
    }
}
