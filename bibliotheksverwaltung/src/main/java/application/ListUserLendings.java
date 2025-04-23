package application;


import java.util.ArrayList;
import java.util.List;

import adapter.DisplayableLending;
import domain.Displayable;
import domain.Lending;
import domain.UserInterface;

public class ListUserLendings implements MenuAction {

	private String description = "List all your lendings";
	private DBHandler<Lending> lendingDB;
	private FrontendHandler frontend;

	public ListUserLendings(DBHandler<Lending> lendingDB, FrontendHandler frontend) {
	    this.lendingDB = lendingDB;
	    this.frontend = frontend;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void executeAction() {
		UserInterface user = frontend.getUser();
		List<Lending> lends = lendingDB.getItemsByString("userID", String.valueOf(user.getID()));
		if (lends == null) {
			System.out.println("No fitting lendings found.");
		}
		List <Displayable> disps = new ArrayList<>();
		for(Lending l : lends) {
			disps.add(new DisplayableLending(l));
		}
		frontend.showResultList(disps);
	}

}
