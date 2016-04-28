package callCentre;

public class Manager extends Employee {

	private static Manager _instance;
	
	// Constructor
	private Manager() {
		level = 3;
	}
	
	public synchronized static Manager getInstance() {
		if (_instance == null) {
			_instance = new Manager();
		}
		return _instance;
	}
}
