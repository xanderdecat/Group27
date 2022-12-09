package com.example.group27;

import APPLICATION.User;
import DB.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LogInUserController {

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;




    // gaat verder naar HOMEPAGE
    public void goToHomePage(ActionEvent actionEvent) {
        for (User user : UserDAO.getUsers())
            if (user.getEmail().equals(emailInput.getText()) && user.getPassword().equals(passwordInput.getText())) {
                HelloApplication.userMain = new User(user.getUserNumber(), user.getFirstName(), user.getLastName(), user.getDateOfBirth(), user.getAge(), user.getEmail(), user.getPhoneNumber(), user.getPassword());
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("HomeScreenPage.fxml"));
                    Scene scene = new Scene(fxmlLoader.load(), 800, 500);
                    Stage stage = new Stage();
                    stage.setTitle("Muzer");
                    stage.setScene(scene);
                    stage.show();
                    ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
                } catch (IOException e) {
                }

            }
    }


    // gaat terug naar WELCOMEPAGE
    public void goToWelcomePage (ActionEvent actionEvent){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("WelcomePage.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 500);
            Stage stage = new Stage();
            stage.setTitle("Muzer");
            stage.setScene(scene);
            stage.show();
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
        }
    }
}

