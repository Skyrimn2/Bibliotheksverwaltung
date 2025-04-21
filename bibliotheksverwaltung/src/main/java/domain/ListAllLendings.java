package domain;

import java.util.ArrayList;
import java.util.List;

import adapter.DisplayableLendingsPerUser;
import application.DBHandler;
import application.FrontendHandler;
import application.MenuAction;

public class ListAllLendings implements MenuAction {
	
	

	private String description = "List all lendings";
	private DBHandler<Lending> lendingDB;
	private FrontendHandler frontend;
	
	public ListAllLendings(DBHandler<Lending> lendingDB, FrontendHandler frontend) {
	    this.lendingDB = lendingDB;
	    this.frontend = frontend;
	}


	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void executeAction() {
		List<Lending> lends = lendingDB.loadAllOfItem();
		List<Displayable> disps = new ArrayList<>();
		for (Lending l : lends) {
			disps.add(new DisplayableLendingsPerUser(l));
		}
		frontend.showResultList(disps);
	}

}
