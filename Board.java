import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.Random;

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
    Random rand = new Random();
    HashMap<String, int[]> treasureDict = new HashMap<>();

    Board() {
        // Initialize frame
        this.frame = new JFrame();
        this.frame.setLayout(new GridLayout(2, 2));

        // Stop button
        this.stopButton = new JButton("Stop");
        this.stopButton.addActionListener(e -> {
            this.frame.dispose();
        });
        // this.lengthPanel.add(lengthLabel);

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

        // Starting head
        this.ruter[6][6].setText("O");
        this.ruter[6][6].setBackground(Color.green);

        // Generate Treasure
        fillTreasure();

        // this.rutePanel.setBackground(Color.green);

        // Add panels and show frame
        this.frame.add(this.lengthPanel);
        this.frame.add(this.movePanel);
        this.frame.add(this.stopButton);
        this.frame.add(this.rutePanel, 2, 2);
        this.frame.setSize(500, 500);
        this.frame.setVisible(true);
    }

    private void fillTreasure() {
        while (treasureDict.size() < 10) {
            int row = rand.nextInt(12);
            int column = rand.nextInt(12);
            String newKey = String.format("%d,%d", row, column);

            for (String key : treasureDict.keySet()) {
                if (newKey == key) {
                    continue;
                }
            }

            JLabel rute = this.ruter[row][column];
            rute.setText("$");
            rute.setBackground(Color.green);

            int[] value = new int[] { row, column };
            treasureDict.put(newKey, value);
        }
    }

    // private static void createButton(String name, JFrame frame) {
    // JButton button = new JButton(name);
    // button.setBounds(130, 100, 100, 40);
    // frame.add(button);
    // }
}
