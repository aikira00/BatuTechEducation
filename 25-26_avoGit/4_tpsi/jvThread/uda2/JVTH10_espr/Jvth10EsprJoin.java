public class Jvth10EsprJoin implements Runnable {
    private int numOne;
    private int numTwo;
    private String operation;
    private float result;
    public Jvth10EsprJoin(int numOne, int numTwo, String operation) {
        this.numOne = numOne;
        this.numTwo = numTwo;
        this.operation = operation;
        this.result = 0;
    }



    public void run(){
        try{
            System.out.println(Thread.currentThread().getName() + " sto calcolando ...");
            Thread.currentThread().sleep(5000);
            switch(operation){
                case "add":
                    result = numOne + numTwo;
                    break;
                case "sub":
                    result = numOne - numTwo;
                    break;
                case "mul":
                    result = numOne * numTwo;
                    break;
                case "div":
                    result = numOne / numTwo; //controllo div zero?
                    break;
            }
        }
        catch(InterruptedException e){
            System.out.println(Thread.currentThread().getName() + " errore: " + e.getMessage());
        }
        catch (ArithmeticException e) {
            System.out.println(Thread.currentThread().getName() + " errore: divisione per zero non consentita.");
            result = 0; // Valore predefinito per evitare crash
        }
    }

    public float getResult(){
        return result;
    }
}
