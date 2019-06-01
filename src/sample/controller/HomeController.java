package sample.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.Movie;
import sample.service.MovieService;

import java.sql.SQLException;
import java.util.List;

public class HomeController extends CommonController {
    public TableView<Movie> tableView;

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
//        Thread t = new Thread(() -> {
            try {
                List<Movie> movies = movieService.getAllMovies();
                movieObservableList.addAll(movies);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
//        });
//        t.start();
        tableView.setItems(movieObservableList);
    }
}
