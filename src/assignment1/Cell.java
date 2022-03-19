package assignment1;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private Coordinate coordinate;
    private Piece piece;

    /**
     * Creates a cell with the given coordinate.
     * Piece is null if there is no piece on the cell.
     * @param coordinate Coordinate of the cell on the board.
     */
    public Cell(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Create a copy of a Cell
     * @param cell a Cell to make a copy of
     */
    public Cell(Cell cell) {
        this.coordinate = cell.coordinate;
        this.piece = cell.piece;
    }

    protected Coordinate getCoordinate() {
        return coordinate;
    }

    protected boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void removePiece() {
        this.piece = null;
    }
    
    public List<Cell> getPossibleDestinations(Board board){
    	List<Cell> destinations = new ArrayList<Cell>();
    	
    	for (Cell[] row : board.board) {
    		for (Cell cell : row) {
    			if (this.piece.performMovementBehaviour(this, cell, board)) destinations.add(cell);
    		}
    	}
    	return destinations;
    }

    @Override
    public String toString() {
        return hasPiece() ? piece.getSymbol() : " ";
    }
}
