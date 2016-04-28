package callCentre;

// This is the employee factory used to create employees with different types
public class EmployeeFactory {
	
	public Employee getEmployee(String employeeType) {
		if (employeeType == null) {
			return null;
		}
		else if (employeeType.equalsIgnoreCase("TELEPHONIST")) {
			return new Telephonist();
		}
		else if (employeeType.equalsIgnoreCase("SUPERVISOR")) {
			return Supervisor.getInstance();
		}
		else if (employeeType.equalsIgnoreCase("MANAGER")) {
			return Manager.getInstance();
		}
		return null;
	}
}
