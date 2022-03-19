package assignment1;

public abstract class MovesCounter {
	public static MovesCounter instance;

	protected int moves;
	
	protected MovesCounter(int moves) {
		this.moves = moves;
	}
	
	public void incrementMoves() {
		this.moves++;
	}
	
	public void decrementMoves() {
		this.moves--;
	}
	
	public abstract Boolean isMovesDone();
}
