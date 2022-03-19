package assignment1;
import assignment1.ThreeMusketeers; 



public class SkipTurn implements Visitable {
	
	
	private Board board; 
	private Piece.Type turn;
	private Agent musketeerAgent, guardAgent;
	
	
	public SkipTurn(Board board) {
		this.board = board; 
		this.turn = this.board.getTurn();
	}

	@Override
	public void accept(Visitor mysteryBox) {
		mysteryBox.visit(this);
		
	}
	
	public void skipPlayerTurn() {
		
		final Agent currentAgent;
		
		// Determine type of current agent 
        if (this.turn == Piece.Type.MUSKETEER)
            currentAgent = musketeerAgent;
        else
            currentAgent = guardAgent;
        
        
        
        // Get move depending on type of agent
        if (currentAgent instanceof HumanAgent) {
        	
        	HumanAgent agent = new HumanAgent(this.board); 
        	
        	Move move = agent.getMove();
        	
        	
        	this.board.move(move, true);
        	
        }
        
        else if (currentAgent instanceof RandomAgent) {
        	
        	RandomAgent agent = new RandomAgent(this.board); 
        	
        	Move move = agent.getMove();
        	
        	
        	this.board.move(move, true);
        	
        }
        
        else if (currentAgent instanceof GreedyAgent) {
        	
        	GreedyAgent agent = new GreedyAgent(this.board); 
        	
        	Move move = agent.getMove();
        	
        	
        	this.board.move(move, true);
        	
        }

		
		
	}

}
