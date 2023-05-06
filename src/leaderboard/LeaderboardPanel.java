package leaderboard;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.MainMenu;

public class LeaderboardPanel implements ActionListener {
    JFrame window;
    Font font;
    Leaderboard leaderboard;

    JButton backButton;

    public LeaderboardPanel(JFrame frame) {
        leaderboard = new Leaderboard();
        load_font();

        window = frame;
        window.getContentPane().removeAll();
        window.setTitle("Leaderboard");

        JPanel panel = new JPanel();

        // label
        try {
            BufferedImage Background = ImageIO.read(new File("res/leaderboard/background.png"));
            JLabel backgroundLabel = new JLabel(new ImageIcon(Background));
            backgroundLabel.setLayout(new GridBagLayout());
            panel.add(backgroundLabel);
            GridBagConstraints gbc = new GridBagConstraints();
            for (int i = 0; i < leaderboard.listSize; i++) {
                // Name
                Record record = leaderboard.recordList[i];
                JLabel label = new JLabel(record.name);
                label.setForeground(Color.WHITE);
                label.setFont(font.deriveFont(Font.PLAIN, 32f));
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.gridx = 0;
                gbc.gridy = (i * 4);
                backgroundLabel.add(label, gbc);

                // Line space
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.gridx = 0;
                gbc.gridy = (i * 4) + 1;
                backgroundLabel.add(Box.createVerticalStrut(2), gbc);

                // Date
                label = new JLabel(record.date);
                label.setForeground(Color.WHITE);
                label.setFont(font.deriveFont(Font.PLAIN, 16f));
                gbc.gridheight = 1;
                gbc.gridwidth = 1;
                gbc.gridx = 0;
                gbc.gridy = (i * 4) + 2;
                backgroundLabel.add(label, gbc);

                gbc.gridheight = 2;
                gbc.gridwidth = 1;
                gbc.gridx = 1;
                gbc.gridy = (i * 4);
                backgroundLabel.add(Box.createHorizontalStrut(200), gbc);

                // Score
                label = new JLabel(Integer.toString(record.score));
                label.setForeground(Color.WHITE);
                label.setFont(font.deriveFont(Font.PLAIN, 41f));
                gbc.gridheight = 3;
                gbc.gridwidth = 1;
                gbc.gridx = 2;
                gbc.gridy = (i * 4);
                backgroundLabel.add(label, gbc);
                // Line space
                gbc.gridheight = 1;
                gbc.gridwidth = 3;
                gbc.gridx = 0;
                gbc.gridy = (i * 4) + 3;
                backgroundLabel.add(Box.createVerticalStrut(20), gbc);
            }

            BufferedImage back = ImageIO.read(new File("res/leaderboard/Back.png"));
            backButton = new JButton();
            backButton.setIcon(new ImageIcon(back));
            backButton.setPreferredSize(new Dimension(285, 71));
            backButton.setContentAreaFilled(false);
            backButton.setBorderPainted(false);
            backButton.setActionCommand("back");
            backButton.addActionListener(this);

            gbc.gridheight = 1;
            gbc.gridwidth = 3;
            gbc.gridx = 0;
            gbc.gridy++;
            backgroundLabel.add(backButton, gbc);

            window.add(panel);
            window.setVisible(true);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    void load_font() {
        try {
            InputStream is = getClass().getResourceAsStream("/fonts/DiaryOfAn8BitMage-lYDD.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "back":
            new MainMenu(window);
            break;
        }
    }
}
