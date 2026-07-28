package edu.avo.serverAritmetico;

public class App implements ICommandConsumer{

    private SenderProtocolManager spm;

    public App(SenderProtocolManager spm) {
        this.spm = spm;
    }

    @Override
    public void add(int n1, int n2) {
        spm.sendAddResult( n1, n2, n1+n2);
    }

    @Override
    public void sub(int n1, int n2) {
        spm.sendSubResult(n1, n2, n1-n2);
    }

    @Override
    public void mul(int n1, int n2) {
        spm.sendMulResult( n1, n2, n1*n2);
    }

    @Override
    public void div(int n1, int n2) {
        spm.sendDivResult( n1, n2, n1/n2);
    }

    @Override
    public void close() {
        spm.close();
    }


}