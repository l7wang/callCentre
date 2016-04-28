package callCentre;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CallHandler {
	
	EmployeeFactory ef = new EmployeeFactory();
	
	// Employees
	private List<Employee> telephonists;
	private Employee supervisor;
	private Employee manager;
	
	// Waiting lists for supervisor and manager
	private Queue<Call> callQueue;
	
	// Constructor
	public CallHandler() {
		telephonists = new ArrayList<Employee>();
		supervisor = ef.getEmployee("SUPERVISOR");
		manager = ef.getEmployee("MANAGER");	
		callQueue = new LinkedList<Call>();
	}
	
	// Getter
	public List<Employee> getTelephonists() {
		return telephonists;
	}
	
	public Employee getSupervisor() {
		return supervisor;
	}
	
	public Employee getManager() {
		return manager;
	}
	
	public Queue<Call> getCallQueues() {
		return callQueue;
	}
	
	// Methods
	// Find an available employee with the passed in level to reply, if not return null
	public Employee getHandler(Call call, int level) {
		switch (level) {
			case 1:
				for (Employee e: telephonists) {
					if (e.isAvaiable()) {
						System.out.println("Found an available employee to pick up " + call.getId());
						return e;
					}
				}
				System.out.println("All the employees are busy, get a new employee to take " + call.getId());
				Employee newTelephonist = ef.getEmployee("TELEPHONIST");
				telephonists.add(newTelephonist);
				return newTelephonist;
			case 2:
				if (supervisor.isAvaiable()) {
					System.out.println("Supervisor is free and will take " + call.getId());
					return supervisor;
				}
				else if (manager.isAvaiable()) {
					System.out.println("Supervisor is not free but manager will take " + call.getId());
					return manager;
				}
				break;
			case 3:
				if (manager.isAvaiable()) {
					System.out.println("Manager is free and will take the " + call.getId());
					return manager;
				}
				break;
		}
		// If the return value is null, then all the employees are busy, the call will be put into waiting list
		System.out.println("All the employees are busy");
		return null;	
	}
	
	/* Dispatch call depend on the level passed in. 
	 * If not available, put the call into waiting list.
	 * Return 0 if call handler is found and call start successfully,
	 * Return -1 if call is in waiting list
	 */
	public int findCallHandler(Call call, int level) {
		
		Employee emp = getHandler(call, level);
		
		if (emp != null) {
			call.setHandler(emp);
			int res = call.callStart();
			// Employee is not able to handle to call
			if (res != 0) {
				call.resetHandler();
				// Telephonist cannot handle the call
				if (res == 1) {
					System.out.println("Employee can't handle the call, send it to supervisor.");
				}
				// Supervisor cannot handle the call
				else if (res == 2) {
					System.out.println("Supervisor can't handle the call, send it to manager.");
				}
				// Increment the request handler level and find handler again
				res = findCallHandler(call, res+1);
			}
			return res;
		}
		else {
			System.out.println("Please wait for an agent to reply");
			System.out.println("Add the call into waiting list");
			callQueue.add(call);
			return -1;
		}
	}
	
	/* End the call and try to find another call for the freeEmployee(supervisor and manager) to pick up
	 * Return 0 if a call in waiting list is launched
	 * Return -2 if the free employee is telephonist
	 * Return -1 if the waiting list is empty
	 */
	public int endCall(Call call) {
		Employee freeEmployee = call.callEnd();
		if (freeEmployee.getLevel() != 1) {
			System.out.println("Trying to find the earliest call in the waiting list");
			int res = assignCall(freeEmployee);
			return res;
		}
		return -2;
	}
	
	/* Assign the first call in waiting list to the new free employee
	 * Return 0 if a call in waiting list is launched
	 * Return -1 if waiting list is empty
	 */
	public int assignCall(Employee employee) {
		if (!callQueue.isEmpty()) {
			Call nextCall = callQueue.poll();
			int res = findCallHandler(nextCall, employee.getLevel());
			return res;
		}
		System.out.println("Waiting list is empty");
		return -1;
	}
}
