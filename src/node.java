import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class node implements Comparable<node>
{
	public double alpha;
	public double beta;
	private double value;
	private List<node> Sons;
	private node Father;
	private int Depth;
	public node Principal;
	private GameState Game;
	private boolean MaxPlayer;

	private void Init (GameState game,boolean maximum_player,node principal,node parent,double alpha,double beta)
	{
		this.Game=game;
		this.MaxPlayer=maximum_player;
		this.Principal=principal;
		this.Sons=null;
		this.Father=parent;
		if (this.Father==null)
			this.Depth=0;
		else
			this.Depth=this.Father.getDepth()+1;
		this.value=mmparams.Heuristic(this.Game);
		this.alpha=alpha;
		this.beta=beta;
	}
	node(GameState game,boolean maximum_player,node parent,double alpha,double beta)
	{Init(game,maximum_player,null, parent, alpha, beta);}

	node(GameState game,boolean maximum_player,node parent)
	{Init(game,maximum_player,null,parent,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);}

	node(GameState game,boolean maximum_player)
	{Init(game,maximum_player,null,null,Double.NEGATIVE_INFINITY,Double.POSITIVE_INFINITY);}
	
	public void setValue(double value) {
		this.value = value;
	}
	public double getValue() {
		return value;
	}
	public GameState GetGameState()
	{return Game;}
	public long GetSubTreeSize()
	{
		long ret=1;
		if (Sons==null)
			return ret;
		if (Sons.isEmpty())
			return ret;
		for (node Ni:Sons)
			ret+=Ni.GetSubTreeSize();
		return ret;
	}
	
	public int compareTo(node o)
	{
		if (MaxPlayer)
		{
			if (getValue()>o.getValue())
				return 1;
			if (getValue()<o.getValue())
				return -1;
			else
				return 0;
		}
		else
		{
			if (getValue()>o.getValue())
				return -1;
			if (getValue()<o.getValue())
				return 1;
			else
				return 0;
		}
	}
	public int getDepth() {
		return Depth;
	}
	
	private void GenerateSonNodes()
	{
		ArrayList<GameState> PossibleGameStates=Game.GenerateSons(MaxPlayer);
		Sons=new ArrayList<node>();
		for(GameState Gi:PossibleGameStates)
		{
			Sons.add(new node(Gi, !MaxPlayer, this, alpha, beta));
		}
		Collections.sort(Sons);
	}
	public void UpdatePrincipal()
	{
		if (Sons==null) return;
		if (MaxPlayer)
		{
			double max_v=Double.NEGATIVE_INFINITY;
			for (node ni:Sons)
			{
				if (max_v<ni.getValue())
				{
					max_v=ni.getValue();
					this.Principal=ni;
				}
			}
		}
		else
		{
			double min_v=Double.POSITIVE_INFINITY;
			for (node ni:Sons)
			{
				if (min_v>ni.getValue())
				{
					min_v=ni.getValue();
					this.Principal=ni;
				}
			}
		}
		this.value=this.Principal.getValue();
	}
	public boolean SonNodesCalculation()
	{
		GenerateSonNodes();
		if (Sons.isEmpty()) return false;
		UpdatePrincipal();
		return true;
	}

	public double RunAlphaBeta(int MaxDepth)
	{
		//Reached the maximum depth 
		if (Depth==MaxDepth)
			return value;
		//generate sons
		GenerateSonNodes();
		if (Sons.isEmpty())
			return value;
		//alpha-beta recursion
		if (MaxPlayer)
		{
			for (node Ni:Sons)
			{
				Ni.alpha=this.alpha;
				this.value = Ni.RunAlphaBeta(MaxDepth);
				if (this.value > this.alpha)
				{
					this.alpha = this.value;
					this.Principal=Ni;
				}
				if (this.alpha >= this.beta)
					return this.alpha;
			}
			return this.alpha;
		}
		else
		{
			for (node Ni:Sons)
			{
				Ni.beta=this.beta;
				this.value = Ni.RunAlphaBeta(MaxDepth);
				if (this.value < this.beta)
				{
					this.beta =this.value;
					this.Principal=Ni;
				}
				if (this.beta <= this.alpha)
					return this.beta;
			}
			return this.beta;
		}

	}
	public void UpdateUp()
	{
		node old_principal=this.Principal;
		UpdatePrincipal();
		if (old_principal==this.Principal)
		{
			if (this.Father!=null)
				this.Father.UpdateUp();
		}
		else
		{
			//update down
			node n=this;
			while((n.Sons!=null)&&(!n.Sons.isEmpty()))
			{
				if (n.Principal==null)
					n.UpdatePrincipal();
				n=n.Principal;
			}
			n.UpdateUp();
		}
		
		
		
	}
	
	public boolean BestFirstIteration()
	{
		double old_value=0;
		boolean hasSons=false;
		//find pricipal leaf
		node n=this;
		while (n.Principal!=null)
			n=n.Principal;
		old_value=n.getValue();
		hasSons=n.SonNodesCalculation();
		if (!hasSons)
			return false;
		if (n.getValue()==old_value)//no need to update the principal leave
			return true;
		UpdateUp();
		return true;
		
	}
}
