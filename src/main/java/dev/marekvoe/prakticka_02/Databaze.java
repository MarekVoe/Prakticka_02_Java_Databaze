package dev.marekvoe.prakticka_02;

import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class Databaze {

    private final String url = "jdbc:mysql://localhost:3306/prakticka_java";
    private final String user = "root";
    private final String password = "";
    private Connection conn = null;

    public Databaze() {
        try {
            this.conn = DriverManager.getConnection(url, user, password);
            System.out.println("Připojení k databázi proběhlo úspešně !");
        } catch (SQLException e) {
            Util.zobrazitAlert("ERROR - Nepovedlo se připojit k databází", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public ResultSet getData(String sql) {
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            return stmt.executeQuery();
        } catch(SQLException e) {
            Util.zobrazitAlert("ERROR - Nepovedlo se získat data z databáze", null, e.getMessage(), Alert.AlertType.ERROR);
        }
        return null;
    }
}
