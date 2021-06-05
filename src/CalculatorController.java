public class CalculatorController {

    private final CalculatorView view;

    public CalculatorController(CalculatorView view) {
        this.view = view;

        initButtonListeners();
    }

    private void initButtonListeners() {
        for (int i = 0; i <= 9; i++) {
            String btn = String.valueOf(i);
            view.getNumBtn(i).addActionListener(e -> pressedButton(btn));
        }
    }

    private void pressedButton(String btn) {
        System.out.println(btn);
    }
}
