package domain;

import java.time.LocalDate;

public class Mitgliedschaft {
    private Benutzer benutzer;
    private LocalDate startDatum;
    private LocalDate endDatum;

    public Mitgliedschaft(LocalDate startDatum, LocalDate endDatum) {
        this.startDatum = startDatum;
        this.endDatum = endDatum;
    }
    

    public LocalDate getstartDatum() {
        return this.startDatum;
    }

    public LocalDate getEndDatum() {
        return this.endDatum;
    }

    public void setstartDatum(LocalDate startDatum) {
        this.startDatum = startDatum;
    }

    public void setEndDatum(LocalDate endDatum) {
        this.endDatum = endDatum;
    }

}