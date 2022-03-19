package assignment1;

public class CreatePiece {			// Factory Class.
	
	
	public Piece createPiece(Piece.Type piece, MovementBehaviour behaviour) {
		
		
		if (piece.equals(Piece.Type.MUSKETEER)) {
			return new Musketeer(behaviour);
		}
		
		return new Guard(behaviour);
		
	}
}
