
public class RunHybreedAgents
{
	private static int PlayMatch(Agent A,Agent B,boolean PrintGame)
	{

		GameState g=new GameState();
		if (PrintGame)
		{
			System.out.println(g);
			System.out.println("Evaluation: "+mmparams.Heuristic(g));
		}
		while (!GameState.CheckDraw(g))
		{
			g=A.Move(g);
			if (PrintGame)
			{
				System.out.println(g);
				System.out.println("Evaluation: "+mmparams.Heuristic(g));
			}
			if (GameState.CheckVictory(g)==1)
			{
				if (PrintGame)
				{
					System.out.print(A);
					System.out.println(" won");
				}
				return 1;
			}
			g=B.Move(g);
			if (PrintGame)
			{
				System.out.println(g);
				System.out.println("Evaluation: "+mmparams.Heuristic(g));
			}
			if (GameState.CheckVictory(g)==2)
			{
				if (PrintGame)
				{
					System.out.print(B);
					System.out.println(" won");
				}
				return -1;
			}
		}
		if (PrintGame)
			System.out.println("Draw");
		return 0;
		
	}
	public static void main(String[] args)
	{
		//PlayMatch(new HybreedAgent(true,1,100),new HybreedAgent(false,3,10),true);
		HybreedAgent Max=new HybreedAgent(true);
		HybreedAgent Min=new HybreedAgent(false);
		for (int i=0;i<8;i++)
		{
			Max.MaxDepth=i;
			Max.BestFirstIterations=(8-i)*1250;
			for (int j=0;j<8;j++)
			{
				Min.MaxDepth=j;
				Min.BestFirstIterations=(8-j)*1250;
				System.out.print(PlayMatch(Max, Min, false));
				System.out.print(',');
			}
			System.out.print('\n');
		}

	}

}
