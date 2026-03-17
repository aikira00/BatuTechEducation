public class RegistroPresenzeMain {

    public static void main(String[] args) {
        RegistroPresenze registro = new RegistroPresenze();
        try{
            RegistroPresenzeThread[] threads = new RegistroPresenzeThread[10];
            for (int i = 0; i < 10; i++) {
                threads[i] = new RegistroPresenzeThread(registro, "Studente" + i);
                threads[i].start();
            }
            for (Thread t : threads) t.join();
            System.out.println("Presenze registrate: " + registro.getNumeroPresenze());
        }
        catch(Exception e){
        }
    }
}
