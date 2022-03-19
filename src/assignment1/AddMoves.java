package assignment1;



public class AddMoves implements Visitable{

	private final int numMoves = 3;
	private Board board; 
	
	
	
	public AddMoves(Board board) {
		this.board = board; 
	}

	@Override
	public void accept(Visitor mysteryBox) {
		mysteryBox.visit(this);
		
	}
	
	public void add() {
		// adds <numMoves> amount of moves for the number of moves allowed in the game
		
		int i = 0; 
		
		
		while (i < numMoves) {
			
			MovesCounter.increamentMoves();
			
			i +=1;
		}
		
	}	
		
		

		
		
}


