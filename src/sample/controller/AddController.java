package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.Movie;
import sample.service.AuthenticationService;
import sample.service.MovieService;

import java.sql.SQLException;

public class AddController extends CommonController {
    public TextField imageTextField;

    public TextField nameTextField;
    public TextField descriptionTextField;
    public TextField directorTextField;
    public TextField yearTextField;
    public TextField countryTextField;
    public Button saveButton;

    private AuthenticationService authenticationService = AuthenticationService.getInstance();
    private MovieService movieService = MovieService.getInstance();

    @FXML
    public void initialize() {
        saveButton.setOnAction(actionEvent -> {
            Movie movie = new Movie();
            movie.setAuthor(authenticationService.getCurrentUser());
            movie.setCountry(countryTextField.getText());
            movie.setImageUrl(imageTextField.getText());
            movie.setName(nameTextField.getText());
            movie.setDescription(descriptionTextField.getText());
            movie.setDirector(directorTextField.getText());
            movie.setYear(Integer.parseInt(yearTextField.getText()));

            try {
                movieService.saveMovie(movie);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            ((Stage)saveButton.getScene().getWindow()).close();

//            getStage().close();
        });
    }
}
