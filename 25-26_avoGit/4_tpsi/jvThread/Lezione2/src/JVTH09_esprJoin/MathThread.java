package JVTH09_esprJoin;

public class MathThread extends Thread{
    private int numberOne;
    private int numberTwo;
    private double result;

    private String operator;

    public MathThread(int numberOne, int numberTwo, String operator){
        this.numberTwo = numberTwo;
        this.numberOne = numberOne;
        this.operator = operator;
        this.result = 0;
    }

    public void run(){
        switch(operator){
            case "+":
                result = this.numberOne + this.numberTwo;
                break;
            case "-":
                result = this.numberOne - this.numberTwo;
                break;
            case "*":
                result = this.numberOne * this.numberTwo;
                break;
            case "/"://qui eventualmente gestione valori ammissibili
                if (numberTwo != 0) {
                    result = (double) numberOne / numberTwo;
                } else {
                    // eccezione divisione per zero
                    throw new ArithmeticException("Divisione per zero");
                }
                break;
            default:
                result = Double.NaN;//per operatori non ammessi etc.
        }
    }

    public double getResult(){//qui potrebbe starci check su result e lancio eccezione
        return result;
    }
}
