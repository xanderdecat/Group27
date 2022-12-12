package com.example.group27;

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
    private ChoiceBox<String > scoreOn10;

    @FXML
    private Label startDateToSet;

    @FXML
    private TextField subjectInput;

    @FXML
    private ListView<String> userToChoose;


    public void goBack(ActionEvent actionEvent) {
    }

    public void submitReview(ActionEvent actionEvent) {
    }
}
