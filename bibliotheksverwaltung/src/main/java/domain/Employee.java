package domain;

public class Employee {
    private String name;
    private String mitarbeiterID;

    public Employee(String name, String mitarbeiterID) {
        this.name = name;
        this.mitarbeiterID = mitarbeiterID;
    }
    
    public String getName() {
        return this.name;
    }

    public String getMitarbeiterID() {
        return this.mitarbeiterID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMitarbeiterID(String mitarbeiterID) {
        this.mitarbeiterID = mitarbeiterID;
    }

}