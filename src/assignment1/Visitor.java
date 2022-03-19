package assignment1;

public interface Visitor {
	
	
	public void visit(AddMusketeer addMusketeerObj); 
	public void visit(AddGuard addGuardObj); 
	public void visit (AddMoves addMovesobj); 
	public void visit (SkipTurn skipTurnobj); 

}
