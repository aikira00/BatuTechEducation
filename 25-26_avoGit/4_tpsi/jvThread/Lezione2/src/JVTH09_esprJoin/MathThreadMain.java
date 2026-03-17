package JVTH09_esprJoin;

public class MathThreadMain {

    public static void main(String [] args){
        MathThread ta = new MathThread(3, 2, "+");
        MathThread tb = new MathThread(7, 4, "-");
        MathThread tc1 = new MathThread(2, 3, "*");
        MathThread tc2 = new MathThread(5, 1, "-");

        //concorrenza => fork genero i flussi paralleli
        ta.start();
        tb.start();
        tc1.start();
        tc2.start();

       try{
           //concorrenza => join riunisco i flussi
           ta.join();
           tb.join();
           tc1.join();
           tc2.join();
       }
       catch(InterruptedException e){

           System.out.println("Interrupted exception occurred, " + e.getMessage());
       }
       double a = ta.getResult();
       double b = tb.getResult();
       double c1 = tc1.getResult();
       double c2 = tc2.getResult();
       if(Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c1) || Double.isNaN(c2))
           System.out.println("Errore uno o più operandi non sono validi");
       else{
           double a1 = 2 * a;
           double c = c1 * c2;
           double d = a1 + b + c;
           System.out.println("Il risultato è " + d);
       }

    }
}
