package adapter;



import application.FrontendHandler;
import domain.UserInterface;


public abstract class Frontend implements FrontendHandler {

	protected UserInterface user;

	public Frontend() {
		super();
	}

	@Override
	public UserInterface getUser() {
		return this.user;
	}

}
