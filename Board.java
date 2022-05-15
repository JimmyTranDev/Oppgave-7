import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Board {
    JFrame frame;
    JPanel lengthPanel;
    JPanel movePanel;
    JPanel rutePanel;
    JButton northButton;
    JButton southButton;
    JButton eastButton;
    JButton westButton;
    JButton stopButton;
    int slangeLengde;
    JLabel[][] ruter = new JLabel[12][12];
    JLabel lengthLabel;

    Board() {
        // Initialize frame
        this.frame = new JFrame();
        this.frame.setLayout(new GridLayout(2, 4));

        // Stop button
        this.stopButton = new JButton("Stop");
        this.stopButton.addActionListener(e -> {
            this.frame.dispose();
        });

        // Text Label
        this.lengthPanel = new JPanel();
        this.lengthLabel = new JLabel("1 Length");
        this.lengthPanel.add(lengthLabel);

        // creating buttons
        this.movePanel = new JPanel(new BorderLayout());
        this.northButton = new JButton("NORTH");
        this.southButton = new JButton("SOUTH");
        this.eastButton = new JButton("EAST");
        this.westButton = new JButton("WEST");

        Trad trad = new Trad(this.ruter, this.lengthLabel);

        // Event listner
        this.northButton.addActionListener(e -> {
            trad.setDirection("N");
        });

        this.southButton.addActionListener(e -> {
            trad.setDirection("S");
        });

        this.eastButton.addActionListener(e -> {
            trad.setDirection("E");
        });

        this.westButton.addActionListener(e -> {
            trad.setDirection("W");
        });

        this.movePanel.add(this.northButton, BorderLayout.NORTH);
        this.movePanel.add(this.southButton, BorderLayout.SOUTH);
        this.movePanel.add(this.eastButton, BorderLayout.EAST);
        this.movePanel.add(this.westButton, BorderLayout.WEST);

        // Create ruter
        this.rutePanel = new JPanel(new GridLayout(12, 12));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                JLabel label = new JLabel("", SwingConstants.CENTER);
                label.setBackground(Color.white);
                label.setOpaque(true);
                label.setBorder(blackline);
                this.ruter[i][j] = label;
                this.rutePanel.add(label);
            }
        }

        // Add panels and show frame
        this.frame.add(this.lengthPanel);
        this.frame.add(this.movePanel);
        this.frame.add(this.stopButton);
        this.frame.add(this.rutePanel);

        this.frame.setSize(500, 500);
        this.frame.setVisible(true);

        trad.run();
    }

}
