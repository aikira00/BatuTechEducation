public class JvTh13Yellow extends JvTh13CalcExpression{


    public JvTh13Yellow(int a, int b, int c){
       super(a, b, c);
    }
    public void run(){
        JvTh13YellowLight yl = new JvTh13YellowLight(this.getA(), this.getB(), this.getC());
        yl.setName("GialloChiaro");
        yl.start();
        JvTh13YellowDark yd = new JvTh13YellowDark(this.getA(), this.getB(), this.getC());
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
