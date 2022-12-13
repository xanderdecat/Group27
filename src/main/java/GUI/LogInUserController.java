package GUI;

import APPLICATION.User;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LogInUserController {

    @FXML
    private TextField emailInput;
    @FXML
    private PasswordField passwordInput;

    public void goToHomePage(ActionEvent actionEvent) {
        for (User user : UserDAO.getUsers())
            if (user.getEmail().equals(emailInput.getText()) && user.getPassword().equals(passwordInput.getText())) {
                HelloApplication.userMain = new User(user.getUserNumber(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getAge(), user.getEmail(), user.getPhoneNumber(), user.getPassword());
                HelloApplication.loadPage("UserPage.fxml", actionEvent);
            }
    }

    public void goToWelcomePage (ActionEvent actionEvent){
        HelloApplication.loadPage("WelcomePage.fxml", actionEvent);
    }
}

