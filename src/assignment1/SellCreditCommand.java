package assignment1;

public class SellCreditCommand implements Order{
	
	private ThreeMusketeers threeMusk;

	public SellCreditCommand(ThreeMusketeers three) {
		
		this.threeMusk = three;
	}
	
	@Override
	public void executeOrder() {
		
		this.threeMusk.sellCredit();
		
	}
	
	
}
