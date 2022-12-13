package GUI;

import APPLICATION.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class AddUserController {

    @FXML
    private DatePicker dateOfBirth;
    @FXML
    private TextField email;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField wachtwoordInput;

    public void goToHomePage(ActionEvent actionEvent) {
        if (dateOfBirth.getValue() != null && email.getText() != null && firstName.getText() != null && lastName.getText() != null && phoneNumber.getText() != null && wachtwoordInput.getText() != null) {
            if(dateOfBirth.getClass().equals(DatePicker.class) ){
                String firstName1 = firstName.getText();
                String lastName1 = lastName.getText();
                LocalDate ld = dateOfBirth.getValue();
                java.sql.Date testdate = java.sql.Date.valueOf(ld);


                String email1 = email.getText();
                String phoneNumber1 = phoneNumber.getText();
                String wachtwoord1 = wachtwoordInput.getText();


                HelloApplication.userMain = new User(firstName1, lastName1, testdate, email1, phoneNumber1, wachtwoord1);
                DB.UserDAO.saveUser(HelloApplication.userMain);   // toevoegen aan DataBase

                try {

                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("UserPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                    Stage stage = new Stage();
                    stage.setTitle("Muzer");
                    stage.setScene(scene);
                    stage.show();
                    stage.setResizable(false);
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                }
            }
        }
    }

    public void goToWelcomePage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WelcomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }
}
