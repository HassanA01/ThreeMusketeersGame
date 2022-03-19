package assignment1;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

import assignment1.Exceptions.PieceDoesntExistException;


public abstract class Piece {

    /**
     * All possible Piece types
     * Musketeer and Guard
     */
    public enum Type {
        MUSKETEER("MUSKETEER"),
        GUARD("GUARD");

        private final String type;
        Type(final String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }
    }

    private String symbol;
    private Type type;
    private MovementBehaviour movementBehaviour;
    /**
     * Construct a new Piece
     * @param symbol to represent the Piece
     * @param type a Type of Piece (Musketeer or Guard)
     */
    public Piece(String symbol, Type type, MovementBehaviour movementBehaviour) {
        this.symbol = symbol;
        this.type = type;
        this.movementBehaviour = movementBehaviour;
    }

    /**
     * @return symbol representation of Piece
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * @return the Type of piece (Musketeer or Guard)
     */
    public Type getType() {
        return type;
    }	
    
    
    /**
     * Precondition: fromCell is the cell this piece is currently on.
     * @param fromCell is the cell to move from.
     * @param toCell is the cell to move onto.
     * @param board is the board of cells.
     * @return true if the piece on thisCell is allowed to move onto toCell. Otherwise return false.
     */
    public Boolean performMovementBehaviour(Cell fromCell, Cell toCell, Board board) {
    	return this.movementBehaviour.movementBehaviour(fromCell, toCell, board);
    }
}
