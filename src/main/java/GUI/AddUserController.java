package GUI;

import APPLICATION.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
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

    public void goToHomePage(ActionEvent actionEvent) {
        if (dateOfBirthInput.getValue() != null && emailInput.getText() != null && firstNameInput.getText() != null && lastNameInput.getText() != null && phoneNumberInput.getText() != null && passwordInput.getText() != null) {
            if(dateOfBirthInput.getClass().equals(DatePicker.class) ){
                String firstName = firstNameInput.getText();
                String lastName = lastNameInput.getText();
                LocalDate ld = dateOfBirthInput.getValue();
                java.sql.Date date = java.sql.Date.valueOf(ld);
                String email = emailInput.getText();
                String phoneNumber = phoneNumberInput.getText();
                String password = passwordInput.getText();
                HelloApplication.userMain = new User(firstName, lastName, date, email, phoneNumber, password);
                DB.UserDAO.saveUser(HelloApplication.userMain);
                HelloApplication.loadPage("UserPage.fxml", actionEvent);
            }
        }
    }

    public void goToWelcomePage(ActionEvent actionEvent) {
        HelloApplication.loadPage("WelcomePage.fxml", actionEvent);
    }
}
