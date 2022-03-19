package assignment2;

import java.util.List;

import assignment2.Piece.Type;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class BoardPanel extends GridPane implements EventHandler<ActionEvent> {

    private final View view;
    private final Board board;
    private Cell fromCell;

    /**
     * Constructs a new GridPane that contains a Cell for each position in the board
     *
     * Contains default alignment and styles which can be modified
     * @param view
     * @param board
     */
    public BoardPanel(View view, Board board) {
        this.view = view;
        this.board = board;

        // Can modify styling
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: #181a1b;");
        int size = 550;
        this.setPrefSize(size, size);
        this.setMinSize(size, size);
        this.setMaxSize(size, size);

        setupBoard();
        updateCells();
    }


    /**
     * Setup the BoardPanel with Cells
     */
    private void setupBoard(){ // TODO
		
		for (int i=0; i < board.size; i++) {
			
			for (int j=0; j < board.size; j++) {
				Coordinate coord = new Coordinate(i, j);
				super.setConstraints(board.getCell(coord), j, i);
				board.getCell(coord).setOnAction(this);
			}
		}
		
		super.getChildren().addAll(board.getAllCells());
    }

    /**
     * Updates the BoardPanel to represent the board with the latest information
     *
     * If it's a computer move: disable all cells and disable all game controls in view
     *
     * If it's a human player turn and they are picking a piece to move:
     *      - disable all cells
     *      - enable cells containing valid pieces that the player can move
     * If it's a human player turn and they have picked a piece to move:
     *      - disable all cells
     *      - enable cells containing other valid pieces the player can move
     *      - enable cells containing the possible destinations for the currently selected piece
     *
     * If the game is over:
     *      - update view.messageLabel with the winner ('MUSKETEER' or 'GUARD')
     *      - disable all cells
     */
    protected void updateCells(){ // TODO

    	if (!(view.model.isHumanTurn())) {											// If it is a computers turn
    		disableAllCells(board.getAllCells(), true);
    		if (!(view.restartButton == null)) view.restartButton.setDisable(true);
    		if (!(view.saveButton == null)) view.saveButton.setDisable(true);
    		if (!(view.undoButton == null)) view.undoButton.setDisable(true);
    	}
    	
    	if (fromCell == null) {														// If human player is picking a piece
    		
    		view.setUndoButton();													// Must do this in case this is the first move (piece being picked)
    		
    		disableAllCells(board.getAllCells(), true);
    		disableAllCells(board.getPossibleCells(), false);
    		for (Cell cell: board.getPossibleCells()) {
    			cell.setOptionsColor();
    		}
    	}
    	
    	else {																		// If human player is picking destination.
    		disableAllCells(board.getAllCells(), true);	// Disable all cells
    		disableAllCells(board.getPossibleCells(), false);	// Enables other pieces of the current turn type to click so player can change
    		
    		for (Cell cell: board.getAllCells()) {			// Changing color of all the other pieces to default other than picked cell.
    			if (cell != fromCell) {
    				cell.setDefaultColor();
    			}
    		}
    		disableAllCells(board.getPossibleDestinations(fromCell), false);	// Enabling the cells with the possible destinations to be clicked.
    		fromCell.setAgentFromColor();										// Disabling and setting Agent From color the cell chosen to move.
    		fromCell.setDisable(true);
    		
    		setOptionColour(board.getPossibleDestinations(fromCell));			// Showing the option color on the cells that can be moved on to this turn.
    	}
    	
    	if (board.isGameOver()) {
    		if (!(view.undoButton == null)) view.undoButton.setDisable(true);
    		view.setMessageLabel(board.getWinner().getType() + " WINS!");
    		disableAllCells(board.getAllCells(), true);
    	}	
    }
    
    /**
     * sets all the cells in list to setOptionsColor
     */
    private void setOptionColour(List<Cell> cells) {
    	
    	for (Cell cell: cells) {
    		
    		cell.setOptionsColor();
    	}
    }
    
    /**
     * disables all cells or enables all cells 
     */
    private void disableAllCells(List<Cell> cells, boolean disable) {
    	
    	for (Cell cell: cells) {
    		cell.setDisable(disable);
    	}
    }
    
    /**
     * Handles Cell clicks and updates the board accordingly
     * When a Cell gets clicked the following must be handled:
     *  - If it's a valid piece that the player can move, select the piece and update the board
     *  - If it's a destination for a selected piece to move, perform the move and update the board
     * @param actionEvent
     */
    @Override
    public void handle(ActionEvent actionEvent) { // TODO
    	
    	if (fromCell == null) {
    		fromCell = ((Cell) actionEvent.getSource());
    		
    	}
    	
    	else {
    		Cell toCell = ((Cell) actionEvent.getSource());
    		if (toCell.hasPiece() && toCell.getPiece().getType() == fromCell.getPiece().getType()) {
    			fromCell = toCell;
    		}
    		else {
    			Move move = new Move(fromCell, toCell);			// Constructing a new Move and perform the move if it is valid
    			if (board.isValidMove(move)) {
    				view.model.move(move);	
    				
    				fromCell.setDefaultColor();		// Changing the moved cell colors back to default.
    				
    				for (Cell cell: board.getAllCells()) {		// Updating board i.e. changing the style of the cells back to default.
    					
    					cell.setDefaultColor();
    				}
    				
    				fromCell = null;		// Re-initialize from Cell to null for next move.
    				}
    			}
    		}
    	view.runMove();
    	
    }
}
