import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculatorModel {

    enum Operations {
        Add, Sub, Mul, Div,
        Sqrt, Pow2, Inverse, Mod,
        Ln, Exp, Sin, Cos, Tan
    }

    enum AngleUnit {
        Deg, Rad, Grad
    }

    private AngleUnit angleUnit = AngleUnit.Deg;
    private Operations operation = null;
    private String lastInput = null;
    private String input = "0";

    private boolean firstDigit = true;
    private boolean errorMode = false;

    public String getInput() {
        return input;
    }

    public String getLastInput() {
        return lastInput;
    }

    public Operations getOperation() {
        return operation;
    }

    public AngleUnit getAngleUnit() {
        return angleUnit;
    }

    public String getAngleAsString() {
        return switch (angleUnit) {
            case Deg -> "DEG";
            case Rad -> "RAD";
            case Grad -> "GRAD";
        };
    }

    public String getOperationAsString() {
        return switch (operation) {
            case Add -> "+";
            case Sub -> "-";
            case Mul -> "×";
            case Div -> "÷";
            case Mod -> "%";
            default -> "";
        };
    }

    public double getInputAsDouble() {
        try {
            return Double.parseDouble(input);
        } catch (Exception ex) {
            return 0.0;
        }
    }

    public double getLastInputAsDouble() {
        try {
            return Double.parseDouble(lastInput);
        } catch (Exception ex) {
            return 0.0;
        }
    }

    public boolean getErrorMode() {
        return errorMode;
    }

    public void inputNumber(int num) {
        if (errorMode) return;

        if (input.length() >= 15) return;

        if (firstDigit) {
            firstDigit = false;
            input = String.valueOf(num);
            return;
        }

        if (input.equals("-0")) {
            input = "-" + num;
            return;
        }

        if (input.equals("0")) {
            input = String.valueOf(num);
            return;
        }

        input += num;
    }

    public void deleteChar() {
        // TODO Clear Entry instead
        if (errorMode) return;

        if (firstDigit) {
            clearEntry();
            return;
        }

        var newInput = input.substring(0, input.length() - 1);
        if ((input.startsWith("-") && input.length() == 2) || newInput.length() == 0) {
            firstDigit = true;
            input = "0";
            return;
        }

        input = newInput;
    }

    public void inputPeriod() {
        if (errorMode) return;

        if (firstDigit) {
            input = "0.";
            firstDigit = false;
            return;
        }

        if (input.contains("."))
            return;

        input += ".";
    }

    public void inputOperation(Operations op) {
        switch (op) {
            case Sqrt, Pow2, Inverse, Ln, Exp, Sin, Cos, Tan -> {
                var prevOperation = operation;
                this.operation = op;
                var result = calculate();

                if (errorMode) return;

                input = String.valueOf(result);
                firstDigit = true;
                this.operation = prevOperation;
            }
            default -> {
                var result = calculate();
                if (errorMode) return;

                this.operation = op;
                lastInput = String.valueOf(result);
                firstDigit = true;
                input = "0";
            }
        }
    }

    public void changeSign() {
        if (errorMode) return;

        if (input.startsWith("-")) {
            input = input.substring(1);
        } else {
            input = "-" + input;
        }

        firstDigit = input.equals("0");
    }

    public void clearEntry() {
        input = "0";
        firstDigit = true;
        errorMode = false;
    }

    public void clearAll() {
        lastInput = null;
        operation = null;

        clearEntry();
    }

    private static BigDecimal truncateDecimal(double x,int numberOfDecimals)
    {
        var roundingMode = x > 0 ? RoundingMode.FLOOR : RoundingMode.CEILING;
        return new BigDecimal(String.valueOf(x)).setScale(numberOfDecimals, roundingMode);
    }

    private double toRad(double angle) {
        return switch (angleUnit) {
            case Deg -> Math.toRadians(angle);
            case Rad -> angle;
            case Grad -> angle * Math.PI / 200;
        };
    }

    private double calculate() {
        var prev = getLastInputAsDouble();
        var curr = getInputAsDouble();

        if (operation == null) return curr;

        var result = switch (operation) {
            case Add -> prev + curr;
            case Sub -> prev - curr;
            case Mul -> {
                if (prev == 0) prev = 1;
                yield prev * curr;
            }
            case Div -> {
                if (curr == 0.0) {
                    errorMode = true;
                    input = "Cannot Divide by Zero";
                    yield 0.0;
                };

                if (lastInput == null)
                    yield curr;

                yield prev / curr;
            }
            case Sqrt -> Math.sqrt(curr);
            case Pow2 -> Math.pow(curr, 2);
            case Mod -> {
                if (lastInput == null) yield curr;

                yield prev % curr;
            }
            case Inverse -> {
                if (curr == 0.0) {
                    errorMode = true;
                    input = "Cannot Divide by Zero";
                    yield 0.0;
                };

                yield 1 / curr;
            }
            case Ln -> {
                // TODO support Infinity
                if (curr <= 0.0) {
                    errorMode = true;
                    input = "Invalid input";
                    yield 0.0;
                };

                yield Math.log(curr);
            }
            case Exp -> Math.exp(curr);
            case Sin -> Math.sin(toRad(curr));
            case Cos -> Math.cos(toRad(curr));
            case Tan -> Math.tan(toRad(curr));
        };

        return CalculatorModel.truncateDecimal(result, 5).doubleValue();
    }

    public void getResult() {
        var result = calculate();

        if (errorMode) return;

        input = String.valueOf(result);
        operation = null;
        lastInput = null;
        firstDigit = true;
    }

    public void toggleAngleUnit() {
        angleUnit = switch (angleUnit) {
            case Deg -> AngleUnit.Rad;
            case Rad -> AngleUnit.Grad;
            case Grad -> AngleUnit.Deg;
        };
    }
}
