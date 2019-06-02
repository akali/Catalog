package sample.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.SceneStorage;

import java.io.IOException;
import java.util.function.Function;

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
            Scene scene = getScene(sceneName);
            getStage().setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Scene getScene(String sceneName) throws IOException {
        SceneStorage storage = SceneStorage.getStorage();

        FXMLLoader loader = getFxmlLoader(sceneName, storage);
        Parent root = loader.load();

        CommonController controller = getController(loader);
        controller.setStage(getStage());

        SceneStorage.Size sizes = storage.getSizes(sceneName);

        return new Scene(root, sizes.getWidth(), sizes.getHeight());
    }

    protected Scene getSceneWithControllerSettings(String sceneName, CommonController controller) throws IOException {
        SceneStorage storage = SceneStorage.getStorage();

        FXMLLoader loader = getFxmlLoader(sceneName, storage, controller);

        Parent root = loader.load();

        ((CommonController) loader.getController()).setStage(getStage());

        SceneStorage.Size sizes = storage.getSizes(sceneName);

        return new Scene(root, sizes.getWidth(), sizes.getHeight());
    }

    protected FXMLLoader getFxmlLoader(String sceneName, SceneStorage storage) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(storage.getUrl(sceneName));
        return loader;
    }

    protected FXMLLoader getFxmlLoader(String sceneName, SceneStorage storage, CommonController controller) {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(controller);
        loader.setLocation(storage.getUrl(sceneName));
        return loader;
    }

    protected CommonController getController(FXMLLoader loader) {
        return loader.getController();
    }
}
