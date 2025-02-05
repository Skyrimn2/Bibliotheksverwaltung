package application;

import java.util.List;
import domain.Buch;

public interface BuchDBHandler {
    public void speichereBuch(Buch buch);
    public Buch ladeBuch(int id);
    public void loescheBuch(int id);
    public void aktualisiereBuch(Buch buch);
    public List<Buch> ladeAlleBuecher();
    public List<Buch> ladeVerfuegbareBuecher();
}
