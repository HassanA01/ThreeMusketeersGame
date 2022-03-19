package assignment1;

public class RealMovesCounter extends MovesCounter{

  
	protected RealMovesCounter(int moves) {
		
		super(moves);
		
	}
	

	@Override
	public Boolean isMovesDone() {
		
		return this.moves <= 0;
	}

}
