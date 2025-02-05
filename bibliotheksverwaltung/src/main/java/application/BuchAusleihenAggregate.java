package application;

import java.util.List;
import domain.Ausleihe;
import domain.Buch;

public class BuchAusleihenAggregate {
    private Buch buch;
    private List<Ausleihe> ausleihen;

    public BuchAusleihenAggregate(Buch buch, List<Ausleihe> ausleihen) {
        this.buch = buch;
        this.ausleihen = ausleihen;
    }

    public Buch getBuch() {
        return buch;
    }

    public void setBuch(Buch buch) {
        this.buch = buch;
    }

    public List<Ausleihe> getAusleihen() {
        return ausleihen;
    }

    public void setAusleihen(List<Ausleihe> ausleihen) {
        this.ausleihen = ausleihen;
    }

}