package chess.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static icons.Icons.getImageIcon;

//Class written By Finns Father

public class ChessField extends JButton {
    
    /*
    Class for better Handling of the loading of the Images into the Buttons.
    Loads and Scales the Images.
    */
    
    //Constructor
    public ChessField(ImageIcon img) {
        super(img);
    }

    //Sets Icon in Button to Image
    public void setButtonIconTo(String fileName) {
        ImageIcon icon = getImageIcon(fileName);
        Image scaledImage = getScaledButtonImage(icon.getImage(), this.getWidth(), this.getHeight());
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        super.setIcon(scaledImageIcon);
    }


    //Scales the Image so it fits properly into the Button
    private Image getScaledButtonImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resizedImg.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(srcImg, 5, 5, w-10, h-10, null);
        graphics.dispose();

        return resizedImg;
    }

}
