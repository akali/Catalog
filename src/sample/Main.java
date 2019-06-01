package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.controller.CommonController;

import java.io.IOException;

public class Main extends Application {

    private Stage primaryStage;
    private Parent rootLayout;

    public void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        SceneStorage storage = SceneStorage.getStorage();
        loader.setLocation(
                storage.getUrl("login")
        );
        rootLayout = loader.load();

        CommonController controller = loader.getController();
        controller.setStage(primaryStage);

        Scene scene = new Scene(rootLayout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Netflix");
        initRootLayout();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
