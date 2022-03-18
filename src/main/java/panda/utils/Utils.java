package panda.utils;

import javafx.scene.image.Image;

import java.io.File;
import java.net.MalformedURLException;

public class Utils {
    public static Image getImage(String path){
        Image output = null;
        if(path!=null){
            File file = new File(path);
            if(file.exists()){
                try {
                    String localUrl = file.toURI().toURL().toString();
                    output = new Image(localUrl, 300, 100,true, true);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

    return output;
    }
}
