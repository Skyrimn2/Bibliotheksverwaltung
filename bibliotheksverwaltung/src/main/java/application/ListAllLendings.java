package application;

import java.util.ArrayList;
import java.util.List;

import adapter.DisplayableLendingsPerUser;
import domain.Displayable;
import domain.Lending;

public class ListAllLendings implements MenuAction {



	private String description = "List all lendings";
	private DBHandler<Lending> lendingDB;
	private FrontendHandler frontend;
	private IDisplayableFactory displayableFactory;

	public ListAllLendings(DBHandler<Lending> lendingDB, FrontendHandler frontend, IDisplayableFactory displayableFactory) {
	    this.lendingDB = lendingDB;
	    this.frontend = frontend;
	    this.displayableFactory = displayableFactory;
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
			disps.add(displayableFactory.createDisplayableLendingsPerUser(l));
		}
		frontend.showResultList(disps);
	}

}
