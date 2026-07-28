package es3.bellucci.math.server;

public class SenderProtocoloManager {
    private MySender sender;

    public SenderProtocoloManager(MySender sender) {
        this.sender = sender;
    }

    public void send(int n1, int n2, int op, int result){
        //qui costruisco stringa da inviare al client
        // ADD 123 10 133
    }
}
