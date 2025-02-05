package application;

import java.util.List;

import domain.Buch;

public interface BuchRepository {
    List<Buch> alleBuecher();
    Buch buchNachId(int id);
    void buchSpeichern(Buch buch);
    void buchLoeschen(Buch buch);
}