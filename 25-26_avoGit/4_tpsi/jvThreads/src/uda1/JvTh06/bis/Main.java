package JvTh06.bis;

public class Main {

    public static void main(String[] args) {
        int a = 4;
        int b = 2;
        int c = 1;
        CalcExpression calcExpression = new CalcExpression(a, b, c);
        calcExpression.start();
        try{
            calcExpression.join();
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        System.out.println(calcExpression.getRes());
    }
}
