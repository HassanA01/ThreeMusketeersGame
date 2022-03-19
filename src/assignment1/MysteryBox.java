package assignment1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random; 

public class MysteryBox implements Visitor{
	
	
	private List<Visitable> featureObjects = new ArrayList<Visitable>();
	private Random randomGenerator;
	private Board board; 
	private Boolean used = false; 
	private Cell cellObj; 
	
	
	public MysteryBox(Board board, Cell cellObj) {
		
		this.board = board; 
		this.cellObj = cellObj; 
		
		

		AddMusketeer addMusketeerObj = new AddMusketeer(this.board);
		AddGuard addGuardObj = new AddGuard(this.board);
		AddMoves addMovesObj = new AddMoves(this.board);
		SkipTurn SkipTurnObj = new SkipTurn(this.board); 
	
		

		featureObjects.add(addMusketeerObj); 
		featureObjects.add(addGuardObj); 
		featureObjects.add(addMovesObj); 
		featureObjects.add(SkipTurnObj); 
		
	}
	
	public String feature() {
		
		
		
		randomGenerator = new Random();
		
		int index = randomGenerator.nextInt(featureObjects.size());
		
		Visitable visitableObj = featureObjects.get(index);
		
		
		// Check what feature is randomly selected and call the visit method for it. 
		if (visitableObj instanceof AddMusketeer ) {
			
			this.visit((AddMusketeer)visitableObj);
			
			this.used = true;
			
			return "Added a musketeer!"; 

		}
		
		else if (visitableObj instanceof AddGuard ) {
			
			this.visit((AddGuard)visitableObj);
			
			this.used = true;
			
			return "Added a guard!"; 

		}
		else if (visitableObj instanceof AddMoves ) {
			
			this.visit((AddMoves)visitableObj);
			
			this.used = true;
			
			return "Free moves added!"; 

		}
		
		else if (visitableObj instanceof SkipTurn ) {
			
			this.visit((SkipTurn)visitableObj);
			
			this.used = true;
			
			return "Turn skipped, sorry!"; 

		}
		
		
		this.used = true;
		
		return null; 
		
		
	}
	
		

	@Override
	public void visit(AddMusketeer addMusketeerObj) {
		// TODO Auto-generated method stub
		addMusketeerObj.add();
	}
	
	@Override
	public void visit(AddGuard addGuardObj) {
		// TODO Auto-generated method stub
		addGuardObj.add();
	}
	
	@Override
	public void visit(AddMoves addMovesObj) {
		// TODO Auto-generated method stub
		addMovesObj.add();
	}
	
	@Override
	public void visit(SkipTurn skipTurnObj) {
		// TODO Auto-generated method stub
		skipTurnObj.skipPlayerTurn();
	}


}
