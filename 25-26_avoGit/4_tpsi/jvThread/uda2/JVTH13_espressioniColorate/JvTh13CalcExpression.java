public class JvTh13CalcExpression extends Thread {
    private int a;
    private int b;
    private int c;
    private double res;

    public JvTh13CalcExpression(int a, int b, int c){
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void run(){
        System.out.println(this.getName() + " sto calcolando intera espressione");
        JvTh13Yellow yellow = new JvTh13Yellow(this.getA(),this.getB(),this.getC());
        yellow.setName("Giallo");
        yellow.start();
        JvTh13Green green = new JvTh13Green(this.getA(),this.getB(),this.getC());
        green.setName("Verde");
        green.start();
        try{
            yellow.join();
            green.join();
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " interrotto " + e.getMessage());
        }
        this.setRes(yellow.getRes() + green.getRes());
        System.out.println(this.getName() + " finito di calcolare");
    }

    public double getRes(){
        return res;
    }

    public void setRes(double res){
        this.res = res;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

}
