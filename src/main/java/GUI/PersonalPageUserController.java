package GUI;

import APPLICATION.User;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    @FXML
    private Label enterValidData;

    public void initialize() {
        nameToSet.setText(Main.userMain.getName());
        firstNameToSet.setText(Main.userMain.getFirstName());
        lastNameToSet.setText(Main.userMain.getLastName());
        dateOfBirthToSet.setText(Main.userMain.getDateOfBirth().toString());
        emailToSet.setText(Main.userMain.getEmail());
        phoneNumberToSet.setText(Main.userMain.getPhoneNumber());
        passWordToSet.setText("**********");
        showPasswordButton.setVisible(true);
        hidePasswordButton.setVisible(false);
        enterValidData.setVisible(false);
    }

    public void showPassword(ActionEvent actionEvent) {
        passWordToSet.setText(Main.userMain.getPassword());
        showPasswordButton.setVisible(false);
        hidePasswordButton.setVisible(true);
    }

    public void hidePassword(ActionEvent actionEvent) {
        passWordToSet.setText("**********");
        showPasswordButton.setVisible(true);
        hidePasswordButton.setVisible(false);
    }

    public void goBack(ActionEvent actionEvent) {
        Main.loadPage("UserPage.fxml", actionEvent);
    }

    public void changePersonalInformation(ActionEvent actionEvent) {
        enterValidData.setVisible(false);
        int userNumber = Main.userMain.getUserNumber();
        String newFirstName = Main.userMain.getFirstName();
        String newLastName = Main.userMain.getLastName();
        java.sql.Date newDateofBirth = Main.userMain.getDateOfBirth();
        int newAge = Main.userMain.getAge();
        String newEmail = Main.userMain.getEmail();
        String newPhoneNumber = Main.userMain.getPhoneNumber();
        String newPassword = Main.userMain.getPassword();
        if (newFirstNameInput.getText() != "") {
            newFirstName = newFirstNameInput.getText();
        }
        if (newLastNameInput.getText() != "") {
            newLastName = newLastNameInput.getText();
        }
        if (newDateOfBirthInput.getValue() != null) {
            if (newDateOfBirthInput.getValue().isBefore(LocalDate.now())) {
                LocalDate ld = newDateOfBirthInput.getValue();
                newDateofBirth = java.sql.Date.valueOf(ld);
                newAge = Period.between(ld, LocalDate.now()).getYears();
            }
            else {
                enterValidData.setVisible(true);
                return;
            }
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
        Main.userMain = new User(userNumber, newFirstName, newLastName, newDateofBirth, newAge, newEmail, newPhoneNumber, newPassword);
        UserDAO.saveUser(Main.userMain);
        initialize();
        newFirstNameInput.clear();
        newLastNameInput.clear();
        newDateOfBirthInput.setValue(null);
        newEmailInput.clear();
        newPhoneNumberInput.clear();
        newPasswordInput.clear();
    }
}
