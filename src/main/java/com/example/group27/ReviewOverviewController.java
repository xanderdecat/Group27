package com.example.group27;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReviewOverviewController {

    @FXML
    private ListView<String> allReviews;

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
                        String s = review.getSubject();
                        allReviews.getItems().add(s);
                    }
                }
            }

    /**/

    public void seeFullReview(ActionEvent actionEvent) {

        Review review =new Review();
        for (Review reviewToFind : ReviewDAO.getReviews()){
            if(reviewToFind.getSubject().equals(allReviews.getSelectionModel().getSelectedItem())){
                review = reviewToFind;
            }
        }
        descriptionToSet.setText(review.getDescription());
        scoreToSet.setText(String.valueOf(review.getScoreOn10()));
        subjectToSet.setText(review.getSubject());

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


}
