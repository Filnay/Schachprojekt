package chess.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameEnd extends JFrame {
    public GameEnd(String status) {
        super("Game Over!");
        setVisible(true);
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new GridLayout(2, 3, 10, 10));
        panel.setBorder(new LineBorder(Color.WHITE, 20));

        JLabel label = new JLabel(status, SwingConstants.CENTER);
        label.setBackground(Color.WHITE);
        label.setVisible(true);
        panel.add(label);

        JPanel spacer1  = new JPanel();
        spacer1.setBackground(Color.WHITE);
        panel.add(spacer1);

        JPanel spacer2  = new JPanel();
        spacer2.setBackground(Color.WHITE);
        panel.add(spacer2);

        JPanel spacer3  = new JPanel();
        spacer3.setBackground(Color.WHITE);
        panel.add(spacer3);

        JButton restart = new JButton("Play Again");
        restart.setBackground(Color.lightGray);
        restart.setBorder(null);
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SetupGame();
            }
        });
        restart.setVisible(true);
        panel.add(restart);

        JButton close = new JButton("Close Game");
        close.setBackground(Color.lightGray);
        restart.setBorder(null);
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        close.setVisible(true);
        panel.add(close);


        panel.setVisible(true);
        add(panel);
    }


    public static void main(String[] args) {
        new GameEnd("Black Wins!");
    }
}
