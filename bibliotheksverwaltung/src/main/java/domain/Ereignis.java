package domain;

import java.time.LocalDateTime;

public class Ereignis {
    private String name;
    private LocalDateTime datum;
    private String ort;
    private LibraryLocation bibliotheksstandort;
    private String beschreibung;
    
    public Ereignis(String name, LocalDateTime datum, String ort, String beschreibung, LibraryLocation bibliotheksstandort) {
        this.name = name;
        this.datum = datum;
        this.ort = ort;
        this.beschreibung = beschreibung;
        this.bibliotheksstandort = bibliotheksstandort;
    }
    
    public String getName() {
        return this.name;
    }

    public LocalDateTime getdatum() {
        return this.datum;
    }

    public String getort() {
        return this.ort;
    }

    public String getBeschreibung() {
        return this.beschreibung;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setdatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public void setort(String ort) {
        this.ort = ort;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public LibraryLocation getBibliotheksstandort() {
        return this.bibliotheksstandort;
    }

    public void setBibliotheksstandort(LibraryLocation bibliotheksstandort) {
        this.bibliotheksstandort = bibliotheksstandort;
    }

}