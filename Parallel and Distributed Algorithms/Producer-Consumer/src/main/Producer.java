/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Producer implements Runnable {

	private List<Integer> criticalZone;
	// private Semaphore mutex;

	Producer(List<Integer> criticalZone/* , Semaphore mutex */) {
		this.criticalZone = criticalZone;
		// this.mutex = mutex;
	}

	@Override
	public void run() {
		while (true) {
			int producedElement = 1;
			addElement(producedElement);
			System.out.println("Produced element");
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}

	}

	private synchronized void addElement(Integer element) {
		this.criticalZone.add(1);
	}

}
