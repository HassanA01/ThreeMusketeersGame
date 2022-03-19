package assignment1;

public class BuyGuardOrder implements Order {
	
	private ThreeMusketeers threeMusk;
	
	public BuyGuardOrder(ThreeMusketeers threeMusk) {
		
		this.threeMusk = threeMusk;
	}

	@Override
	public void executeOrder() {
		
		threeMusk.buyPiece();
		
	}
	
	
}
