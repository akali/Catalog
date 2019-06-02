package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.model.Movie;

public class DetailController extends CommonController {
    public ImageView imageView;
    public Label nameLabel;
    public Label descriptionLabel;
    public Label directorLabel;
    public Label yearLabel;
    public Label countryLabel;
    public Label authorLabel;

    private Movie movie;

    public DetailController(Movie movie) {
        this.movie = movie;
    }

    public DetailController() {
    }

    @FXML
    public void initialize() {
        nameLabel.setText(movie.getName());
        descriptionLabel.setText(movie.getDescription());
        directorLabel.setText(movie.getDirector());
        yearLabel.setText(String.valueOf(movie.getYear()));
        countryLabel.setText(movie.getCountry());
        authorLabel.setText(movie.getAuthor().getLogin());

        Image image = new Image(movie.getImageUrl(), true);
        imageView.setImage(image);
    }
}
