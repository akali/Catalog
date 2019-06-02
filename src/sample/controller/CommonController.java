package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.SceneStorage;

import java.io.IOException;

public class CommonController {
    private Stage stage;

    public Stage getStage() {
        return this.stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    protected void changeScene(String sceneName) {
        try {
            SceneStorage storage = SceneStorage.getStorage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(storage.getUrl(sceneName));
            Parent root = loader.load();

            CommonController controller = loader.getController();
            controller.setStage(getStage());

            SceneStorage.Size sizes = storage.getSizes(sceneName);

            Scene scene = new Scene(root, sizes.getWidth(), sizes.getHeight());
            getStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
