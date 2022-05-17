package panda.utils;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class Utils {

    public static Image getImage(String path, int width, int height){
        Image output = null;
        if(path!=null){
            File file = new File(path);
            if(file.exists()){
                try {
                    String localUrl = file.toURI().toURL().toString();
                    output = new Image(localUrl, width, height,true, true);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }

    return output;
    }

    public static Map<String, Double> getFullScreenDimension() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        HashMap<String, Double> resultDimention = new HashMap<>();
            resultDimention.put("height", screenSize.getHeight());
            resultDimention.put("width", screenSize.getWidth());
        return resultDimention;
    }

    public static void startLink(String link) throws IOException, URISyntaxException {
            URI uri = new URI(link);
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(uri);
            }
    }
}
