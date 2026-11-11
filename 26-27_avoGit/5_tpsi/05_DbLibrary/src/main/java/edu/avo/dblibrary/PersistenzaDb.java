/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.dblibrary;

import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author palma
 */
public class PersistenzaDb implements Persistenza {

    private ResultSet rs;
    private PreparedStatement ps;
    private Connection conn;

    public PersistenzaDb(String connectionString) {
        try {
            conn = DriverManager.getConnection(connectionString);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<Movimento> estrattoConto(String codiceConto, int numeroMovimenti) {
        List<Movimento> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("select Versamento,Importo,Data from Movimenti where CodiceConto=? order by Progressivo desc limit ?");
            ps.setString(1, codiceConto);
            ps.setInt(2, numeroMovimenti);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Movimento(rs.getBoolean(1), rs.getDouble(2), LocalDate.parse(rs.getString(3))));
            }
            return list;
        } catch (SQLException ex) {
            return list;
        }
    }

    @Override
    public List<Movimento> ricercaMovimenti(String codiceConto, String dataPrecedente, String dataSuccessiva) {
        List<Movimento> list = new ArrayList<>();
        try {
            ps = conn.prepareStatement("select Versamento,Importo,Data from Movimenti where CodiceConto=? and Data between ? and ?");
            ps.setString(1, codiceConto);
            ps.setString(2, dataPrecedente);
            ps.setString(3, dataSuccessiva);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Movimento(rs.getBoolean(1), rs.getDouble(2), LocalDate.parse(rs.getString(3))));
            }
            return list;
        } catch (SQLException ex) {
            return list;
        }
    }

    @Override
    public double saldo(String codiceConto) {
        try {
            ps = conn.prepareStatement("select Saldo from ContiCorrenti where Codice=?");
            ps.setString(1, codiceConto);
            rs = ps.executeQuery();
            rs.next();
            return rs.getDouble(1);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean prelievo(String codiceConto, double importo) {
        boolean ok = false;
        double saldo = saldo(codiceConto);
        if (saldo >= importo) {
            inserimento(codiceConto,-importo);
            aggiornamentoSaldo(codiceConto,-importo);
            ok = true;
        }
        return ok;
    }

    @Override
    public void versamento(String codiceConto, double importo) {
        inserimento(codiceConto,importo);
        aggiornamentoSaldo(codiceConto,importo);

    }

    @Override
    public String login(String username, String password) {
        try {
            String codiceCliente = "";
            ps = conn.prepareStatement("select CodiceConto from Utenti  where Username=? and Pass=?");
            ps.setString(1,username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                codiceCliente = rs.getString(1);
            }

            return codiceCliente;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean bonifico(String codiceConto, double importo, String codiceContoCorrente) {
        boolean ok = false;
        if (esisteConto(codiceContoCorrente)) {
            if (!codiceConto.equals(codiceContoCorrente)) {
                ok = prelievo(codiceConto, importo);
                if (ok) {
                    versamento(codiceContoCorrente,importo);
                }
            }
        }
        return ok;
    }

    private void inserimento(String codiceConto, double importo) {
        try {
            ps = conn.prepareStatement("insert into Movimenti (Versamento,Importo,Data,CodiceConto) values (?,?,?,?)");
            ps.setBoolean(1, importo > 0);
            ps.setDouble(2, Math.abs(importo));
            ps.setString(3, LocalDate.now().toString());
            ps.setString(4, codiceConto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    private boolean aggiornamentoSaldo(String codiceConto, double importo) {
        try {
            ps = conn.prepareStatement("update ContiCorrenti set Saldo=Saldo+? where Codice=?");
            ps.setDouble(1, importo);
            ps.setString(2, codiceConto);
            return ps.executeUpdate() == 1;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() {
        try {
            if (rs!=null && !rs.isClosed()) {
                rs.close();
            }
            if (ps!=null && !ps.isClosed()) {
                ps.close();
            }
            conn.close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public boolean esisteConto(String codiceConto) {
        try {
            ps = conn.prepareStatement("select Codice from ContiCorrenti where Codice=?");
            ps.setString(1, codiceConto);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
