package GUI;

import javafx.event.ActionEvent;

import java.io.IOException;

public class WelcomePageController {

    public void goToLogInPage(ActionEvent actionEvent) {
        Main.loadPage("LogInUser.fxml", actionEvent);
    }

    public void goToNewUserPage(ActionEvent actionEvent) throws IOException {
        Main.loadPage("AddUser.fxml", actionEvent);
    }
}
