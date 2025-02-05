package application;

import java.util.List;
import domain.Buch;

public class BucherAnzeigen {
    private BuchDBHandler buchDBHandler;
    private FrontendHandler frontendHandler;

    public BucherAnzeigen(BuchDBHandler buchDBHandler, FrontendHandler frontendHandler) {
        this.buchDBHandler = buchDBHandler;
        this.frontendHandler = frontendHandler;
    }

    public void zeigeBuch(int buchId) {
        Buch buch = buchDBHandler.ladeBuch(buchId);
        frontendHandler.zeigeBuch(buch);
    }

    public void zeigealleBuecher() {
        for (Buch buch : buchDBHandler.ladeAlleBuecher()) {
            frontendHandler.zeigeBuch(buch);
        }
    }

    public void zeigeVerfuegbareBuecher() {
        List<Buch> verfuegbareBuecher = buchDBHandler.ladeVerfuegbareBuecher();
        for (Buch buch : verfuegbareBuecher) {
            frontendHandler.zeigeBuch(buch);
        }
    }



}
