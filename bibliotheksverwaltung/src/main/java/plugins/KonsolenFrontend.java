package plugins;

import application.FrontendHandler;

public class KonsolenFrontend implements FrontendHandler {
    @Override
    public void zeigeBuch(domain.Buch buch) {
        System.out.println("Buchtitel: " + buch.getTitel());
        System.out.println("Buchautor: " + buch.getAutor());
        System.out.println("Verfügbar: " + (buch.istVerfuegbar() ? "Ja" : "Nein"));
    }
}
