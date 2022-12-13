package GUI;

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
import java.time.Period;

public class PersonalPageUserController {

    @FXML
    private Label dateOfBirthToSet;
    @FXML
    private Label emailToSet;
    @FXML
    private Label firstNameToSet;
    @FXML
    private Label lastNameToSet;
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
        firstNameToSet.setText(HelloApplication.userMain.getFirstName());
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
        HelloApplication.loadPage("UserPage.fxml", actionEvent);
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
        if (newFirstNameInput.getText() != "") {
            newFirstName = newFirstNameInput.getText();
        }
        if (newLastNameInput.getText() != "") {
            newLastName = newLastNameInput.getText();
        }
        if (newDateOfBirthInput.getValue() != null) {
            LocalDate ld = newDateOfBirthInput.getValue();
            newDateofBirth = java.sql.Date.valueOf(ld);
            newAge = Period.between(ld, LocalDate.now()).getYears();
        }
        if (newEmailInput.getText() != "") {
            newEmail = newEmailInput.getText();
        }
        if (newPhoneNumberInput.getText() != "") {
            newPhoneNumber = newPhoneNumberInput.getText();
        }
        if (newPasswordInput.getText() != "") {
            newPassword = newPasswordInput.getText();
        }

        HelloApplication.userMain = new User(userNumber, newFirstName, newLastName, newDateofBirth, newAge, newEmail, newPhoneNumber, newPassword);
        UserDAO.saveUser(HelloApplication.userMain);

        initialize();

        newFirstNameInput.clear();
        newLastNameInput.clear();
        newDateOfBirthInput.setValue(null);
        newEmailInput.clear();
        newPhoneNumberInput.clear();
        newPasswordInput.clear();
    }
}
