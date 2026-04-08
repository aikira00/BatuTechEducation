package edu.avo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class DbServer {

    private PreparedStatement ps;
    private ResultSet rs;
    private Connection connection;

    public DbServer(Connection connection) {
        this.connection = connection;
    }


    public List<Autore> getAutori() throws SQLException {
        String sql = "SELECT * FROM AUTORE";
        List<Autore> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapAutore(rs));
            }
        }
        return list;
    }

    public Autore getAutore(int id) throws SQLException {
        String sql = "SELECT * FROM AUTORE WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapAutore(rs);
                }
            }
        }
        return null; // non trovato
    }

    public int insertAutore(Autore autore) throws SQLException {
        String sql = "INSERT INTO AUTORE (COGNOME, NOME, DATA_NASCITA, DATA_MORTE) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, autore.getCognome());
            ps.setString(2, autore.getNome());
            ps.setObject(3, autore.getDataNascita());  // LocalDate → DATE
            ps.setObject(4, autore.getDataMorte());    // null se ancora vivo
            return ps.executeUpdate();
        }
    }

    public int updateAutore(Autore autore) throws SQLException {
        String sql = "UPDATE AUTORE SET COGNOME=?, NOME=?, DATA_NASCITA=?, DATA_MORTE=? WHERE ID=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, autore.getCognome());
            ps.setString(2, autore.getNome());
            ps.setObject(3, autore.getDataNascita());
            ps.setObject(4, autore.getDataMorte());
            ps.setInt(5, autore.getId());
            return ps.executeUpdate();
        }
    }

    public int deleteAutore(int id) throws SQLException {
        String sql = "DELETE FROM AUTORE WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    // =========================================================
    //  ROMANZO
    // =========================================================

    public List<Romanzo> getRomanzi() throws SQLException {
        // JOIN per caricare l'autore in un'unica query
        String sql = """
                SELECT r.ID, r.ANNO_PUBBLICAZIONE, r.TITOLO,
                       a.ID AS A_ID, a.COGNOME, a.NOME,
                       a.DATA_NASCITA, a.DATA_MORTE
                FROM ROMANZO r
                JOIN AUTORE a ON r.AUTORE = a.ID
                """;
        List<Romanzo> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapRomanzo(rs));
            }
        }
        return list;
    }

    public Romanzo getRomanzo(int id) throws SQLException {
        String sql = """
                SELECT r.ID, r.ANNO_PUBBLICAZIONE, r.TITOLO,
                       a.ID AS A_ID, a.COGNOME, a.NOME,
                       a.DATA_NASCITA, a.DATA_MORTE
                FROM ROMANZO r
                JOIN AUTORE a ON r.AUTORE = a.ID
                WHERE r.ID = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRomanzo(rs);
                }
            }
        }
        return null;
    }

    public int insertRomanzo(Romanzo romanzo) throws SQLException {
        String sql = "INSERT INTO ROMANZO (TITOLO, ANNO_PUBBLICAZIONE, AUTORE) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, romanzo.getTitolo());
            // Year → DATE: MySQL vuole una data, usiamo il 1° gennaio dell'anno
            ps.setObject(2, romanzo.getAnnoPubblicazione().atDay(1));
            ps.setInt(3, romanzo.getAutore().getId());
            return ps.executeUpdate();
        }
    }

    public int updateRomanzo(Romanzo romanzo) throws SQLException {
        String sql = "UPDATE ROMANZO SET TITOLO=?, ANNO_PUBBLICAZIONE=?, AUTORE=? WHERE ID=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, romanzo.getTitolo());
            ps.setObject(2, romanzo.getAnnoPubblicazione().atDay(1));
            ps.setInt(3, romanzo.getAutore().getId());
            ps.setInt(4, romanzo.getId());
            return ps.executeUpdate();
        }
    }

    public int deleteRomanzo(int id) throws SQLException {
        String sql = "DELETE FROM ROMANZO WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate();
        }
    }

    // =========================================================
    //  MAPPING ResultSet → oggetto (evita duplicazione)
    // =========================================================

    private Autore mapAutore(ResultSet rs) throws SQLException {
        return new Autore(rs.getInt("ID"), rs.getString("COGNOME"), rs.getString("NOME"),                           // nullable → null ok
                rs.getObject("DATA_NASCITA", LocalDate.class),  // nullable → null ok
                rs.getObject("DATA_MORTE", LocalDate.class)   // nullable → null ok
        );
    }

    private Romanzo mapRomanzo(ResultSet rs) throws SQLException {
        Autore autore = new Autore(rs.getInt("A_ID"), rs.getString("COGNOME"), rs.getString("NOME"), rs.getObject("DATA_NASCITA", LocalDate.class), rs.getObject("DATA_MORTE", LocalDate.class));
        return new Romanzo(rs.getInt("ID"), rs.getString("TITOLO"), Year.of(rs.getObject("ANNO_PUBBLICAZIONE", LocalDate.class).getYear()), autore);
    }

}

