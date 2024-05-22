package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FileDAO {

    PreparedStatement psF;
    ResultSet rsF;
    Connection conectionF;
    Conection connect = Conection.getInstance();
    
    public List ToListF() {
        List<File> data = new ArrayList<>();
        try {
            conectionF = connect.connect();
            psF = conectionF.prepareStatement("SELECT * FROM ficha JOIN  aprendiz ON ficha.aprendiz_id = aprendiz.id");
            rsF = psF.executeQuery();
            while (rsF.next()) {
                File fil = new File();
                fil.setId(rsF.getInt(1));
                fil.setOfficeNumber(rsF.getString(2));
                fil.setTokenName(rsF.getString(3));
                fil.setCampus(rsF.getString(4));
                fil.setCity(rsF.getString(5));
                fil.setIdApprentice(rsF.getInt(6));
                data.add(fil);

            }
        } catch (SQLException e) {
        }
        return data;

    }
    
    public int AddF(File fil){
        int r = 0;
        String sql = "INSERT INTO ficha(officeNumber,tokenName,campus,city,aprendiz_id)VALUES(?,?,?,?,?)";
        try {
            conectionF = connect.connect();
            psF = conectionF.prepareStatement(sql);
            
            psF.setString(1, fil.getOfficeNumber());
            psF.setString(2, fil.getTokenName());
            psF.setString(3, fil.getCampus());
            psF.setString(4, fil.getCity());
            psF.setInt(5, fil.getIdApprentice());
            r = psF.executeUpdate();
            
        } catch (SQLException e) {
        }
        return r; 
    }
    
    public int UpdateF(File fil){
        int r = 0;
        String sql = "UPDATE ficha SET officeNumber=? , tokenName=? , campus=? , city=?, aprendiz_id=? WHERE id=?";
        try {
            conectionF = connect.connect();
            psF = conectionF.prepareStatement(sql);
            psF.setString(1, fil.getOfficeNumber());
            psF.setString(2, fil.getTokenName());
            psF.setString(3, fil.getCampus());
            psF.setString(4, fil.getCity());
            psF.setInt(5, fil.getIdApprentice());
            psF.setInt(6, fil.getId());
            
            r = psF.executeUpdate();
            if (r==1) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
        }
        return r; 
    }
    public int DeleteF(int ida){
        int r = 0;
        String sql = "DELETE FROM ficha WHERE id=" + ida;
        try {
            conectionF = connect.connect();
            psF = conectionF.prepareStatement(sql);
            r = psF.executeUpdate();
        } catch (SQLException e) {
        }
        return r; 
    }
}
