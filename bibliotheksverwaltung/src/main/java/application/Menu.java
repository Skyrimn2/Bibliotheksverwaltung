package application;

import java.util.ArrayList;
import java.util.List;

public class Menu {

	private List<MenuAction> actions;

	public Menu() {
		actions = new ArrayList<>();
	}

	public void registerAction(MenuAction action) {
		actions.add(action);
	}

	public void unregisterAction(MenuAction action) {
		actions.remove(action);
	}

	public List<String> getAllDescriptions() {
		List<String> returnString = new ArrayList<>();
		for (MenuAction action : actions) {
			returnString.add(action.getDescription());
		}
		return returnString;
	}

	public void executeAction(int actionNum) {
		actions.get(actionNum).executeAction();
	}

}
