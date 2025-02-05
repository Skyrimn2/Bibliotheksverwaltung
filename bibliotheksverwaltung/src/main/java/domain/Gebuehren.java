package domain;

public class Gebuehren {
    private double basisGebuehr;
    private double gebuehrProTag;

    public Gebuehren(double basisGebuehr, double gebuehrProTag) {
        this.basisGebuehr = basisGebuehr;
        this.gebuehrProTag = gebuehrProTag;
    }
    
    public double getBasisGebuehr() {
        return this.basisGebuehr;
    }

    public double getGebuehrProTag() {
        return this.gebuehrProTag;
    }
    
    public void setBasisGebuehr(double basisGebuehr) {
        this.basisGebuehr = basisGebuehr;
    }

    public void setGebuehrProTag(double gebuehrProTag) {
        this.gebuehrProTag = gebuehrProTag;
    }

}