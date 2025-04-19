package adapter;



import application.FrontendHandler;


public abstract class Frontend implements FrontendHandler {

	protected int userLevel = 0;
	
	public Frontend() {
		super();
	}

}
