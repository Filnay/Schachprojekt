package chess.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUIChangeSkin extends JFrame {

    public GUIChangeSkin() {
        super("Chess Skins");
        setVisible(true);
        setSize(500, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        showSkins();
    }

    public void showSkins() {
        JPanel skinPanelFrame = new JPanel();


        skinPanelFrame.setLayout(new GridLayout(0, 3, 30, 20));
        skinPanelFrame.setBorder(new LineBorder(Color.WHITE, 20));
        skinPanelFrame.setBackground(Color.WHITE);

        JPanel skinPanel1 = new JPanel();
        skinPanel1.setSize(this.getWidth() / 3, this.getHeight()/ 3);

        Chessfield skin1 = new Chessfield(null);
        skin1.setSize(100, 100);
        skin1.setButtonIconTo("King_Black.png");
        skin1.setBackground(Color.lightGray);
        skin1.setBorder(null);

        skin1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        skinPanel1.add(skin1);
        skinPanelFrame.add(skinPanel1);

        JPanel skinPanel2 = new JPanel();
        skinPanel2.setSize(100, 100);

        Chessfield skin2 = new Chessfield(null);
        skin2.setSize(100, 100);
        skin2.setButtonIconTo("King_Black.png");
        skin2.setBackground(Color.lightGray);
        skin2.setBorder(null);

        skin2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        skinPanel2.add(skin2);
        skinPanelFrame.add(skinPanel2);

        JPanel skinPanel3 = new JPanel();
        skinPanel3.setSize(100, 100);

        Chessfield skin3 = new Chessfield(null);
        skin3.setSize(100, 100);
        skin3.setButtonIconTo("King_Black.png");
        skin3.setBackground(Color.lightGray);
        skin3.setBorder(null);

        skin3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        skinPanel3.add(skin3);
        skinPanelFrame.add(skinPanel3);

        add(skinPanelFrame);
        setVisible(true);



//        JLabel skin1Text = new JLabel("Skin1", SwingConstants.CENTER);
//        skin1Text.setVerticalAlignment(SwingConstants.CENTER);
//        skinPanel.add(skin1Text);
//
//        JLabel skin2Text = new JLabel("Skin2", SwingConstants.CENTER);
//        skin2Text.setVerticalAlignment(SwingConstants.CENTER);
//        skinPanel.add(skin2Text);
//
//        JLabel skin3Text = new JLabel("Skin3", SwingConstants.CENTER);
//        skin3Text.setVerticalAlignment(SwingConstants.CENTER);
//        skinPanel.add(skin3Text);
//        GridBagLayout skinLayout = new GridBagLayout();
//        skinPanel.setLayout(skinLayout);
//        GridBagConstraints constraints = new GridBagConstraints();
//
//        constraints.fill = GridBagConstraints.CENTER;
//        constraints.insets = new Insets(10,20, 10, 20);
//        constraints.weightx = GridBagConstraints.CENTER;
//
//
//        constraints.gridx = 0;
//        constraints.gridy = 0;
//        constraints.gridwidth = 3;
//        constraints.gridheight = 5;
//
//        Chessfield skin1 = new Chessfield(null);
//        skin1.setSize(70, 70);
//        skin1.setButtonIconTo("King_Black.png");
//        skin1.setBackground(Color.lightGray);
//        skin1.setBorder(null);
//        skinPanel.add(skin1);
//
//        constraints.gridx = 4;
//
//        Chessfield skin2 = new Chessfield(null);
//        skin2.setSize(70, 70);
//        skin2.setButtonIconTo("King_Black.png");
//        skin2.setBackground(Color.lightGray);
//        skin2.setBorder(null);
//        skinPanel.add(skin2);
//
//        constraints.gridx = 9;
//
//        Chessfield skin3 = new Chessfield(null);
//        skin3.setSize(70, 70);
//        skin3.setButtonIconTo("King_Black.png");
//        skin3.setBackground(Color.lightGray);
//        skin3.setBorder(null);
//        skinPanel.add(skin3);

//        constraints.gridx = 1;
//        constraints.gridy = 6;
//        constraints.gridwidth = 1;
//        constraints.gridheight = 1;
//
//        JLabel skin1Text = new JLabel("Skin1", SwingConstants.CENTER);
//        skin1Text.setVerticalAlignment(SwingConstants.TOP);
//        skinPanel.add(skin1Text);
//
//
//        constraints.gridx = 5;
//
//        JLabel skin2Text = new JLabel("Skin1", SwingConstants.CENTER);
//        skin2Text.setVerticalAlignment(SwingConstants.TOP);
//        skinPanel.add(skin2Text);
//
//
//        constraints.gridx = 9;
//
//        JLabel skin3Text = new JLabel("Skin1", SwingConstants.CENTER);
//        skin3Text.setVerticalAlignment(SwingConstants.TOP);
//        skinPanel.add(skin3Text);
    }

    public static void main(String[] args) {
        new GUIChangeSkin();
    }
}
