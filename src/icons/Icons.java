package icons;

import javax.swing.*;
import java.net.URL;
import chess.GUI.GUIControls;

public class Icons {
    public static ImageIcon getImageIcon(String fileName) {
        URL url = Icons.class.getResource(fileName);
        if (url == null) {
            throw new RuntimeException("Resource \"" + fileName + "\" not found.");
        }
        return new ImageIcon(url);
    }
}

