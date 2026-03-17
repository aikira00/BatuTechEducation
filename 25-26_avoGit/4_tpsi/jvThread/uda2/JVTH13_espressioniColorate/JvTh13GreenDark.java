public class JvTh13GreenDark extends JvTh13CalcExpression{

    public JvTh13GreenDark(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        System.out.println(this.getName() + " sto calcolando c + b + a");
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " interrotto " + e.getMessage());
        }
        this.setRes(this.getA() + this.getB() + this.getC());
        System.out.println(this.getName() + " ho calcolato c + b + a => " + this.getRes());
    }
}
