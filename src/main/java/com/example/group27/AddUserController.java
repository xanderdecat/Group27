package com.example.group27;

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
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class AddUserController {

    @FXML
    private Button RegisterButton;

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

    // gaat verder naar HOMEPAGE indien gegevens OK
    public void goToHomePage(ActionEvent actionEvent) {
        if (dateOfBirth.getValue() != null && email.getText() != null && firstName.getText() != null && lastName.getText() != null && phoneNumber.getText() != null) {
            String firstName1 = firstName.getText();
            String lastName1 = lastName.getText();
            LocalDate ld = dateOfBirth.getValue();
            Instant instant = Instant.from(ld.atStartOfDay(ZoneId.systemDefault()));
            Date dob = Date.from(instant);
            String email1 = email.getText();
            String phoneNumber1 = phoneNumber.getText();

            HelloApplication.userMain = new User(firstName1, lastName1, dob, email1, phoneNumber1);
            DB.UserDAO.saveUser(HelloApplication.userMain);   // toevoegen aan DataBase

            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 630, 400);
                Stage stage = new Stage();
                stage.setTitle("Muzer");
                stage.setScene(scene);
                stage.show();
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
            }
        }
    }

    public void goToWelcomePage(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WelcomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 630, 400);
            Stage stage = new Stage();
            stage.setTitle("Event App");
            stage.setScene(scene);
            stage.show();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
        }
    }
}
