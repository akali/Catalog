package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.SceneStorage;
import sample.service.AuthenticationService;

import java.io.IOException;
import java.sql.SQLException;

public class Controller extends CommonController {

    public Button homeButton;
    public Button registerButton;
    public Button serialButton;
    public Button movieButton;
    public TextField loginTextField;
    public PasswordField passwordTextField;

    private AuthenticationService authenticationService = AuthenticationService.getInstance();

    @FXML
    private Button signInButton;

    private EventHandler onSignInButtonClick = event -> {
        System.out.println("onSignInButtonClick");

        String login = loginTextField.getText();
        String password = passwordTextField.getText();

        try {
            if (authenticationService.authenticate(login, password)) {
                changeScene("home");
            } else {
                System.out.println("Wrong user or password");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    };

    private EventHandler onHomeButtonClick = event -> {
        System.out.println("onHomeButtonClick");
        homeButton.setText("home clicked");
    };

    private EventHandler onRegisterButtonClick = event -> {
        System.out.println("onHomeButtonClick");
        changeScene("register");
    };

    @FXML
    void initialize() {
        System.out.println("initialized");
        signInButton.setOnAction(onSignInButtonClick);
        homeButton.setOnAction(onHomeButtonClick);
        registerButton.setOnAction(onRegisterButtonClick);
    }



}
