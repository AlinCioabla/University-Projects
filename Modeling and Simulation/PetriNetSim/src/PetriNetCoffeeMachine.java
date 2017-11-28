import java.util.LinkedList;
import java.util.Scanner;


public class PetriNetCoffeeMachine extends PetriNet
{
	public PetriNetCoffeeMachine()
	{
		super(Initialize());
	}
	
	public String GetCurrentState()
	{
		for(Transition transition : mTransitions)
		{
			LinkedList<Location> locs = transition.GetLocations();
			for(Location loc:locs)
			{
				if(loc.GetJetoane() == 1)
				{
					return loc.GetTag();
				}
			}
		}
		return null;
	}
	
	private static LinkedList<Transition> Initialize()
	{
		LinkedList<Transition> transitions = new LinkedList<Transition>();
		
		Location lZeroB = new Location("0b");
		Location lCinciB = new Location("5b");
		Location lZeceB = new Location("10b");
		Location lCincispeB = new Location("15b");
		Location lDouazeciB = new Location("20b");
		
		lZeroB.UpdateJetoane(1);
		
		transitions.add(new Transition("dep5b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lZeroB, Direction.OUT)); 
			add(new Arc(1, lCinciB, Direction.IN)); 
			}}
		));
		
		transitions.add(new Transition("dep10b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lCinciB, Direction.OUT)); 
			add(new Arc(1, lCincispeB, Direction.IN)); 
			}}
		));
		
		transitions.add(new Transition("dep10b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lZeroB, Direction.OUT)); 
			add(new Arc(1, lZeceB, Direction.IN)); 
			}}
		));
		
		transitions.add(new Transition("dep10b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lZeceB, Direction.OUT)); 
			add(new Arc(1, lDouazeciB, Direction.IN)); 
			}}
		));

		
		transitions.add(new Transition("dep5b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lCinciB, Direction.OUT)); 
			add(new Arc(1, lZeceB, Direction.IN)); 
			}}
		));

		transitions.add(new Transition("dep5b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lZeceB, Direction.OUT)); 
			add(new Arc(1, lCincispeB, Direction.IN)); 
			}}
		));
		
		transitions.add(new Transition("dep5b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lCincispeB, Direction.OUT)); 
			add(new Arc(1, lDouazeciB, Direction.IN)); 
			}}
		));
		
		
		
		//-------- cumpara cafea de 15 cand ai 20
		transitions.add(new Transition("iacafea15b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lDouazeciB, Direction.OUT)); 
			add(new Arc(1, lCinciB, Direction.IN)); 
			}}
		));
		
		
		transitions.add(new Transition("iacafea15b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lCincispeB, Direction.OUT)); 
			add(new Arc(1, lZeroB, Direction.IN)); 
			}}
		));
		
		transitions.add(new Transition("iacafea20b", new LinkedList<Arc>() {{ 
			add(new Arc(1, lDouazeciB, Direction.OUT)); 
			add(new Arc(1, lZeroB, Direction.IN)); 
			}}
		));
		
		
		return transitions;
	}
	
	
	
	public void Run()
	{
		System.out.println("Welcome to the Alin Coffee Machine! - PetriNet");
		int option;
		do 
		{
			PrintMenu();
			Scanner scanner = new Scanner(System.in);
			option = scanner.nextInt();
			
			switch(option)
			{
			case 1:
				Exec("dep5b");
				break;
			case 2:
				Exec("dep10b");
				break;
			case 3:
				Exec("iacafea15b");
				break;
			case 4:
				Exec("iacafea20b");
				break;
			}

		}while(option != 0);
	
	}
	
	
	private void PrintMenu()
	{
		System.out.println("Current coins: ");
		System.out.println(GetCurrentState());
		System.out.println("1. Insert 5 coins ");
		System.out.println("2. Insert 10 coins ");
		System.out.println("3. Buy expresso (15 coins) ");
		System.out.println("4. Buy cappuccino (20 coins) ");
		System.out.println("0. Exit ");	
	}
	

}
