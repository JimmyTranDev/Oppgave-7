import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

public class Trad extends Thread {
    JLabel[][] ruter;
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
                Thread.sleep(1000);
                this.updateSnake(this.retning);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public Trad(JLabel[][] ruter) {
        this.ruter = ruter;
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

        // int[] arr2 = { 5, 6 };
        // this.ruter[5][6].setText("+");
        // this.ruter[5][6].setBackground(Color.green);
        // this.slange.addLast(arr2);
    }

    public void updateSnake(String retning) throws InterruptedException {
        // @Todo cant go back on self
        // @Todo length count
        // @Todo handle corner and eat self

        int[] forrigeRute = new int[2];
        Boolean isEatingTreasure = false;
        for (int i = 0; i < this.slange.size(); i++) {
            System.out.println(i);
            int[] slangeDel = this.slange.get(i);
            JLabel rute = this.ruter[slangeDel[0]][slangeDel[1]];

            if (i == 0) {
                if (slangeDel[0] > 12 || slangeDel[1] > 12 || slangeDel[0] < 0 || slangeDel[1] < 0) {
                    throw new InterruptedException("Out");
                }

                rute.setText("");
                rute.setBackground(Color.white);
                forrigeRute = slangeDel.clone();

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

                rute = this.ruter[slangeDel[0]][slangeDel[1]];
                isEatingTreasure = rute.getText() == "$";
                if (rute.getText() == "+") {
                    throw new InterruptedException("Invalid");
                }

                rute.setText("O");
                rute.setBackground(Color.green);
                rute.setForeground(Color.black);
            } else {
                System.out.println(String.format("%d,%d",
                        slangeDel[0],
                        slangeDel[1]));

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
