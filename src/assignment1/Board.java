package assignment1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import assignment1.Exceptions.PieceDoesntExistException;

public class Board {
    public int size = 5;

    // 2D Array of Cells for representation of the game board
    public final Cell[][] board = new Cell[size][size];

    private Piece.Type turn;
    private Piece.Type winner;
    private Piece.Type humanPlayerType;
    
    ThreeMusketeers game;
    
    
    /**
     * Create a Board with the current player turn set.
     */
    public Board(ThreeMusketeers game) {
        this.loadBoard("Boards/Starter.txt");
        this.game = game;
    }
    

    /**
     * Create a Board with the current player turn set and a specified board.
     * @param boardFilePath The path to the board file to import (e.g. "Boards/Starter.txt")
     */
    public Board(String boardFilePath, ThreeMusketeers game) {
        this.loadBoard(boardFilePath);
        this.game = game;
    }

    /**
     * Creates a Board copy of the given board.
     * @param board Board to copy
     */
    public Board(Board board) {
        this.size = board.size;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                this.board[row][col] = new Cell(board.board[row][col]);
            }
        }
        this.turn = board.turn;
        this.winner = board.winner;
    }

    /**
     * @return the Piece.Type (Muskeeteer or Guard) of the current turn
     */
    public Piece.Type getTurn() {
        return turn;
    }

    /**
     * Get the cell located on the board at the given coordinate.
     * @param coordinate Coordinate to find the cell
     * @return Cell that is located at the given coordinate
     */
    public Cell getCell(Coordinate coordinate) {
        return this.board[coordinate.row][coordinate.col];
    }

    /**
     * @return the game winner Piece.Type (Muskeeteer or Guard) if there is one otherwise null
     */
    public Piece.Type getWinner() {
        return winner;
    }

    /**
     * Gets all the Musketeer cells on the board.
     * @return List of cells
     */
    public List<Cell> getMusketeerCells() {
        return getAllCells()
                .stream()
                .filter(cell -> cell.hasPiece() && cell.getPiece().getType() == Piece.Type.MUSKETEER)
                .toList();
    }

    /**
     * Gets all the Guard cells on the board.
     * @return List of cells
     */
    public List<Cell> getGuardCells() {
        return getAllCells()
                .stream()
                .filter(cell -> cell.hasPiece() && cell.getPiece().getType() == Piece.Type.GUARD)
                .toList();
    }
    
    public List<Cell> getEmptyCells() {
        return getAllCells()
                .stream()
                .filter(cell -> !cell.hasPiece())
                .toList();
    }
    private List<Cell> getAllCells(){
    	List<Cell> cells = new ArrayList<Cell>();
    	
    	for (Cell[] row : board) {
    		for (Cell cell : row) {
    			cells.add(cell);
    		}
    	}
    	return cells;
    }
    
    
    

    /**
     * Executes the given move on the board and changes turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move) {
        Piece piece = move.fromCell.getPiece();
        move.toCell.setPiece(piece);
        move.fromCell.removePiece();
        changeTurn();
    }
    
    /**
     * Executes the given move on the board and does NOT change turns at the end of the method.
     * @param move a valid move
     */
    public void move(Move move, Boolean bool) {
    	
    	if (bool) {
            Piece piece = move.fromCell.getPiece();
            move.toCell.setPiece(piece);
            move.fromCell.removePiece();
    		
    	}

        
    }

    /**
     * Undo the move given.
     * @param move Copy of a move that was done and needs to be undone. The move copy has the correct piece info in the
     *             from and to cell fields. Changes turns at the end of the method.
     */
    public void undoMove(Move move) {
        Cell fromCell = getCell(move.fromCell.getCoordinate());
        Cell toCell = getCell(move.toCell.getCoordinate());
        fromCell.setPiece(move.fromCell.getPiece());
        toCell.setPiece(move.toCell.getPiece());
        changeTurn();
    }
    
    private void changeTurn() {
    	Piece.Type currentTurn = getTurn();
    	
    	if (currentTurn == Piece.Type.MUSKETEER) this.turn = Piece.Type.GUARD;
    	else this.turn = Piece.Type.MUSKETEER;
    }
    
    public Boolean onBoard(Coordinate coord) {
    	Boolean onHorizontal = 0 <= coord.col && coord.col < this.size;
    	Boolean onVertical = 0 <= coord.row && coord.row < this.size;
    	
    	return onHorizontal && onVertical;
    }

    /**
     * Get all the possible cells that have pieces that can be moved this turn.
     * @return      Cells that can be moved from the given cells
     */
    public List<Cell> getPossibleCells() {
        List<Cell> allCellsThisTurn = getTurn() == Piece.Type.MUSKETEER ? getMusketeerCells() : getGuardCells();
        List<Cell> possibleCells = new ArrayList<>();
        for (Cell cell : allCellsThisTurn) {
            if (!cell.getPossibleDestinations(this).isEmpty())
                possibleCells.add(cell);
        }
        return possibleCells;
    }

    /**
     * Get all the possible moves that can be made this turn.
     * @return List of moves that can be made this turn
     */
    public List<Move> getPossibleMoves() {
        List<Move> moves = new ArrayList<>();
        List<Cell> possibleCells = this.getPossibleCells();
        for (Cell fromCell: possibleCells) {
            List<Cell> possibleDestinations = fromCell.getPossibleDestinations(this);
            for (Cell toCell : possibleDestinations) {
                moves.add(new Move(fromCell, toCell));
            }
        }
        return moves;
    }

    /**
     * Checks if the game is over and sets the winner if there is one.
     * @return True, if the game is over, false otherwise.
     */
    public boolean isGameOver() {
        if (inSameRowOrSameCol(getMusketeerCells())) {
            winner = Piece.Type.GUARD;
            return true;
        }
        if (getPossibleCells().isEmpty()) {
            winner = Piece.Type.MUSKETEER;
            return true;
        }
        if (MovesCounter.instance.isMovesDone()) {
        	if (this.game.getHumanPlayerType() == Piece.Type.MUSKETEER) this.winner = Piece.Type.GUARD;
        	else this.winner = Piece.Type.MUSKETEER;
        	return true;
        }
        return false;
    }
    
    private Boolean inSameRowOrSameCol(List<Cell> musketeerCells) {
    	int colToMatch = musketeerCells.get(0).getCoordinate().col;
    	int rowToMatch = musketeerCells.get(0).getCoordinate().row;
    	
    	Boolean sameCol = true;
    	Boolean sameRow = true;
    	
    	for (Cell cell : musketeerCells) {
    		if (cell.getCoordinate().col != colToMatch) sameCol = false;
    		if (cell.getCoordinate().row != rowToMatch) sameRow = false;
    	}
    	
    	return sameCol || sameRow;
    }

    /**
     * Saves the current board state to the boards directory
     */
    public void saveBoard() {
        String filePath = String.format("boards/%s.txt",
                new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
        File file = new File(filePath);

        try {
            file.createNewFile();
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
            writer.write(turn.getType() + "\n");
            for (Cell[] row: board) {
                StringBuilder line = new StringBuilder();
                for (Cell cell: row) {
                    if (cell.getPiece() != null) {
                        line.append(cell.getPiece().getSymbol());
                    } else {
                        line.append("_");
                    }
                    line.append(" ");
                }
                writer.write(line.toString().strip() + "\n");
            }
            writer.close();
            System.out.printf("Saved board to %s.\n", filePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("Failed to save board to %s.\n", filePath);
        }
    }

    @Override
    public String toString() {
        StringBuilder boardStr = new StringBuilder("  | A B C D E\n");
        boardStr.append("--+----------\n");
        for (int i = 0; i < size; i++) {
            boardStr.append(i + 1).append(" | ");
            for (int j = 0; j < size; j++) {
                Cell cell = board[i][j];
                boardStr.append(cell).append(" ");
            }
            boardStr.append("\n");
        }
        return boardStr.toString();
    }

    /**
     * Loads a board file from a file path.
     * @param filePath The path to the board file to load (e.g. "Boards/Starter.txt")
     */
    private void loadBoard(String filePath) {
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.err.printf("File at %s not found.", filePath);
            System.exit(1);
        }

        turn = Piece.Type.valueOf(scanner.nextLine().toUpperCase());

        int row = 0, col = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] pieces = line.trim().split(" ");
            for (String piece: pieces) {
                Cell cell = new Cell(new Coordinate(row, col));
                
                if (piece.equals("X")) 
                	cell.setPiece(new Musketeer(new ClassicMusketeerMovementBehaviour()));         
                else if (piece.equals("O"))
                	cell.setPiece(new Guard(new ClassicGuardMovementBehaviour()));
                
                this.board[row][col] = cell;                
                col += 1;
            }
            col = 0;
            row += 1;
        }
        scanner.close();
        System.out.printf("Loaded board from %s.\n", filePath);        
    }
}
