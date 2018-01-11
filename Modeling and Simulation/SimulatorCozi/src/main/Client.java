
package main;


import java.util.ArrayList;


public class Client {
	
	
	private int clientNumber;
	private ArrayList<String> operationType;
	private int arrivalTime;
	
	public Client(int arrivalTime,int clientNumber,ArrayList<String> type)
	{
		this.clientNumber = clientNumber;;
		operationType = type;
		this.arrivalTime = arrivalTime;
	}

	public int getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(int number) {
		this.clientNumber = number;
	}

	public ArrayList<String> getOperationType() {
		return operationType;
	}

	public void setOperationType(ArrayList<String> operationType) {
		this.operationType = operationType;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

}

