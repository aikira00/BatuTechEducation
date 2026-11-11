package edu.avo.servermatematico;

public class Application implements CommandExecutor{

    private MessageProtocolWriter writer;

    public Application(MessageProtocolWriter w){
        this.writer = w;
    }
    @Override
    public void add(int n1, int n2) {
        writer.add(n1,n2, n1+n2);
    }

    @Override
    public void sub(int n1, int n2) {
        writer.sub(n1,n2, n1-n2);
    }

    @Override
    public void mul(int n1, int n2) {
        writer.mul(n1,n2, n1*n2);
    }

    @Override
    public void error() {
        writer.error();
    }

    @Override
    public void close() {
        writer.close();
    }
}
