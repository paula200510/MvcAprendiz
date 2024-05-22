
package Model;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conection {
    private Conection(){
        
    }
    
    private static Connection connect;
    
    private static Conection instancia;
    
    public static final String URL = "jdbc:mysql://localhost:3306/mvcaprendiz";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
    
    public Connection connect() {
        try {
            connect = DriverManager.getConnection(URL,USERNAME,PASSWORD);
            return (Connection) connect;
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Error: " + e);
        }
        return connect;
    }
    
    public void closeConnection() throws SQLException{
        try {
            connect.close();
        } catch (SQLException e) {
            JOptionPane.showInternalMessageDialog(null, "Error: " + e);
            connect.close();
        }
    }
    
    //IMPLEMENTACION PATRON SINGLENTON
    
    public static Conection getInstance(){
        if (instancia == null) {
            instancia = new Conection();
        }
        return instancia;
    }
    
}
