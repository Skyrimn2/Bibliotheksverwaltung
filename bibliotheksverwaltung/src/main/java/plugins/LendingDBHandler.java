package plugins;

import domain.Lending;

public interface LendingDBHandler {
    public void saveLending(Lending lending);
    public Lending loadLending(int lendingID);
    public void deleteLending(int lendingID);
    public void updateLending(Lending lending);
}
