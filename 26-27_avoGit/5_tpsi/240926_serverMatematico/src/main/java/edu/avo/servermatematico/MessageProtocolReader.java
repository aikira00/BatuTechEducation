package edu.avo.servermatematico;

import edu.avo.tcpiolibrary.MessageConsumer;

public class MessageProtocolReader implements MessageConsumer{
    private CommandExecutor executor;
    private String forClose;

    public MessageProtocolReader(CommandExecutor e, String forClose){
        this.executor = e;
        this.forClose = forClose;
    }

    public void consumeMessage(String message){
        String[] array = message.split(" ");
        if(array == null || array.length == 0 ){
            executor.error();
        }
        else{
            if (array.length == 1 && forClose.equals(array[0])){
                executor.close();

            }
            else{
                if(array.length == 3) {
                    //ok ho i 3 pezzi
                    int n1 = Integer.parseInt(array[0]);
                    int n2 = Integer.parseInt(array[1]);

                    try {
                        switch (array[2]) {
                            case "ADD" -> {
                                executor.add(n1, n2);
                            }

                            case "SUB" -> {
                                executor.sub(n1, n2);
                            }

                            case "MUL" -> {
                                executor.mul(n1, n2);
                            }
                            default -> {
                                executor.error();
                            }

                        }
                    }
                    catch(NumberFormatException e){
                        executor.error();
                    }

                }else{
                    executor.error();
                }

            }
        }
    }

}
