package sample.controller;

import com.mysql.jdbc.StringUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.model.Movie;
import sample.service.MovieService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class HomeController extends CommonController {
    public TableView<Movie> tableView;
    public TextField searchTextField;
    public Button searchButton;
    public Button addButton;
    public Button detailsButton;

    private MovieService movieService = MovieService.getInstance();
    private ObservableList<Movie> movieObservableList = FXCollections.observableArrayList();

    private TableColumn<Movie, String> nameColumn = new TableColumn<>("Name");
    private TableColumn<Movie, String> directorColumn = new TableColumn<>("Director");
    private TableColumn<Movie, String> authorColumn = new TableColumn<>("Author");

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

        detailsButton.setOnAction(actionEvent -> {
            try {
                onDetailClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        addButton.setOnAction(actionEvent -> {
            try {
                onAddButtonClick();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void onAddButtonClick() throws IOException {
        Stage dialog = new Stage();
        dialog.setScene(getScene("add"));

        dialog.initOwner(getStage());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }

    private void onDetailClick() throws IOException {
        Movie selectedItem = tableView.getSelectionModel().getSelectedItem();

        if (selectedItem == null) return;

        System.out.println(selectedItem);

        Stage dialog = new Stage();

        dialog.setScene(getSceneWithControllerSettings("detail", new DetailController(selectedItem)));

        dialog.initOwner(getStage());
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.showAndWait();
    }

    private List<Movie> fetchAllMovies() {
        try {
            return movieService.getAllMovies();
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
