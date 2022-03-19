package assignment1;

public class NullMovesCounter extends MovesCounter{

	public NullMovesCounter() {
		super(0);
	}

	@Override
	public Boolean isMovesDone() {
		return false;
	}

}
