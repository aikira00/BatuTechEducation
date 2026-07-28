package es3.bellucci.math.server;

public class ReaderProtocolManager {
    private App app;
    private MyReader reader;

    public ReaderProtocolManager(App app, MyReader reader) {
        this.app = app;
        this.reader = reader;
    }

    public void start(){
        String inline = reader.readLine();
        //qui un while che continua a leggere fino a QUIT
        //leggo ADD 123 10
        //qui uso app con switch case ADD SUB MUL

    }
}
