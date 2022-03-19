package assignment1;

import java.util.List;
import java.util.Random; 

public class AddGuard implements Visitable {
	
	private Board board; 
	private Random randomGenerator;
	
	public AddGuard(Board board) {
		this.board = board; 
	}

	@Override
	public void accept(Visitor mysteryBox) {
		mysteryBox.visit(this);
		
	}
	
	public void add() {
		// adds a Guard to random cell
		
		List<Cell> emptyCells = this.board.getEmptyCells();
		
		
		randomGenerator = new Random();
		
		int index = randomGenerator.nextInt(emptyCells.size());
		
		Cell emptyCell = emptyCells.get(index);
		
		ClassicGuardMovementBehaviour behaviour = new ClassicGuardMovementBehaviour();
		
		Guard newGuardPiece = new Guard(behaviour); 
		
		emptyCell.setPiece(newGuardPiece);
		
		
	}

}
