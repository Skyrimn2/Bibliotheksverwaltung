package application;
import domain.Adress;

public class AdressReader {
	
	
	public AdressReader() {
		
	}
	
	public Adress readFromString(String input) {
		
		String[] splitted = input.split("\\s+");
		
		String streetName = splitted[0];
		int houseNumber = Integer.parseInt(splitted[1]);
		String city = splitted[2];
		String postalCode = splitted[3];
		
		
		return new Adress(streetName, houseNumber, city, postalCode);
	}

}
