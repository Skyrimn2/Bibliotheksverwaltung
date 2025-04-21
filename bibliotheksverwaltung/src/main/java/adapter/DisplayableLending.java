package adapter;

import domain.Displayable;
import domain.Lending;

public class DisplayableLending implements Displayable {

	protected Lending lending;
	
	public DisplayableLending(Lending lending) {
		super();
		this.lending = lending;
	}

	@Override
	public String getDisplayText() {
		String returned = lending.getReturnDate() != null ? "yes" : "no";
		return " lending ID:\t" + lending.getID()
			+ "\n Book:\t\t" + lending.getBookCopy().getBook().getTitle() 
			+ "\n lendingDate:\t" + lending.getLendingDate().toString()
			+ "\n returend:\t" + returned;
	}

}
