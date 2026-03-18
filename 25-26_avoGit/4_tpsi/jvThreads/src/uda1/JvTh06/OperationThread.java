package JvTh06;

public class OperationThread implements Runnable {

    private int a;
    private int b;
    private char operator;
    private double result;
    private boolean error;

    public OperationThread(int a, int b, char operator) {
        this.a = a;
        this.b = b;
        this.operator = operator;
        this.error = false;
    }

    @Override
    public void run() {
        switch (operator) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    error = true;
                    System.out.println("[Division] Error: division by zero!");
                } else {
                    result = (double) a / b;
                }
                break;
            default:
                error = true;
                System.out.println("[Error] Unknown operator: " + operator);
        }
    }

    public double getResult() {
        return result;
    }

    public boolean hasError() {
        return error;
    }

    public char getOperator() {
        return operator;
    }

    public String getLabel() {
        switch (operator) {
            case '+': return "Addition";
            case '-': return "Subtraction";
            case '*': return "Multiplication";
            case '/': return "Division";
            default:  return "Unknown";
        }
    }

    @Override
    public String toString() {
        if (error) {
            return "[" + getLabel() + "] " + a + " " + operator + " " + b + " = ERROR";
        }
        return "[" + getLabel() + "] " + a + " " + operator + " " + b + " = " + result;
    }
}