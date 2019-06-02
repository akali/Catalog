package sample;

import javafx.scene.Scene;

import java.net.URL;
import java.util.HashMap;

public class SceneStorage {

    public static class Size {
        double width, height;

        public Size(double width, double height) {
            this.width = width;
            this.height = height;
        }

        public double getWidth() {
            return width;
        }

        public void setWidth(double width) {
            this.width = width;
        }

        public double getHeight() {
            return height;
        }

        public void setHeight(double height) {
            this.height = height;
        }
    }

    private static SceneStorage sceneStorage;

    private HashMap<String, URL> urls;
    private HashMap<String, Size> sizes;

    public URL getUrl(String scene) {
        return this.urls.get(scene);
    }
    public Size getSizes(String scene) {
        return this.sizes.get(scene);
    }

    private SceneStorage() {
        this.urls = new HashMap<>() {{
            put("login", Main.class.getResource("view/sample.fxml"));
            put("home", Main.class.getResource("view/home.fxml"));
            put("register", Main.class.getResource("view/register.fxml"));
        }};
        this.sizes = new HashMap<>() {{
            put("login", new Size(600, 400));
            put("register", new Size(600, 400));
            put("home", new Size(1000, 600));
        }};
    }

    public static SceneStorage getStorage() {
        if (sceneStorage == null)
            sceneStorage = new SceneStorage();
        return sceneStorage;
    }
}
