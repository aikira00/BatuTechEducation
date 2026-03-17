public class RegistroPresenzeThread extends Thread{
    RegistroPresenze registro;

    public RegistroPresenzeThread(RegistroPresenze r, String name){
       this.setName(name);
        this.registro = r;
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            registro.aggiungiPresenza(Thread.currentThread().getName());
        }
    }
}
