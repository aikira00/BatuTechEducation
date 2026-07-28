package edu.avo.serverAritmetico;

public interface ICommandConsumer {
    void add(int n1, int n2);
    void sub(int n1, int n2);
    void mul(int n1, int n2);
    void div(int n1, int n2);
    void close();
}