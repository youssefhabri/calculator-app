import javax.swing.*;
import java.awt.*;

public class CalculatorView extends JFrame {
    private JPanel rootPanel;
    private JTextField textField1;

    private JButton clrButton;
    private JButton clrAllButton;
    private JButton delButton;
    private JButton dotButton;
    private JButton calcButton;

    // Operations
    private JButton modButton;
    private JButton a_xButton;
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

    public CalculatorView() {
        rootPanel.setPreferredSize(new Dimension(340, 480));

        setTitle("Calculator App");
        setContentPane(rootPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setResizable(false);
    }

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

}
