package JvTh06.bis;

public class Yellow extends CalcExpression {


    public Yellow(int a, int b, int c){
       super(a, b, c);
    }
    public void run(){
        YellowLight yl = new YellowLight(this.getA(), this.getB(), this.getC());
        yl.setName("GialloChiaro");
        yl.start();
        YellowDark yd = new YellowDark(this.getA(), this.getB(), this.getC());
        yd.setName("GialloScuro");
        yd.start();
        try {
            yl.join();
            yd.join();
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " interrotto " + e.getMessage());
        }
        System.out.println(this.getName() + " sto calcolando giallo chiaro * giallo scuro");
        this.setRes(yl.getRes()*yd.getRes());
        System.out.println(this.getName() + " ho calcolato (3a+5)/(b-c)*(3a+5c) => " + this.getRes());
    }
}
