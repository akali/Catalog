package sample.controller;

import com.mysql.jdbc.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Movie;
import sample.service.MovieService;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class HomeController extends CommonController {
    public TableView<Movie> tableView;
    public TextField searchTextField;
    public Button searchButton;

    private MovieService movieService = MovieService.getInstance();
    private ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

    public TableColumn<Movie, String> nameColumn = new TableColumn<>("Name");
    public TableColumn<Movie, String> directorColumn = new TableColumn<>("Director");
    public TableColumn<Movie, String> authorColumn = new TableColumn<>("Author");

    @FXML
    public void initialize() {

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        directorColumn.setCellValueFactory(new PropertyValueFactory<>("director"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(directorColumn);
        tableView.getColumns().add(authorColumn);

        movieObservableList.addAll(fetchAllMovies());

        tableView.setItems(movieObservableList);

        searchButton.setOnAction(actionEvent -> {
            try {
                onSearchButtonClick();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private List<Movie> fetchAllMovies() {
        try {
            List<Movie> movies = movieService.getAllMovies();
            return movies;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private void onSearchButtonClick() throws SQLException, ClassNotFoundException {
        String query = searchTextField.getText();
        if (StringUtils.isNullOrEmpty(query)) {
            movieObservableList.clear();
            movieObservableList.addAll(fetchAllMovies());
            return;
        }

        List<Movie> moviesByQuery = movieService.getMoviesByQuery(query);
        movieObservableList.clear();
        movieObservableList.addAll(moviesByQuery);
    }
}
