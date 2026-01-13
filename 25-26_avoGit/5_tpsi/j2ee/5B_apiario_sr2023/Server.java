/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.esame;

import jakarta.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author palma
 */
public class Server {

    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    public Server(Connection connection) {
        this.connection = connection;
    }

    public boolean existsMiele(Miele miele) {
        try {
            boolean result = false;
            statement = connection.prepareStatement("Select * from mieli where tipologia=? and denominazione=?");
            statement.setInt(1, miele.getTipologia());
            statement.setString(2, miele.getDenominazione());
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = true;
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public int insertMiele(Miele miele) {
        try {

            statement = connection.prepareStatement("insert into mieli(denominazione,tipologia) values (?,?)");
            statement.setString(1, miele.getDenominazione());
            statement.setInt(2, miele.getTipologia());
            int num = statement.executeUpdate();
            return num;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<TipoMiele> selectTipiMiele() {
        try {
            List<TipoMiele> list = new ArrayList<>();
            statement = connection.prepareStatement("Select * from tipimiele");
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new TipoMiele(resultSet.getInt(1), resultSet.getString(2)));
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<Apiario> selectApiariByProvincia(String provincia) {
        try {
            List<Apiario> list = new ArrayList<>();
            statement = connection.prepareStatement("Select * from apiari, province where idprovincia=province.id and nome=?");
            statement.setString(1, provincia);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(new Apiario(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5)));
            }
            return list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
