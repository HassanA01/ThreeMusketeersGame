package assignment1;

import java.util.Queue;

public class OrderQueue {
	
	private Queue<Order> orders;
	
	
	public void sellCredit() {
		
	}
	
	public void placeOrder(Order order) {
		
		orders.remove().executeOrder();
	}
}
