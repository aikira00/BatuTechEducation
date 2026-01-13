package edu.avo.demo;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author palma
 */
public class ConnectionUtility {
    /**
     * Utilizza un delle connessioni che vengono create in automatico da Tomcat
     * Per creare la connessione nel file context.xml, presente nella cartella conf
     * della directory di Tomcat, è stato inserito un tag con le caratteristiche
     * della connessione
     *
     * @return Una connessione  a un db
     *
     * @throws NamingException
     * @throws SQLException
     */
    public static Connection getConnection() throws NamingException, SQLException {
        Context initContext = new InitialContext();
        DataSource ds = (DataSource) initContext.lookup("java:/comp/env/sr2023_eserciziows");
        return ds.getConnection();
    }
}