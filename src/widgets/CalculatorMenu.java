package widgets;

import enums.CalculatorMode;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CalculatorMenu extends JMenuBar {

    private final JRadioButtonMenuItem basicViewItem;
    private final JRadioButtonMenuItem scientificViewItem;
    private CalculatorMode mode;

    public CalculatorMenu(CalculatorMode mode) {
        this.mode = mode;

        var fileMenu = new JMenu("Mode");
        add(fileMenu);

        ButtonGroup group = new ButtonGroup();

        basicViewItem = new JRadioButtonMenuItem("Basic");
        basicViewItem.setSelected(this.mode == CalculatorMode.Basic);
        group.add(basicViewItem);
        fileMenu.add(basicViewItem);

        scientificViewItem = new JRadioButtonMenuItem("Scientific");
        scientificViewItem.setSelected(this.mode == CalculatorMode.Scientific);
        group.add(scientificViewItem);
        fileMenu.add(scientificViewItem);
    }

    public JRadioButtonMenuItem getBasicViewItem() {
        return basicViewItem;
    }

    public JRadioButtonMenuItem getScientificViewItem() {
        return scientificViewItem;
    }
}
