package domain;

import java.time.LocalDate;

public class Mitgliedschaft {
    private LocalDate beginnDatum;
    private LocalDate endDatum;

    public Mitgliedschaft(LocalDate beginnDatum, LocalDate endDatum) {
        this.beginnDatum = beginnDatum;
        this.endDatum = endDatum;
    }
    

    public LocalDate getBeginnDatum() {
        return this.beginnDatum;
    }

    public LocalDate getEndDatum() {
        return this.endDatum;
    }

    public void setBeginnDatum(LocalDate beginnDatum) {
        this.beginnDatum = beginnDatum;
    }

    public void setEndDatum(LocalDate endDatum) {
        this.endDatum = endDatum;
    }

}