package sample;

import javafx.scene.Scene;

import java.net.URL;
import java.util.HashMap;

public class SceneStorage {
    private static SceneStorage sceneStorage;

    private HashMap<String, URL> urls;

    public URL getUrl(String scene) {
        return this.urls.get(scene);
    }

    private SceneStorage() {
        this.urls = new HashMap<>() {{
            put("login", Main.class.getResource("view/sample.fxml"));
            put("home", Main.class.getResource("view/home.fxml"));
            put("register", Main.class.getResource("view/register.fxml"));
        }};
    }

    public static SceneStorage getStorage() {
        if (sceneStorage == null)
            sceneStorage = new SceneStorage();
        return sceneStorage;
    }
}
