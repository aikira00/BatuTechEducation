package edu.avo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class DbManager {

    private Map<String, String> dbUsers;
    private String username;

    //per operazioni su file
    private PrintWriter out;
    private BufferedReader in;

    public DbManager(Map<String, String> dbUsers) {
        this.dbUsers = dbUsers;
    }

    public void setUsername(String username) {
        this.username = username;
        File file = new File("db/"+username+".txt");
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException ex){
                throw new RuntimeException();
            }
        }
    }

    public boolean loginOk(String username, String password){
        return dbUsers.containsKey(username) && dbUsers.get(username).equals(password);
    }

    public void saveText(String text){
        try{
            //apro file in modalità append con il flag true
            out = new PrintWriter(new FileWriter("db/"+username+".txt"), true);
            out.println(text);
            out.close();
        }
        catch(IOException ex){
            throw new RuntimeException();
        }
    }

    public List<String> getList(){
        List<String> msgs = new ArrayList<>();
        try{
            in = new BufferedReader(new FileReader("db/"+username+".txt"));
            String line;
           while((line=in.readLine())!=null){
               msgs.add(line);
           }
        }
        catch(IOException ex){
            throw new RuntimeException();
        }
        return msgs;
    }

}
