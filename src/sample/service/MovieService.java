package sample.service;

import sample.model.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private final static String TABLE = "movie";
    private static MovieService ourInstance = new MovieService();

    public static MovieService getInstance() {
        return ourInstance;
    }

    private DatabaseService databaseService = DatabaseService.getInstance();
    private AuthenticationService authenticationService = AuthenticationService.getInstance();
    private MovieService() {
    }


    public List<Movie> getAllMovies() throws SQLException, ClassNotFoundException {
        Connection connection = databaseService.getConnection();
        PreparedStatement statement =
                connection.prepareStatement(
                        "select name, description, country, director, year, imageUrl, author from movie"
                );
        List<Movie> list = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Movie movie = new Movie();

            movie.setName(resultSet.getString(1));
            movie.setDescription(resultSet.getString(2));
            movie.setCountry(resultSet.getString(3));
            movie.setDirector(resultSet.getString(4));
            movie.setYear(resultSet.getInt(5));
            movie.setImageUrl(resultSet.getString(6));
            movie.setAuthor(authenticationService.getUser(resultSet.getLong(7)));

            list.add(movie);
        }

        return list;
    }
}
