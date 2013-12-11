import java.util.ArrayList;


public class GameState
{
	private byte[][] Board;
	GameState()
	{
		Board=new byte[7][6];
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				Board[i][j]=0;
	}
	GameState(byte[][] B)
	{
		Board=new byte[7][6];
		for(int i=0;i<7;i++)
			for(int j=0;j<6;j++)
				Board[i][j]=B[i][j];
	}
	public GameState clone()
	{
		return new GameState(Board);
	}
	public byte Get(int col,int row)
	{
		return Board[col][row];
	}
	public boolean Put(boolean max_player,int col)
	{
		for (int j=0;j<6;j++)
		{
			if (Board[col][j]==0)
			{
				Board[col][j]=(byte) (max_player?1:2);
				return true;
			}
		}
		return false;
	}
	public ArrayList<GameState> GenerateSons(boolean max_player)
	{
		ArrayList<GameState> ret = new ArrayList<GameState>();
		GameState G=this.clone();
		for (int i=0;i<7;i++)
		{
			if (G.Put(max_player, i))
			{
				ret.add(G);
				G=this.clone();
			}
		}
		return ret;
	}
	public String toString()
	{
		String s="Game State:\n";
		for (int j=5;j>=0;j--)
		{
			for (int i=0;i<7;i++)
			{
				if (Get(i, j)==1)
					s+="X";
				else if (Get(i, j)==2)
					s+="O";
				else
					s+="_";
			}
			s+="\n";
		}
		return s;
	}
	
	public static byte CheckFours(GameState g,byte doesnt_have_player)
	{
		int i=0,j=0;
		for (j=0;j<6;j++)
		{
			for (i=0;i<4;i++)
			{
				if ((g.Get(i, j)!=doesnt_have_player)&&(g.Get(i, j)==g.Get(i+1, j))&&(g.Get(i, j)==g.Get(i+2, j))&&(g.Get(i, j)==g.Get(i+3, j)))
					return g.Get(i, j);
			}
		}
		for (j=0;j<3;j++)
		{
			for (i=0;i<7;i++)
			{
				if ((g.Get(i, j)!=doesnt_have_player)&&(g.Get(i, j)==g.Get(i, j+1))&&(g.Get(i, j)==g.Get(i, j+2))&&(g.Get(i, j)==g.Get(i, j+3)))
					return g.Get(i, j);
			}
		}
		for (j=0;j<3;j++)
		{
			for (i=0;i<4;i++)
			{
				if ((g.Get(i, j)!=doesnt_have_player)&&(g.Get(i, j)==g.Get(i+1, j+1))&&(g.Get(i, j)==g.Get(i+2, j+2))&&(g.Get(i, j)==g.Get(i+3, j+3)))
					return g.Get(i, j);
				if ((g.Get(i, j+3)!=doesnt_have_player)&&(g.Get(i, j+3)==g.Get(i+1, j+2))&&(g.Get(i, j+3)==g.Get(i+2, j+1))&&(g.Get(i, j+3)==g.Get(i+3, j)))
					return g.Get(i, j+3);
			}
		}
		return -1;
	}
	public static int CountFours(GameState g,int player)
	{
		int i=0,j=0;
		int ret=0;
		byte other_player=(byte) (3-player);
		for (j=0;j<6;j++)
		{
			for (i=0;i<4;i++)
			{
				if ((g.Get(i, j)!=other_player)&&(g.Get(i+1, j)!=other_player)&&(g.Get(i+2, j)!=other_player)&&(g.Get(i+3, j)!=other_player))
					ret+=(g.Get(i, j)==player?1:0)+(g.Get(i+1, j)==player?1:0)+(g.Get(i+2, j)==player?1:0)+(g.Get(i+3, j)==player?1:0);
			}
		}
		for (j=0;j<3;j++)
		{
			for (i=0;i<7;i++)
			{
				if ((g.Get(i, j)!=other_player)&&(g.Get(i, j+1)!=other_player)&&(g.Get(i, j+2)!=other_player)&&(g.Get(i, j+3)!=other_player))
					ret+=(g.Get(i, j)==player?1:0)+(g.Get(i, j+1)==player?1:0)+(g.Get(i, j+2)==player?1:0)+(g.Get(i, j+3)==player?1:0);
			}
		}
		for (j=0;j<3;j++)
		{
			for (i=0;i<4;i++)
			{
				if ((g.Get(i, j)!=other_player)&&(g.Get(i+1, j+1)!=other_player)&&(g.Get(i+2, j+2)!=other_player)&&(g.Get(i+3, j+3))!=other_player)
					ret+=(g.Get(i, j)==player?1:0)+(g.Get(i+1, j+1)==player?1:0)+(g.Get(i+2, j+2)==player?1:0)+(g.Get(i+3, j+3)==player?1:0);
				if ((g.Get(i, j+3)!=other_player)&&(g.Get(i+1, j+2)!=other_player)&&(g.Get(i+2, j+1)!=other_player)&&(g.Get(i+3, j))!=other_player)
					ret+=(g.Get(i, j+3)==player?1:0)+(g.Get(i+1, j+2)==player?1:0)+(g.Get(i+2, j+1)==player?1:0)+(g.Get(i+3, j)==player?1:0);
			}
		}
		return ret;
	}
	public static byte CheckVictory(GameState g)
	{return CheckFours(g,(byte)0);}
	public static boolean CheckDraw(GameState g)
	{
		for (int i=0;i<7;i++)
		{
			if (g.Get(i, 5)==0)
				return false;
		}
		return true;
	}
}
