package JvTh06.bis;

public class GreenLight extends CalcExpression {

    public GreenLight(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        System.out.println(this.getName() + " sto calcolando (7-a/b+22)");
        try{
            Thread.sleep(3000);
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " interrotto " + e.getMessage());
        }
        this.setRes(7-this.getA()/this.getB()+22);
        System.out.println(this.getName() + " ho calcolato (7-a/b+22) => " + this.getRes());
    }
}
