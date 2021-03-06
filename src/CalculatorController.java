import enums.CalculatorMode;
import views.CalculatorBasicView;
import views.CalculatorScientificView;
import views.CalculatorView;
import widgets.CalculatorMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CalculatorController {

    private CalculatorView view;
    private final CalculatorMenu menu;
    private final CalculatorModel model;
    private CalculatorMode mode = CalculatorMode.Basic;

    public CalculatorController(CalculatorModel model) {
        this.menu = new CalculatorMenu(mode);
        this.view = new CalculatorBasicView(menu);
        this.model = model;

        initializeCalculator();
    }

    private void initializeCalculator() {
        initBasicButtonListeners();
        initKeyboardListener();
        initMenuListeners();

        updateDisplay();
    }

    private void initBasicButtonListeners() {
        for (int i = 0; i <= 9; i++) {
            String btn = String.valueOf(i);
            view.getNumBtn(i).addActionListener(e -> pressedButton(btn));
        }

        ArrayList<String> buttons = new ArrayList<>();
        var basicButtons = Arrays.asList(// Actions
                "DEL", "DOT", "CLR_ENTRY", "CLR_ALL", "CALC", "SIGN",
                // Operations
                "ADD", "SUB", "MUL", "DIV", "SQRT", "POW2", "INVERSE", "MOD"
        );
        buttons.addAll(basicButtons);

        if (mode == CalculatorMode.Scientific) {
            List<String> sciButtons = Arrays.asList(
                    // Actions
                    "ANGLE",
                    // Operations
                    "LN", "EXP", "SIN", "COS", "TAN",
                    "ABS", "10X", "ASIN", "ACOS", "ATAN"
            );
            buttons.addAll(sciButtons);
        }

        for(var btn: buttons) {
            view.getBtn(btn).addActionListener(e -> pressedButton(btn));
        }
    }

    private void initKeyboardListener() {
        var keyboard = new KeyboardInput(this);
        view.addKeyListener(keyboard);
    }

    private void initMenuListeners() {
        this.menu.getBasicViewItem().setAction(new AbstractAction("Basic") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = CalculatorMode.Basic;

                changeView();
            }
        });

        this.menu.getScientificViewItem().setAction(new AbstractAction("Scientific") {
            @Override
            public void actionPerformed(ActionEvent e) {
                mode = CalculatorMode.Scientific;

                changeView();
            }
        });
    }

    private void changeView() {
        this.view.dispose();
        switch (mode) {
            case Basic -> {
                this.view = new CalculatorBasicView(this.menu);
            }
            case Scientific -> {
                this.view = new CalculatorScientificView(this.menu);
            }
        }
        this.view.repaint();

        initializeCalculator();
    }

    private void setDisplayFontSize(float size) {
        var font = view.getDisplayField().getFont().deriveFont(size);
        view.getDisplayField().setFont(font);
    }

    private void updateDisplay() {
        setDisplayFontSize(model.getErrorMode() ? 28 : 36);

        view.setDisplayField(model.getInput());

        if (model.getLastInput() == null) {
            view.getLastInputDisplay().setText("");
        } else {
            var lastInput = model.getLastInput();
            if (model.getOperation() != null) {
                lastInput += " " + model.getOperationAsString();
            }

            view.getLastInputDisplay().setText(lastInput);
        }

        if (mode == CalculatorMode.Scientific)
            view.getBtn("ANGLE").setText(model.getAngleAsString());
    }

    // TODO switch to an enum for the buttons
    protected void pressedButton(String btn) {
        switch (btn) {
            case "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
                -> model.inputNumber(Integer.parseInt(btn));
            case "DOT" -> model.inputPeriod();
            case "DEL" -> model.deleteChar();
            case "CLR_ENTRY" -> model.clearEntry();
            case "CLR_ALL" -> model.clearAll();
            case "SIGN" -> model.changeSign();
            case "CALC" -> model.getResult();
            case "ANGLE" -> model.toggleAngleUnit();

            case "ADD" -> model.inputOperation(CalculatorModel.Operations.Add);
            case "SUB" -> model.inputOperation(CalculatorModel.Operations.Sub);
            case "MUL" -> model.inputOperation(CalculatorModel.Operations.Mul);
            case "DIV" -> model.inputOperation(CalculatorModel.Operations.Div);
            case "SQRT" -> model.inputOperation(CalculatorModel.Operations.Sqrt);
            case "POW2" -> model.inputOperation(CalculatorModel.Operations.Pow2);
            case "INVERSE" -> model.inputOperation(CalculatorModel.Operations.Inverse);
            case "MOD" -> model.inputOperation(CalculatorModel.Operations.Mod);
            case "LN" -> model.inputOperation(CalculatorModel.Operations.Ln);
            case "EXP" -> model.inputOperation(CalculatorModel.Operations.Exp);
            case "ABS" -> model.inputOperation(CalculatorModel.Operations.Abs);
            case "10X" -> model.inputOperation(CalculatorModel.Operations.TenX);
            case "SIN" -> model.inputOperation(CalculatorModel.Operations.Sin);
            case "ASIN" -> model.inputOperation(CalculatorModel.Operations.ArcSin);
            case "COS" -> model.inputOperation(CalculatorModel.Operations.Cos);
            case "ACOS" -> model.inputOperation(CalculatorModel.Operations.ArcCos);
            case "TAN" -> model.inputOperation(CalculatorModel.Operations.Tan);
            case "ATAN" -> model.inputOperation(CalculatorModel.Operations.ArcTan);
            default -> System.out.println("Unknown button: " + btn);
        }

        updateDisplay();
    }
}
