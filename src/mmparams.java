public class mmparams
{
	public static double Heuristic(GameState g)
	{
		byte b=GameState.CheckFours(g,(byte)0);//Absolute victory
		if (b==1)//player 1 won
			return 1000;
		else if (b==2)//player 2 won
			return -1000;
		return GameState.CountFours(g, 1)-GameState.CountFours(g, 2);
	}
}
