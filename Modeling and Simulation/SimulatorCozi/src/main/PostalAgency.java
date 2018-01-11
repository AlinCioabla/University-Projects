
package main;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


public class PostalAgency {

	private ArrayList<Booth> booths;
	
	public PostalAgency()
	{
		booths = new ArrayList<Booth>();
		Queue<Client> queue1 = new LinkedList<Client>();
		Queue<Client> queue2 = new LinkedList<Client>();
		
		
		ArrayList<String> client1Op = new ArrayList<String>();
		client1Op.add("PlataFactura");
		client1Op.add("TrimitereColet");
		
		ArrayList<String> client2Op = new ArrayList<String>();
		client2Op.add("TrimitereColet");
		
		ArrayList<String> client3Op = new ArrayList<String>();
		client3Op.add("TrimitereColet");
		
		ArrayList<String> client4Op = new ArrayList<String>();
		client4Op.add("PlataFactura");
		client4Op.add("TrimitereColet");
		
		ArrayList<String> client5Op = new ArrayList<String>();
		client4Op.add("PrimireColet");
		
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		queue1.add(new Client(randomInt,1,client1Op));
		
		randomInt = randomGenerator.nextInt(100);
		queue1.add(new Client(randomInt,2,client2Op));
		
		randomInt = randomGenerator.nextInt(100);
		queue1.add(new Client(randomInt,3,client3Op));
		
		randomInt = randomGenerator.nextInt(100);
		queue1.add(new Client(randomInt,4,client4Op));
		
		randomInt = randomGenerator.nextInt(100);
		queue1.add(new Client(randomInt,5,client5Op));
		
		randomInt = randomGenerator.nextInt(100);
		queue1.add(new Client(randomInt,6,client5Op));
		
		
		queue2.add(new Client(randomInt,7,client1Op));
		
		randomInt = randomGenerator.nextInt(100);
		queue2.add(new Client(randomInt,8,client2Op));
		
		randomInt = randomGenerator.nextInt(100);
		queue2.add(new Client(randomInt,9,client3Op));

		booths.add(new Booth(1,client1Op,queue1));
		booths.add(new Booth(2,client1Op,queue2));
	}
	
	public ArrayList<Booth> checkBooths()
	{
		ArrayList<Integer> clients = new ArrayList<Integer>();
		
		for(int i =0;i<booths.size();i++)
		{
			clients.add(booths.get(i).getClientQueue().size());
		}
		
		for(int i=0;i<clients.size();i++)
		{
			for(int j =0;j<booths.size();j++)
			{
				if(clients.get(i) < (booths.get(j).getClientQueue().size()+2) && (i!=j) && (booths.get(i).getBoothNumber() != booths.get(j).getBoothNumber()))
				{
					for(int k = 0;k < booths.get(i).getOperationType().size();k++)
					{
						if(booths.get(j).getClientQueue().element().getOperationType().equals(booths.get(j).getOperationType()))
						{
							System.out.println("Client id :"+booths.get(j).getClientQueue().element().getClientNumber()+" a was moved to booth  "+booths.get(i).getBoothNumber());
							booths.get(i).getClientQueue().add(booths.get(j).getClientQueue().poll());
						}
						
					}
				}
			}
		}
		
		for(int i = 0;i<booths.size();i++)
		{
			for(int j = 0; j< booths.get(i).getOperationType().size();j++)
			{
				for(int k =0;k<booths.get(i).getClientQueue().element().getOperationType().size();k++)
				{
					if(!booths.get(i).getOperationType().get(j).equals(booths.get(i).getClientQueue().element().getOperationType().get(k)))
					{
						if((!booths.get(i).getOperationType().equals(booths.get(i).getClientQueue().element().getOperationType())&&(booths.get(i).getBoothNumber()!=booths.get(k).getBoothNumber())))
						{
							System.out.println("Client id :"+booths.get(j).getClientQueue().element().getClientNumber()+"was moved to booth : "+booths.get(i).getBoothNumber());
							booths.get(i).getClientQueue().add(booths.get(j).getClientQueue().poll());
						}
					}
				}
			}
		}
		
		return booths;
	}
	
	public void leaveAgency()
	{
		int time = 0;
		
		for(int i = 0; i<booths.size();i++)
		{
			int j = 0;
			while(!booths.get(i).getClientQueue().isEmpty())
			{
				time = booths.get(i).calculateOperationsDuration(booths.get(i).getClientQueue().element());
				System.out.println("Client id : "+booths.get(i).getClientQueue().element().getClientNumber()+" stayed in the agency for : "+time+" minutes and waited at booth : "+booths.get(i).getBoothNumber());
				booths.get(i).getClientQueue().remove();
				j++;
				
			}
		}	
	}
	
}
