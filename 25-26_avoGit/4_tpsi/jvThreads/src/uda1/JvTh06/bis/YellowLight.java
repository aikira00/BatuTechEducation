package JvTh06.bis;

public class YellowLight extends CalcExpression {

    public YellowLight(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        System.out.println(this.getName() + " sto calcolando (3a+5)/(b-c)");
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " interrotto " + e.getMessage());
        }
        this.setRes((double) (3 * this.getA() + 5) /(this.getB()-this.getC()));
        System.out.println(this.getName() + " ho calcolato (3a+5)/(b-c) => " + this.getRes());
    }
}
