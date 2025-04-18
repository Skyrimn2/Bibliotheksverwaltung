package application;
import domain.Adresse;

public class AdressReader {
	
	
	public AdressReader() {
		
	}
	
	public Adresse readFromString(String input) {
		
		String[] splitted = input.split("\\s+");
		
		String streetName = splitted[0];
		int houseNumber = Integer.parseInt(splitted[1]);
		String city = splitted[2];
		String postalCode = splitted[3];
		
		
		return new Adresse(streetName, houseNumber, city, postalCode);
	}

}
