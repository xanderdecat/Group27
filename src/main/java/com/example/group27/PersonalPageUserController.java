package com.example.group27;

import APPLICATION.User;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;

public class PersonalPageUserController {

    @FXML
    private Label dateOfBirthToSet;

    @FXML
    private Label emailToSet;

    @FXML
    private Label firstNametoSet;

    @FXML
    private Label lastNameToSet;

    @FXML
    private Button makeNewEvent;

    @FXML
    private Label nameToSet;

    @FXML
    private DatePicker newDateOfBirthInput;

    @FXML
    private TextField newEmailInput;

    @FXML
    private TextField newFirstNameInput;

    @FXML
    private TextField newLastNameInput;

    @FXML
    private TextField newPasswordInput;

    @FXML
    private TextField newPhoneNumberInput;

    @FXML
    private Label passWordToSet;

    @FXML
    private Label phoneNumberToSet;

    @FXML
    private Button showPasswordButton;

    @FXML
    private Button hidePasswordButton;

    public void initialize() {
        nameToSet.setText(HelloApplication.userMain.getName());
        firstNametoSet.setText(HelloApplication.userMain.getFirstName());
        lastNameToSet.setText(HelloApplication.userMain.getLastName());
        dateOfBirthToSet.setText(HelloApplication.userMain.getDateOfBirth().toString());
        emailToSet.setText(HelloApplication.userMain.getEmail());
        phoneNumberToSet.setText(HelloApplication.userMain.getPhoneNumber());
        passWordToSet.setText("**********");
        showPasswordButton.setVisible(true);
        hidePasswordButton.setVisible(false);
    }

    public void showPassword(ActionEvent actionEvent) {
        passWordToSet.setText(HelloApplication.userMain.getPassword());
        showPasswordButton.setVisible(false);
        hidePasswordButton.setVisible(true);
    }

    public void hidePassword(ActionEvent actionEvent) {
        passWordToSet.setText("**********");
        showPasswordButton.setVisible(true);
        hidePasswordButton.setVisible(false);
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
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }

    public void changePersonalInformation(ActionEvent actionEvent) {
        int userNumber = HelloApplication.userMain.getUserNumber();
        String newFirstName = HelloApplication.userMain.getFirstName();
        String newLastName = HelloApplication.userMain.getLastName();
        java.sql.Date newDateofBirth = HelloApplication.userMain.getDateOfBirth();
        int newAge = HelloApplication.userMain.getAge();
        String newEmail = HelloApplication.userMain.getEmail();
        String newPhoneNumber = HelloApplication.userMain.getPhoneNumber();
        String newPassword = HelloApplication.userMain.getPassword();
        if (newFirstNameInput.getText() != null) {
            newFirstName = newFirstNameInput.getText();
        }
        if (newLastNameInput != null) {
            newLastName = newLastNameInput.getText();
        }
        if (newDateOfBirthInput != null) {
            LocalDate ld = newDateOfBirthInput.getValue();
            newDateofBirth = java.sql.Date.valueOf(ld);
            newAge = Period.between(ld, LocalDate.now()).getYears();
        }
        if (newEmailInput != null) {
            newEmail = newEmailInput.getText();
        }
        if (newPhoneNumberInput != null) {
            newPhoneNumber = newPhoneNumberInput.getText();
        }
        if (newPasswordInput != null) {
            newPassword = newPasswordInput.getText();
        }

        HelloApplication.userMain = new User(userNumber, newFirstName, newLastName, newDateofBirth, newAge, newEmail, newPhoneNumber, newPassword);
        UserDAO.saveUser(HelloApplication.userMain);

        initialize();

    }


}
