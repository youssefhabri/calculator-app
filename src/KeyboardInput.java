import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInput implements KeyListener {

    CalculatorController controller;

    public KeyboardInput(CalculatorController controller) {
        this.controller = controller;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_0, KeyEvent.VK_NUMPAD0 -> controller.pressedButton("0");
            case KeyEvent.VK_1, KeyEvent.VK_NUMPAD1 -> controller.pressedButton("1");
            case KeyEvent.VK_2, KeyEvent.VK_NUMPAD2 -> controller.pressedButton("2");
            case KeyEvent.VK_3, KeyEvent.VK_NUMPAD3 -> controller.pressedButton("3");
            case KeyEvent.VK_4, KeyEvent.VK_NUMPAD4 -> controller.pressedButton("4");
            case KeyEvent.VK_5, KeyEvent.VK_NUMPAD5 -> controller.pressedButton("5");
            case KeyEvent.VK_6, KeyEvent.VK_NUMPAD6 -> controller.pressedButton("6");
            case KeyEvent.VK_7, KeyEvent.VK_NUMPAD7 -> controller.pressedButton("7");
            case KeyEvent.VK_8, KeyEvent.VK_NUMPAD8 -> controller.pressedButton("8");
            case KeyEvent.VK_9, KeyEvent.VK_NUMPAD9 -> controller.pressedButton("9");
            case KeyEvent.VK_PERIOD, KeyEvent.VK_COMMA, KeyEvent.VK_DECIMAL
                    -> controller.pressedButton("DOT");
            // Actions
            case KeyEvent.VK_BACK_SPACE -> controller.pressedButton("DEL");
            case KeyEvent.VK_ENTER -> controller.pressedButton("CALC");

            // Operations
            case KeyEvent.VK_PLUS -> controller.pressedButton("ADD");
            case KeyEvent.VK_MINUS -> controller.pressedButton("SUB");
            case KeyEvent.VK_ASTERISK -> controller.pressedButton("MUL");
            case KeyEvent.VK_DIVIDE -> controller.pressedButton("DIV");
            default -> {}
        }
    }
}
