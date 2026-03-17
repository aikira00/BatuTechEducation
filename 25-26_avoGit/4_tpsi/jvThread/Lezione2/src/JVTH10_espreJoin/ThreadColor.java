package JVTH10_espreJoin;

public class ThreadColor {
    protected double result;
    protected int a;
    protected int b;
    protected int c;

    public ThreadColor(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public double getResult(){
        return result;
    }

    public  void setResult(double result) {
        this.result= result;
    }
}
