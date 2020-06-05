package chess.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitGame extends JFrame {
    public ExitGame() {
        super("Are you sure?");
        setLocationRelativeTo(null);
        setSize(300, 200);
        setVisible(true);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.fill = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,20, 10, 20);
        constraints.weightx = GridBagConstraints.CENTER;

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        JLabel verificationText = new JLabel("Do you really want to exit the Game?");
        verificationText.setBounds(10, 10, 100, 30);
        verificationText.setVisible(true);
        panel.add(verificationText, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        JButton yes = new JButton("Yes");
        yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        yes.setVisible(true);
        panel.add(yes, constraints);


        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.fill = GridBagConstraints.CENTER;
        JButton no = new JButton("No");
        no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        panel.add(no, constraints);
        setVisible(true);

        panel.setVisible(true);
        add(panel);

    }
}
