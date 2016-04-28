package callCentre;

import static org.junit.Assert.*;

import org.junit.Test;

public class MainTest {
	
	@Test
	public void testSingleCall() {
		System.out.println("TestSingleCall start:");
		
		/* Create a call, a new telephonist will be create to pick up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call1 = new Call("call1", 1);
		int res1 = Main.callHandler.findCallHandler(call1, 1);
		assertEquals(0, res1);
		
		/* End a call
		 * Return value should be -2 because the free employee is telephonist
		 */
		int res2 = Main.callHandler.endCall(call1);
		assertEquals(-2, res2);
		
		System.out.println("TestSingleCall end.");
		
	}

	@Test
	public void testOneLevelCall() {
		System.out.println("TestOneLevelCall start:");
		
		/* Create a call, a new telephonist will be create to pick up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call1 = new Call("call1", 1);
		int res1 = Main.callHandler.findCallHandler(call1, 1);
		assertEquals(0, res1);
		
		/* Create a call, a new telephonist will be create to pick up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call2 = new Call("call2", 1);
		int res2 = Main.callHandler.findCallHandler(call2, 1);
		assertEquals(0, res2);
		
		/* End a call
		 * Return value should be -2 because the free employee is telephonist
		 */
		int res4 = Main.callHandler.endCall(call1);
		assertEquals(-2, res4);
		
		/* Create a call, a new telephonist will be create to pick up the call
		 * The handler of this call should be the first telephonist
		 */
		Call call3 = new Call("call3", 1);
		Main.callHandler.findCallHandler(call3, 1);
		assertEquals(call3.getHandler(), Main.callHandler.getTelephonists().get(0));
		
		/* End a call
		 * Return value should be -2 because the free employee is telephonist
		 */
		int res6 = Main.callHandler.endCall(call2);
		assertEquals(-2, res6);
		
		/* End a call
		 * Return value should be -2 because the free employee is telephonist
		 */
		int res7 = Main.callHandler.endCall(call3);
		assertEquals(-2, res7);
		
		System.out.println("TestOneLevelCall end.\n");
	}
	
	@Test
	public void testTwoLevelCallSingle() {
		System.out.println("TestTwoLevelCallSingle start:");
		
		/* Create a call, telephonist will be create to pick up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call1 = new Call("call1", 1);
		int res1 = Main.callHandler.findCallHandler(call1, 1);
		assertEquals(0, res1);
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor will be picking up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call2 = new Call("call2", 2);
		int res2 = Main.callHandler.findCallHandler(call2, 1);
		assertEquals(0, res2);
		
		/* End a call
		 * Return value should be -2 because the free employee is telephonist
		 */
		int res3 = Main.callHandler.endCall(call1);
		assertEquals(-2, res3);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Return value should be -1 because no call in the waiting list to be picked up
		 */
		int res4 = Main.callHandler.endCall(call2);
		assertEquals(-1, res4);
		
		System.out.println("TestTwoLevelCallSingle end.\n");
	}
	
	@Test
	public void testThreeLevelCallSingle() {
		System.out.println("TestThreeLevelCallSingle start:");
		
		/* Create a call, telephonist will be create to pick up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call1 = new Call("call1", 1);
		int res1 = Main.callHandler.findCallHandler(call1, 1);
		assertEquals(0, res1);
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor will be picking up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call2 = new Call("call2", 2);
		int res2 = Main.callHandler.findCallHandler(call2, 1);
		assertEquals(0, res2);
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor cannot handle the call, pass it to manager
		 * Manager will be picking up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call3 = new Call("call3", 3);
		int res3 = Main.callHandler.findCallHandler(call3, 1);
		assertEquals(0, res3);
		
		/* End a call
		 * Return value should be -2 because the free employee is telephonist
		 */
		int res4 = Main.callHandler.endCall(call1);
		assertEquals(-2, res4);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Return value should be -1 because no call in the waiting list to be picked up
		 */
		int res5 = Main.callHandler.endCall(call2);
		assertEquals(-1, res5);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Return value should be -1 because no call in the waiting list to be picked up
		 */
		int res6 = Main.callHandler.endCall(call3);
		assertEquals(-1, res6);
		
		System.out.println("TestThreeLevelCallSingle end.\n");
	}
	

	
	@Test
	public void testMultiLevelCallSupervisorBusy() {
		System.out.println("TestTwoLevelCallSupervisorBusy start:");
		
		/* Create a call, telephonist will be picking up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call1 = new Call("call1", 1);
		int res1 = Main.callHandler.findCallHandler(call1, 1);
		assertEquals(0, res1);
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor will be picking up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call2 = new Call("call2", 2);
		int res2 = Main.callHandler.findCallHandler(call2, 1);
		assertEquals(0, res2);
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor is busy and cannot take the call
		 * Manager will take the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call3 = new Call("call3", 2);
		int res3 = Main.callHandler.findCallHandler(call3, 1);
		assertEquals(0, res3);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Return value should be -1 because no call in the waiting list to be picked up
		 */
		int res4 = Main.callHandler.endCall(call2);
		assertEquals(-1, res4);
		
		/* End a call
		 * Return value should be -2 because the free employee is telephonist
		 */
		int res5 = Main.callHandler.endCall(call1);
		assertEquals(-2, res5);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Return value should be -1 because no call in the waiting list to be picked up
		 */
		int res6 = Main.callHandler.endCall(call3);
		assertEquals(-1, res6);
		
		System.out.println("TestTwoLevelCallSupervisorBusy end.\n");
	}

	@Test
	public void testMultiLevelCallWithWaitingList() {
		System.out.println("TestMultiLevelCallWithWaitingList start:");
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor will be picking up the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call1 = new Call("call1", 2);
		int res1 = Main.callHandler.findCallHandler(call1, 1);
		assertEquals(0, res1);
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor is busy and cannot take the call
		 * Manager will take the call
		 * Return value should be 0 because a call successfully created
		 */
		Call call2 = new Call("call2", 2);
		int res2 = Main.callHandler.findCallHandler(call2, 1);
		assertEquals(0, res2);
		
		/* Create a call
		 * Telephonist cannot handle the call, pass it to supervisor
		 * Supervisor is busy and cannot take the call
		 * Manager is busy and cannot take the call
		 * Return value should be -1 because the call is in waiting list
		 */
		Call call3 = new Call("call3", 2);
		int res3 = Main.callHandler.findCallHandler(call3, 1);
		assertEquals(-1, res3);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Supervisor is busy and cannot take the call
		 * Manager will take the call
		 * Return value should be 0 because a call in waiting list is launched
		 */
		int res4 = Main.callHandler.endCall(call2);
		assertEquals(0, res4);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Return value should be -1 because no call in the waiting list to be picked up
		 */
		int res5 = Main.callHandler.endCall(call1);
		assertEquals(-1, res5);
		
		/* End a call, will try to find if there is a call in waiting list
		 * Return value should be -1 because no call in the waiting list to be picked up
		 */
		int res6 = Main.callHandler.endCall(call3);
		assertEquals(-1, res6);
		System.out.println("TestMultiLevelCallWithWaitingList end.\n");
	}
}
