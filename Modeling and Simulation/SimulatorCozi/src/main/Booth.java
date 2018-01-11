
package main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class Booth {
	
	private int boothNumber;
	private ArrayList<String> operationType;
	private Queue<Client> clientQueue = new LinkedList<Client>();
	
	
	public Booth(int number,ArrayList<String> type,Queue<Client> queue)
	{
		boothNumber = number;
		operationType = type;
		clientQueue = queue;
	}
	
	public int calculateOperationsDuration(Client client)
	{	
		Client cl = client;

		int totalTime = cl.getArrivalTime();
		
		for (int i = 0; i < operationType.size(); i++) {
			if (operationType.get(i).equals("PlataFactura")) {
				for (int j = 0; j < cl.getOperationType().size(); j++) {
					if ((cl.getOperationType().get(j).equals("PlataFactura")))
						totalTime += 2;
				}
			} else if (operationType.get(i).equals("TrimitereColet")) {
				for (int j = 0; j < cl.getOperationType().size(); j++) {
					if ((cl.getOperationType().get(j).equals("TrimitereColet")))
						totalTime += 5;
				}
			} else if (operationType.get(i).equals("PrimireColet")) {
				for (int j = 0; j < cl.getOperationType().size(); j++) {
					if ((cl.getOperationType().get(j).equals("PrimireColet")))
						totalTime += 3;
				}
		}
                }
		return totalTime;
        
        }
	public int getBoothNumber()
        {
		return boothNumber;
	}

	public void setBoothNumber(int number) {
		this.boothNumber = number;
	}

	public ArrayList<String> getOperationType() {
		return operationType;
	}

	public void setOperationType(ArrayList<String> operationType) {
		this.operationType = operationType;
	}

	public Queue<Client> getClientQueue() {
		return clientQueue;
	}

	public void setClientQueue(Queue<Client> clients) {
		this.clientQueue = clients;
	}
	
	
}
