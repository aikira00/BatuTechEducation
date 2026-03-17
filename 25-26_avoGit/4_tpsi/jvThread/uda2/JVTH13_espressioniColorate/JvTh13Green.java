public class JvTh13Green extends JvTh13CalcExpression{

    public JvTh13Green(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        JvTh13GreenLight gl = new JvTh13GreenLight(this.getA(), this.getB(), this.getC());
        gl.setName("VerdeChiaro");
        gl.start();
        JvTh13GreenDark gd = new JvTh13GreenDark(this.getA(), this.getB(), this.getC());
        gd.setName("VerdeScuro");
        gd.start();
        try {
            gl.join();
            gd.join();
        }
        catch(InterruptedException e){
            System.out.println(this.getName() + " interrotto " + e.getMessage());
        }
        this.setRes(gl.getRes()/gd.getRes());
        System.out.println(this.getName() + " ho calcolato (7-a/b+22)/(c+b+a) => " + this.getRes());
    }
}
