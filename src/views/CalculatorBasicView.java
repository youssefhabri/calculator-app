package views;

import widgets.CalculatorMenu;

import javax.swing.*;

public class CalculatorBasicView extends CalculatorView {
    private JPanel rootPanel;
    private JTextField displayField;

    private JButton clrAllButton;
    private JButton clrEntryButton;
    private JButton delButton;
    private JButton dotButton;
    private JButton calcButton;

    // Operations
    private JButton modButton;
    private JButton inverseButton;
    private JButton expButton;
    private JButton sqrtButton;
    private JButton divButton;
    private JButton mulButton;
    private JButton subButton;
    private JButton addButton;

    // Numbers
    private JButton n0Button;
    private JButton n1Button;
    private JButton n2Button;
    private JButton n3Button;
    private JButton n4Button;
    private JButton n5Button;
    private JButton n6Button;
    private JButton n7Button;
    private JButton n8Button;
    private JButton n9Button;
    private JTextField lastInput;
    private JButton signButton;

    public CalculatorBasicView(CalculatorMenu menu) {
        setJMenuBar(menu);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Calculator App");
        setContentPane(rootPanel);
        setLocation(100, 100);
        setResizable(false);
        setVisible(true);
        pack();

        lastInput.setBorder(BorderFactory.createEmptyBorder());
        displayField.setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public JTextField getDisplayField() {
        return displayField;
    }

    @Override
    public JTextField getLastInputDisplay() {
        return lastInput;
    }

    @Override
    public void setDisplayField(String value) {
        displayField.setText(value);
    }

    @Override
    public JButton getNumBtn(int num) {
        assert num >= 0 && num < 10;

        return switch (num) {
            case 0 -> n0Button;
            case 1 -> n1Button;
            case 2 -> n2Button;
            case 3 -> n3Button;
            case 4 -> n4Button;
            case 5 -> n5Button;
            case 6 -> n6Button;
            case 7 -> n7Button;
            case 8 -> n8Button;
            case 9 -> n9Button;
            default -> throw new IllegalStateException("Unexpected value: " + num);
        };
    }

    @Override
    public JButton getBtn(String btn) {
        return switch (btn) {
            case "CLR_ALL" -> clrAllButton;
            case "CLR_ENTRY" -> clrEntryButton;
            case "DEL" -> delButton;
            case "DOT" -> dotButton;
            case "CALC" -> calcButton;
            case "SIGN" -> signButton;

            // Operations
            case "ADD" -> addButton;
            case "SUB" -> subButton;
            case "MUL" -> mulButton;
            case "DIV" -> divButton;
            case "SQRT" -> sqrtButton;
            case "POW2" -> expButton;
            case "MOD" -> modButton;
            case "INVERSE" -> inverseButton;
            default -> throw new IllegalStateException("Unknown view button: " + btn);
        };
    }

}
