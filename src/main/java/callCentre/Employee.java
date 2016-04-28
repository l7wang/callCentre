package callCentre;

public abstract class Employee {
	
	// Fields
	private Call currentCall = null;
	protected int level;
	
	// Constructor
	public Employee() {
		
	}
	
	public void setCurrentCall(Call call) {
		currentCall = call;
	}
	
	public void setFree() {
		currentCall = null;
	}
	
	public int getLevel() {
		return level;
	}
	
	public boolean isAvaiable() {
		return currentCall == null;
	}
	
	public boolean canReply(Call call) {
		return level >= call.getCallLevel();
	}
}
