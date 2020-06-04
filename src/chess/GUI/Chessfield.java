package chess.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static icons.Icons.getImageIcon;

public class Chessfield extends JButton {

    public Chessfield(ImageIcon img) {
        super(img);
    }

    public void setIconTo(String fileName) {
        ImageIcon icon = getImageIcon(fileName);
        Image scaledImage = getScaledImage(icon.getImage(), this.getWidth(), this.getHeight());
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
        super.setIcon(scaledImageIcon);
    }


    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = resizedImg.createGraphics();

        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.drawImage(srcImg, 5, 5, w-10, h-10, null);
        graphics.dispose();

        return resizedImg;
    }

}
