package views;

import javax.swing.*;

interface CalculatorViewInterface {

    JButton getBtn(String btn);
    JButton getNumBtn(int num);

    JTextField getDisplayField();
    void setDisplayField(String text);

    JTextField getLastInputDisplay();

}

public abstract class CalculatorView extends JFrame implements CalculatorViewInterface {}