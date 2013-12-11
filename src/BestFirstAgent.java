
public class BestFirstAgent extends Agent
{
	public long IterationLimit;
	BestFirstAgent(boolean maxplayer)
	{
		super(maxplayer);
		IterationLimit=1;
	}
	public String toString()
	{return (MaxPlayer?"Maximum":"Minimum")+" Best-First-Minimax Agent";}
	public GameState Move(GameState g)
	{
		node n=new node(g,MaxPlayer);
		for (int i=0;i<IterationLimit;i++)
			n.BestFirstIteration();
		if (n.Principal==null)//draw
			return n.GetGameState();
		return n.Principal.GetGameState();
	}
}
