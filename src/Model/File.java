package Model;

public class File {
    
    int id;
    int idApprentice;
    String officeNumber;
    String tokenName;
    String campus;
    String city;

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdApprentice() {
        return idApprentice;
    }

    public void setIdApprentice(int idApprentice) {
        this.idApprentice = idApprentice;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
}
