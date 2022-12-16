package GUI;

import APPLICATION.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class AddUserController {

    @FXML
    private DatePicker dateOfBirthInput;
    @FXML
    private TextField emailInput;
    @FXML
    private TextField firstNameInput;
    @FXML
    private TextField lastNameInput;
    @FXML
    private TextField phoneNumberInput;
    @FXML
    private TextField passwordInput;
    @FXML
    private Label enterValidData;

    public void initialize() {
        enterValidData.setVisible(false);
    }

    public void goToHomePage(ActionEvent actionEvent) {
        boolean OK = false;
        if (dateOfBirthInput.getValue() != null && dateOfBirthInput.getClass().equals(DatePicker.class) && !emailInput.getText().equals("") && !firstNameInput.getText().equals("") && !lastNameInput.getText().equals("") && !phoneNumberInput.getText().equals("") && !passwordInput.getText().equals("")) {
            if(dateOfBirthInput.getValue().isBefore(LocalDate.now())){
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                LocalDate ld = dateOfBirthInput.getValue();
                java.sql.Date date = java.sql.Date.valueOf(ld);
                String email = emailInput.getText();
                String phoneNumber = phoneNumberInput.getText();
                String password = passwordInput.getText();
                Main.userMain = new User(firstName, lastName, date, email, phoneNumber, password);
                DB.UserDAO.saveUser(Main.userMain);
                Main.loadPage("UserPage.fxml", actionEvent);
                OK = true;
            }
        }
        if (!OK)
            enterValidData.setVisible(true);
    }

    public void goToWelcomePage(ActionEvent actionEvent) {
        Main.loadPage("WelcomePage.fxml", actionEvent);
    }

    public void goToLogInPage(ActionEvent actionEvent) {
        Main.loadPage("LogInUser.fxml", actionEvent);
    }

}
