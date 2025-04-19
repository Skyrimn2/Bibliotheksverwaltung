package application;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	
	private List<MenuAction> actions;
	
	public Menu() {
		actions = new ArrayList<MenuAction>();
	}
	
	public void registerAction(MenuAction action) {
		actions.add(action);
	}
	
	public void unregisterAction(MenuAction action) {
		actions.remove(action);
	}
	
	public String getAllDescriptions() {
		String returnString = "";
		int i = 0;
		for (MenuAction action : actions) {
			returnString += String.valueOf(i) + "\t\t" + action.getDescription() + "\n";
		}
		return returnString;
	}
	
	public void executeAction(int actionNum) {
		actions.get(actionNum).executeAction();
	}
	
}
