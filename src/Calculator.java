import com.formdev.flatlaf.FlatLightLaf;
import enums.CalculatorMode;
import views.CalculatorBasicView;
import widgets.CalculatorMenu;

import javax.swing.*;

public class Calculator {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch(Exception ex1) {
            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex2) {
                System.err.println("Failed to initialize LaF");
            }
        }

        final var model = new CalculatorModel();
        final var controller = new CalculatorController(model);
    }
}
