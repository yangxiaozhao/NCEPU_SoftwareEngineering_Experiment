package cn.edu.ncepu.tankgame.Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageCache {

    /**
     * 哈希存储，检索快
     */
    private static Map<String, BufferedImage> map = new HashMap<>();

    public static BufferedImage get(String key) {
        return map.get(key);
    }

    public static BufferedImage load(String key, String path) {
    	    BufferedImage buff = readImage(path);
    	    if (buff == null) {
    	        System.out.println("Failed to load image from " + path);
    	    } else {
    	        System.out.println("Loaded image from " + path);
    	    }
    	    map.put(key, buff);
    	    return buff;
    }

    public static BufferedImage readImage(String path) {
        try {
            InputStream inputStream = ImageCache.class.getClassLoader().getResourceAsStream(path);
            //return ImageIO.read(new File(path));
            return ImageIO.read(inputStream);
        } catch (Exception e) {
            return null;
        }
    }

    static {
        load("player", "image/player.gif");
        load("enemy1", "image/enemy1.gif");
        load("enemy2", "image/enemy2.gif");
        load("walls", "image/walls.gif");
        load("steels", "image/steels.gif");
        load("bullet", "image/bullet.png");
        load("home", "image/home1.png");
        load("home_over", "image/home2.png");
    }
}
