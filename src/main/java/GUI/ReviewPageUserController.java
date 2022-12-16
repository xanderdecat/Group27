package GUI;

import APPLICATION.Provider;
import APPLICATION.Review;
import APPLICATION.Transaction;
import DB.ProviderDAO;
import DB.ReviewDAO;
import DB.TransactionDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

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
    @FXML
    private Label enterValidData;

    public void initialize() {
        enterValidData.setVisible(false);
        scoreOn10.getItems().addAll("1/10", "2/10", "3/10", "4/10", "5/10", "6/10", "7/10", "8/10", "9/10", "10/10");
        eventNameToSet.setText(UserPageController.previousEvent.getEventName());
        startDateToSet.setText(UserPageController.previousEvent.getStartDate().toString());
        endDateToSet.setText(UserPageController.previousEvent.getEndDate().toString());
        cityToSet.setText(UserPageController.previousEvent.getCity());
        for (Transaction transaction : TransactionDAO.getTransactions()) {
            if (transaction.getUserNumber() == Main.userMain.getUserNumber() && (transaction.getStatus() == Transaction.status.Accepted || transaction.getStatus() == Transaction.status.Payed) && transaction.getEventNumber() == UserPageController.previousEvent.getEventUserNumber()) {
                String s = ProviderDAO.getProvider(transaction.getProviderNumber()).getArtistName();
                artistToChoose.getItems().add(s);
            }
        }
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }

    public void submitReview(ActionEvent actionEvent) {
        if(artistToChoose.getSelectionModel().getSelectedItem() != null && !descriptionInput.getText().equals("") && !scoreOn10.getValue().equals("") && !subjectInput.getText().equals("")) {
            int providerNumber = 0;
            String artist = artistToChoose.getSelectionModel().getSelectedItem();
            for(Provider provider : ProviderDAO.getProviders()) {
                if(artist.equals(provider.getArtistName())) {
                    providerNumber = provider.getProviderNumber();
                }
            }
            String subject = subjectInput.getText();
            int score = Integer.parseInt(scoreOn10.getValue().substring(0,scoreOn10.getValue().indexOf("/")));
            String description = descriptionInput.getText();
            Review review = new Review(UserPageController.previousEvent.getEventNumber(), true, UserPageController.previousEvent.getEventUserNumber(), providerNumber, subject, score, description);
            ReviewDAO.save(review);
            Main.loadPage("UserPage.fxml", actionEvent);

        }
        else
            enterValidData.setVisible(true);
    }

}
