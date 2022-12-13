package GUI;

import javafx.event.ActionEvent;

import java.io.IOException;

public class WelcomePageController {

    public void goToLogInPage(ActionEvent actionEvent) {
        HelloApplication.loadPage("LogInUser.fxml", actionEvent);
    }

    public void goToNewUserPage(ActionEvent actionEvent) throws IOException {
        HelloApplication.loadPage("AddUser.fxml", actionEvent);
    }
}
