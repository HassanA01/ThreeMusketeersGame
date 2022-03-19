package assignment1;

import java.util.List;
import java.util.Random; 

public class AddMusketeer implements Visitable {
	
	private Board board; 
	private Random randomGenerator;
	
	public AddMusketeer(Board board) {
		this.board = board; 
	}

	@Override
	public void accept(Visitor mysteryBox) {
		mysteryBox.visit(this);
		
	}
	
	public void add() {
		// adds a musketeer to random cell
		
		List<Cell> emptyCells = this.board.getEmptyCells();
		
		
		randomGenerator = new Random();
		
		int index = randomGenerator.nextInt(emptyCells.size());
		
		Cell emptyCell = emptyCells.get(index);
		
		ClassicMusketeerMovementBehaviour behaviour = new ClassicMusketeerMovementBehaviour();
		
		Musketeer newMusketeerPiece = new Musketeer(behaviour); 
		
		emptyCell.setPiece(newMusketeerPiece);
		
		
	}

}
