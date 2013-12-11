
public class AlphaBetaAgent extends Agent
{
	private long SubTreeSize;
	public int MaxDepth=5;
	AlphaBetaAgent(boolean maxplayer)
	{
		super(maxplayer);
		SubTreeSize=1;
	}
	public long GetSubTreeSize()
	{return SubTreeSize;}
	public String toString()
	{return (MaxPlayer?"Maximum":"Minimum")+" AlphaBeta Agent";}
	public GameState Move(GameState g)
	{
		node n=new node(g,MaxPlayer);
		n.RunAlphaBeta(MaxDepth);
		SubTreeSize=n.GetSubTreeSize();
		return n.Principal.GetGameState();
	}

}
