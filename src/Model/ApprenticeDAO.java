
package Model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ApprenticeDAO {
    
    PreparedStatement ps;
    ResultSet rs;
    Connection conection;
    Conection connect = Conection.getInstance();
   
    public List ToList(){
        List<Apprentice> data = new ArrayList<>();
        try {
            conection = connect.connect();
            ps = conection.prepareStatement("SELECT * FROM aprendiz");
            rs = ps.executeQuery();
            while (rs.next()) {
               Apprentice ap = new Apprentice();
               ap.setId(rs.getInt(1));
               ap.setDoctype(rs.getString(2));
               ap.setDocnumber(rs.getString(3));
               ap.setName(rs.getString(4));
               ap.setBirthdate(rs.getDate(5));
               ap.setGender(rs.getString(6));
               ap.setCity(rs.getString(7));
               data.add(ap);
               
               
            }
        } catch (SQLException e) {
        }
        return data;
    }
    
    public int Add(Apprentice appre){
        int r = 0;
        String sql = "INSERT INTO aprendiz(doctype,docnumber,name,birthdate,gender,city)VALUES (?,?,?,?,?,?)";
        try {
            conection = connect.connect();
            ps = conection.prepareStatement(sql);
            ps.setString(1, getDocTypeIndex(appre.getDoctype()));
            ps.setString(2, appre.getDocnumber());
            ps.setString(3, appre.getName());
            java.util.Date date = appre.getBirthdate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            ps.setString(4, format);
            ps.setString(5, appre.getGender());
            ps.setString(6, appre.getCity());
            r = ps.executeUpdate();
            
            
        } catch (SQLException e) {
        }
        return r;
    }
    
    public int Update(Apprentice appre){
        int r = 0;
        String sql = "UPDATE aprendiz SET doctype=? , docnumber=?, name=?, birthdate=?, gender=?, city=? WHERE id=?";
        try {
            conection = connect.connect();
            ps = conection.prepareStatement(sql);
            ps.setString(1, getDocTypeIndex(appre.getDoctype()));
            ps.setString(2, appre.getDocnumber());
            ps.setString(3, appre.getName());
            java.util.Date date = appre.getBirthdate();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(date);
            ps.setString(4, format);
            ps.setString(5, appre.getGender());
            ps.setString(6, appre.getCity());
            ps.setInt(7, appre.getId());
            
            r = ps.executeUpdate();
            if (r==1) {
                return 1;
            } else {
                return 0;
            }
            
        } catch (SQLException e) {
        }
        return r;
    }
    
    public int Delete(int ida){
        int r = 0;
        String sql = "DELETE FROM aprendiz WHERE id=" + ida;
        try {
            conection = connect.connect();
            ps = conection.prepareStatement(sql);
            r = ps.executeUpdate();
            
        } catch (SQLException e) {
        }
        return r;
    }
    
    private String getDocTypeIndex(String doctype){
        return switch (doctype){
            case "Citizenship Card" -> "CC";
            case "Foreigner ID" -> "FI";
            case "Identity Card" -> "IC";
            case "Civil Registration" -> "CR";
            case "Passport" -> "PS";
            default ->"";
        };
        
    }
    
}