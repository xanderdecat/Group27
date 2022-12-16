package GUI;

import APPLICATION.User;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LogInUserController {

    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label incorrectEmailPassword;

    public void initialize() {
        incorrectEmailPassword.setVisible(false);
    }

    public void goToHomePage(ActionEvent actionEvent) {
        boolean OK = false;
        for (User user : UserDAO.getUsers()) {
            if (user.getEmail().equals(emailInput.getText()) && user.getPassword().equals(passwordInput.getText())) {
                OK = true;
                Main.userMain = new User(user.getUserNumber(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getAge(), user.getEmail(), user.getPhoneNumber(), user.getPassword());
                Main.loadPage("UserPage.fxml", actionEvent);
            }
        }
        if (!OK)
            incorrectEmailPassword.setVisible(true);

    }

    public void goToWelcomePage (ActionEvent actionEvent){
        Main.loadPage("WelcomePage.fxml", actionEvent);
    }

    public void goToNewUserPage(ActionEvent actionEvent) throws IOException {
        Main.loadPage("AddUser.fxml", actionEvent);
    }
}

