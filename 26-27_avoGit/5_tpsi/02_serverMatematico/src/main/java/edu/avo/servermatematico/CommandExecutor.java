package edu.avo.servermatematico;

public interface CommandExecutor {
     void add (int n1, int n2);
     void sub (int n1, int n2);
     void mul (int n1, int n2);
     void error();
     void close();
}
