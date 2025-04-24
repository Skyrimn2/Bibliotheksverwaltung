package application;

public class QuitAppAction implements MenuAction {

	private String description = "Quit app";

	public QuitAppAction() {
		super();
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public void executeAction() {
		System.exit(0);
	}

}
