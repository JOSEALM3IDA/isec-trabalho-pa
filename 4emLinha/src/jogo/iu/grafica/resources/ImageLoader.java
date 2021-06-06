package jogo.iu.grafica.resources;

import javafx.scene.image.Image;

import java.util.HashMap;

public class ImageLoader {

    static HashMap<String, Image> imgCache;

    static { imgCache = new HashMap<>(); }

    private ImageLoader() {}

    public static Image getImage(String name) {

        Image img = imgCache.get(name);
        if (img != null) return img;

        return new Image(Resources.getResourceAsFileStream("images/" + name));
    }

    public static Image getImageForce(String name) {
        imgCache.remove(name);
        return getImage(name);
    }

}
