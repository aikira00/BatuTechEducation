package JvTh06.bis;

public class Green extends CalcExpression {

    public Green(int a, int b, int c){
        super(a, b, c);
    }
    public void run(){
        GreenLight gl = new GreenLight(this.getA(), this.getB(), this.getC());
        gl.setName("VerdeChiaro");
        gl.start();
        GreenDark gd = new GreenDark(this.getA(), this.getB(), this.getC());
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
