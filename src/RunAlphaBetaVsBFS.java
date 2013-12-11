
public class RunAlphaBetaVsBFS {

	public static void main(String[] args)
	{
		System.out.println("---------A vs B: AlphaBeta Plays First---------");
		GameState g=new GameState();
		AlphaBetaAgent A=new AlphaBetaAgent(true);
		BestFirstAgent B=new BestFirstAgent(false);
		A.MaxDepth=7;
		while (!GameState.CheckDraw(g))
		{
			//System.out.print(g);
			g=A.Move(g);
			//System.out.print(g);
			B.IterationLimit=1+A.GetSubTreeSize();
			System.out.println("Number of nodes: "+B.IterationLimit);
			if (GameState.CheckVictory(g)==1)
			{
				System.out.print(A);
				System.out.println(" won");
				break;
			}
			g=B.Move(g);
			if (GameState.CheckVictory(g)==2)
			{
				System.out.print(B);
				System.out.println(" won");
				break;
			}
			
		}
		if (GameState.CheckDraw(g))
			System.out.println("Draw");
		System.out.println("---------A vs B: Best First Plays First----------");
		g=new GameState();
		A=new AlphaBetaAgent(false);
		B=new BestFirstAgent(true);
		B.IterationLimit=100;
		g=B.Move(g);
		while (!GameState.CheckDraw(g))
		{
			//System.out.print(g);
			g=A.Move(g);
			//System.out.print(g);
			B.IterationLimit=1+A.GetSubTreeSize();
			System.out.println("Number of nodes: "+B.IterationLimit);
			if (GameState.CheckVictory(g)==1)
			{
				System.out.print(A);
				System.out.println(" won");
				break;
			}
			g=B.Move(g);
			if (GameState.CheckVictory(g)==2)
			{
				System.out.print(B);
				System.out.println(" won");
				break;
			}
			
		}
		if (GameState.CheckDraw(g))
			System.out.println("Draw");
	}

}
