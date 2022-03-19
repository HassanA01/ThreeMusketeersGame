package assignment1;

public class ClassicGuardMovementBehaviour implements MovementBehaviour{

	@Override
	public Boolean movementBehaviour(Cell fromCell, Cell toCell, Board board) {
		return this.isNextTo(fromCell.getCoordinate(), toCell.getCoordinate()) && !toCell.hasPiece();
	}

    private Boolean isNextTo(Coordinate fromCoord, Coordinate toCoord) {
    	Coordinate absOffset = new Coordinate(Math.abs(fromCoord.col - toCoord.col), 
    			Math.abs(fromCoord.row - toCoord.row));
    	
    	return absOffset.col <= 1 && absOffset.row <= 1 && !(absOffset.col == 1 && absOffset.row == 1);
    }

}
