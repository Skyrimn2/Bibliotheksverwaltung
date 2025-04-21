package adapter;

import domain.Displayable;
import domain.Lending;

public class DisplayableLendingsPerUser extends DisplayableLending {

	public DisplayableLendingsPerUser(Lending lending) {
		super(lending);
	}

	@Override
	public String getDisplayText() {
		return super.getDisplayText() + "\n Lend By:\t" + this.lending.getUser().getName();
	}

}
