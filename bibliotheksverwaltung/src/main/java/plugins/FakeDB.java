package plugins;

import java.util.List;
import java.util.stream.Collectors;

import application.BuchDBHandler;
import domain.Buch;

public class FakeDB implements BuchDBHandler{

    @Override
    public void speichereBuch(domain.Buch buch) {
        System.out.println("Fake Buch gespeichert");
    }

    @Override
    public Buch ladeBuch(int id) {
        System.out.println("Fake Buch geladen");
        return new Buch("Java ist toll", "John Doe", 1);
    }

    @Override
    public void loescheBuch(int id) {
        System.out.println("Fake Buch gelöscht");
    }

    @Override
    public void aktualisiereBuch(Buch buch) {
        System.out.println("Fake Buch aktualisiert");
    }

    @Override
    public List<Buch> ladeAlleBuecher() {
        System.out.println("Fake Alle Bücher geladen");
        return List.of(new Buch("Java ist toll", "John Doe", 1), new Buch("Python ist toll", "John Doe", 2));
    }

    @Override
    public List<Buch> ladeVerfuegbareBuecher() {
        System.out.println("Fake Verfügbare Bücher geladen");
        return ladeAlleBuecher().stream()
            // Fake Logik momentan
            .filter(buch -> buch.getId() % 2 != 0)
            // logik für die verfügbaren Bücher
            .collect(Collectors.toList());
    }
}
