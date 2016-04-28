package callCentre;

public class Call {
	
	// Fields
	private String id;
	private int callLevel;
	//private int duration;
	private Employee handler;
	
	// Constructor
	public Call(String id, int callLevel) {
		this.id = id;
		this.callLevel = callLevel;
		System.out.println("New call " + id + " is created.");
	}
	
	// Getter
	// Get the call level
	public String getId() {
		return id;
	}
	
	// Get the call level
	public int getCallLevel() {
		return callLevel;
	}
	
	// Get the handler of the call
	public Employee getHandler() {
		return handler;
	}
	
	// Setter
	// Set the call level into a new one
	public void setCallLevel(int newCallLevel) {
		callLevel = newCallLevel;
	}
	
	// Set the handler of the call
	public void setHandler(Employee e) {
		handler = e;
	}
	
	// Methods
	// Reset the handler of the call
	public void resetHandler() {
		handler = null;
	}
	
	// If call successfully start, return 0, else return the latest handler level
	public int callStart() {
		handler.setCurrentCall(this);
		int latestHandlerLevel = handler.getLevel();
		if (handler.canReply(this)) {
			System.out.println("Call " + getId() + " start:");
			return 0;
		}
		else {
			return latestHandlerLevel;
		}
	}
	
	// End the call, return the free Employee
	public Employee callEnd() {
		handler.setFree();
		Employee freeEmployee = handler;
		resetHandler();
		System.out.println("Call " + getId() + " end.");
		return freeEmployee;
		
	}
}
