import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Trad extends Thread {
    JLabel[][] ruter;
    JLabel lengthLabel;
    public String retning = "N";
    HashMap<String, int[]> treasureDict = new HashMap<>();
    Random rand = new Random();
    int treasureCount = 0;
    LinkedList<int[]> slange = new LinkedList<>();

    @Override
    public void run() {
        setHead();
        this.fillTreasure();

        while (true) {
            try {
                int compensatedCount = 1000 - (this.slange.size() * 10);
                if (compensatedCount > 0) {
                    Thread.sleep(compensatedCount);
                } else {
                    Thread.sleep(50);
                }

                this.updateSnake(this.retning);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public Trad(JLabel[][] ruter, JLabel lengthLabel) {
        this.ruter = ruter;
        this.lengthLabel = lengthLabel;
    }

    public void setDirection(String retning) {
        if (this.retning == retning) {
        } else {
            this.retning = retning;
        }
    }

    public void setHead() {
        int[] arr = { 6, 6 };
        this.ruter[6][6].setText("O");
        this.ruter[6][6].setBackground(Color.green);
        this.slange.addLast(arr);
    }

    public void updateSnake(String retning) throws InterruptedException {
        int[] forrigeRute = new int[2];
        Boolean isEatingTreasure = false;
        for (int i = 0; i < this.slange.size(); i++) {
            int[] slangeDel = this.slange.get(i);
            JLabel rute = this.ruter[slangeDel[0]][slangeDel[1]];

            if (i == 0) {
                int[] holder = slangeDel.clone();

                switch (this.retning) {
                    case "N":
                        slangeDel[0] -= 1;
                        break;

                    case "S":
                        slangeDel[0] += 1;
                        break;

                    case "W":
                        slangeDel[1] -= 1;
                        break;

                    case "E":
                        slangeDel[1] += 1;
                        break;

                    default:
                        break;
                }

                if (slangeDel[0] > 11 || slangeDel[1] > 11 || slangeDel[0] < 0 || slangeDel[1] < 0) {
                    throw new InterruptedException("Out");
                }

                JLabel nyRute = this.ruter[slangeDel[0]][slangeDel[1]];
                isEatingTreasure = nyRute.getText() == "$";
                if (nyRute.getText() == "+") {
                    throw new InterruptedException("Invalid");
                }
                nyRute.setText("O");
                nyRute.setBackground(Color.green);
                nyRute.setForeground(Color.black);

                rute.setText("");
                rute.setBackground(Color.white);
                forrigeRute = holder;
            } else {
                rute.setText("");
                rute.setBackground(Color.white);
                int[] holder = slangeDel.clone();

                JLabel nyrute = this.ruter[forrigeRute[0]][forrigeRute[1]];
                nyrute.setText("+");
                nyrute.setBackground(Color.green);

                slangeDel[0] = forrigeRute[0];
                slangeDel[1] = forrigeRute[1];
                forrigeRute = holder;
            }
        }

        if (isEatingTreasure) {
            this.treasureCount -= 1;
            this.fillTreasure();
            JLabel rute = this.ruter[forrigeRute[0]][forrigeRute[1]];
            rute.setText("+");
            rute.setBackground(Color.green);
            this.slange.addLast(forrigeRute);
            this.lengthLabel.setText(String.format("%d Length", slange.size()));
        }
    }

    private void fillTreasure() {
        while (treasureCount < 10) {
            int row = rand.nextInt(12);
            int column = rand.nextInt(12);
            JLabel rute = this.ruter[row][column];

            if (rute.getText() != "") {
                continue;
            }

            rute.setText("$");
            rute.setForeground(Color.red);
            treasureCount += 1;
        }
    }
}
