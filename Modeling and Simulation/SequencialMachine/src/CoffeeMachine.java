import java.util.HashMap;
import java.util.Scanner;

public class CoffeeMachine extends StateMachine{

	public CoffeeMachine() {
		super(Initialize());
	}
	
	protected static HashMap<Integer,HashMap<Integer, Integer>> Initialize()
	{
		HashMap<Integer, HashMap<Integer, Integer>> table = new HashMap<Integer,HashMap<Integer, Integer>>();
		table.put(0, new HashMap<Integer, Integer>());
		table.get(0).put(5, 5);
		table.get(0).put(10, 10);
		
		table.put(5, new HashMap<Integer, Integer>());
		table.get(5).put(5,10);
		table.get(5).put(10, 15);
		
		table.put(10, new HashMap<Integer, Integer>());
		table.get(10).put(5,15);
		table.get(10).put(10, 20);
		
		table.put(15, new HashMap<Integer, Integer>());
		table.get(15).put(5,20);
		table.get(15).put(10, 15);
		table.get(15).put(-15, 0);
		
		
		table.put(20, new HashMap<Integer, Integer>());
		table.get(20).put(-20, 0);
		table.get(20).put(-15, 5);
		
		return table;
	}
	
	public void Run()
	{
		System.out.println("Welcome to the Alin Coffee Machine!");
		int option;
		do 
		{
			PrintMenu();
			Scanner scanner = new Scanner(System.in);
			option = scanner.nextInt();
			
			switch(option)
			{
			case 1:
				TransitionTo(5);
				break;
			case 2:
				TransitionTo(10);
				break;
			case 3:
				TransitionTo(-15);
				System.out.println("You bought coffee!");
				break;
			case 4:
				TransitionTo(-20);
				System.out.println("You bought coffee!");
				break;
			}

		}while(option != 0);
	
	}
	
	
	private void PrintMenu()
	{
		System.out.println("Current coins: ");
		System.out.println(mCurrentState);
		System.out.println("1. Insert 5 coins ");
		System.out.println("2. Insert 10 coins ");
		System.out.println("3. Buy expresso (15 coins) ");
		System.out.println("4. Buy cappuccino (20 coins) ");
		System.out.println("0. Exit ");	
	}
	
}
