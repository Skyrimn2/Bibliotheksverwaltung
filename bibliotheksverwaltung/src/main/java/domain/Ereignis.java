package domain;

import java.time.LocalDateTime;

public class Ereignis {
    private String name;
    private LocalDateTime date;
    private String location;
    private LibraryLocation LibraryLocation;
    private String description;
    
    public Ereignis(String name, LocalDateTime datum, String ort, String beschreibung, LibraryLocation bibliotheksstandort) {
        this.setName(name);
        this.setDate(datum);
        this.setLocation(ort);
        this.setDescription(beschreibung);
        this.setLibraryLocation(bibliotheksstandort);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LibraryLocation getLibraryLocation() {
		return LibraryLocation;
	}

	public void setLibraryLocation(LibraryLocation libraryLocation) {
		LibraryLocation = libraryLocation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    


}