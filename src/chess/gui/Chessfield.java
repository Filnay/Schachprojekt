package chess.gui;
//imports
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static icons.Icons.getImageIcon;


//Class written By Finns Father


public class Chessfield extends JButton {

    //Constructor
    public Chessfield(ImageIcon img) {
        super(img);
    }
    public Chessfield() {

    }

    //Sets Icon in Button to Image, calls getScaledButtonImage-method
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
