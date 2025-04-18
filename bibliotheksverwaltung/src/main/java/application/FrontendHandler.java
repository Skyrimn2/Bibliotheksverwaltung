package application;

import domain.Buch;

public interface FrontendHandler {
    public void zeigeBuch(Buch buch);
    public void showMenu();
    public int readMenuOption();
}
