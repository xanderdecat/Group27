package GUI;

import APPLICATION.Provider;
import APPLICATION.Review;
import DB.ProviderDAO;
import DB.ReviewDAO;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    private Label userToSet;
    @FXML
    private Label enterValidData;

    public void initialize() {
        enterValidData.setVisible(false);
        scoreOn10.getItems().addAll("1/10", "2/10", "3/10", "4/10", "5/10", "6/10", "7/10", "8/10", "9/10", "10/10");
        eventNameToSet.setText(ProviderPageController.previousEvent.getEventName());
        userToSet.setText(UserDAO.getUser(ProviderPageController.previousEvent.getEventUserNumber()).getName());
        startDateToSet.setText(ProviderPageController.previousEvent.getStartDate().toString());
        endDateToSet.setText(ProviderPageController.previousEvent.getEndDate().toString());
        cityToSet.setText(ProviderPageController.previousEvent.getCity());
    }

    public void submitReview(ActionEvent actionEvent) {
        if (!descriptionInput.getText().equals("") && !scoreOn10.getValue().equals("") && !subjectInput.getText().equals("")) {
            String subject = subjectInput.getText();
            int score = Integer.parseInt(scoreOn10.getValue().substring(0, scoreOn10.getValue().indexOf("/")));
            String description = descriptionInput.getText();
            Review review = new Review(ProviderPageController.previousEvent.getEventNumber(), false, ProviderPageController.previousEvent.getEventUserNumber(), Main.providerMain.getProviderNumber(), subject, score, description);
            ReviewDAO.save(review);
            Main.loadPage("ProviderPage.fxml", actionEvent);
        }
        else
            enterValidData.setVisible(true);
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("ProviderPage.fxml", actionEvent);
    }

}