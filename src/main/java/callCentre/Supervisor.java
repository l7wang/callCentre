package callCentre;

public class Supervisor extends Employee {
	
	private static Supervisor _instance;
	
	// Constructor
	private Supervisor() {
		level = 2;
	}
	
	public synchronized static Supervisor getInstance() {
		if (_instance == null) {
			_instance = new Supervisor();
		}
		return _instance;
	}
}
