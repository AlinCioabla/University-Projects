/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Consumer implements Runnable {

	private ArrayList<Integer> criticalZone;
	private Object condProd;
	private Object condCons;

	Consumer(ArrayList<Integer> criticalZone, Object condProd, Object condCons) {
		this.criticalZone = criticalZone;
		this.condProd = condProd;
		this.condCons = condCons;
	}

	@Override
	public void run() {
		while (true) {
			while (isQueueEmpty()) {
				try {
					synchronized (condCons) {
						condCons.wait();
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			int elementToConsume = getElement();
			synchronized (condProd) {
				condProd.notifyAll();
			}
			consume(elementToConsume);
		}
	}

	private synchronized int getElement() {
		int elementToConsume = this.criticalZone.get(0);
		this.criticalZone.remove(0);

		return elementToConsume;
	}

	private synchronized boolean isQueueEmpty() {
		return criticalZone.size() == 0;
	}

	private void consume(int element) {
		System.out.println("Consumed element");
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
