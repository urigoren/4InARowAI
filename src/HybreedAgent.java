
public class HybreedAgent extends Agent
{
	public int MaxDepth;
	public int BestFirstIterations;
	HybreedAgent(boolean maxplayer)
	{
		super(maxplayer);
		MaxDepth=4;
		BestFirstIterations=500;
	}
	public String toString()
	{return (MaxPlayer?"Maximum":"Minimum")+" Hybreed Agent,Depth="+MaxDepth+", BFS="+BestFirstIterations;}
	HybreedAgent(boolean maxplayer,int maxdepth,int bfs_iterations)
	{
		super(maxplayer);
		MaxDepth=maxdepth;
		BestFirstIterations=bfs_iterations;
	}
	public GameState Move(GameState g)
	{
		node n=new node(g,MaxPlayer);
		n.RunAlphaBeta(MaxDepth);
		for (int i=0;i<BestFirstIterations;i++)
			n.BestFirstIteration();
		return n.Principal.GetGameState();
	}
}
