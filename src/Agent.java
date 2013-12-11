
public abstract class Agent
{
	public boolean MaxPlayer;
	public GameState Move(GameState g)
	{
		return null;
	}
	public String toString()
	{return (MaxPlayer?"Maximum":"Minimum")+" Agent";}
	Agent(boolean maxplayer)
	{
		this.MaxPlayer=maxplayer;
	}

}
