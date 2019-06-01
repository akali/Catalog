package sample.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.model.User;
import sample.service.AuthenticationService;

import java.sql.SQLException;

public class RegisterController extends CommonController {
    public TextField nameTextField;
    public TextField loginField;
    public PasswordField passwordTextField;
    public TextField countryTextField;
    public TextField contactTextField;
    public TextField lastNameTextField;
    public TextField emailField;
    public Button signUpButton;

    private AuthenticationService authenticationService = AuthenticationService.getInstance();

    EventHandler<ActionEvent> onSignUpClick = actionEvent -> {
        User user = new User();
        user.setPassword(passwordTextField.getText());
        user.setContact(contactTextField.getText());
        user.setCountry(countryTextField.getText());
        user.setEmail(emailField.getText());
        user.setLogin(loginField.getText());
        user.setName(nameTextField.getText());
        user.setSurname(lastNameTextField.getText());

        try {
            if (authenticationService.register(user)) {
                System.out.println("registered!");
                changeScene("home");
            } else {
                System.out.println("could not register");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    };

    @FXML
    public void initialize() {
        signUpButton.setOnAction(onSignUpClick);

        passwordTextField.setText("123");
        contactTextField.setText("+77052105967");
        countryTextField.setText("Kazakhstan");
        emailField.setText("aisultan.kali@gmail.com");
        loginField.setText("akali");
        nameTextField.setText("Aisultan");
        lastNameTextField.setText("Kali");
    }
}
