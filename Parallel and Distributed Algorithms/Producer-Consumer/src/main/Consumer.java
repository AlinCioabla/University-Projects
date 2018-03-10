/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Consumer implements Runnable {

	private ArrayList<Integer> criticalZone;
	private Semaphore semFull;
	private Semaphore semFree;

	Consumer(ArrayList<Integer> criticalZone, Semaphore semFree, Semaphore semFull) {
		this.criticalZone = criticalZone;
		this.semFull = semFull;
		this.semFree = semFree;
	}

	@Override
	public void run() {
		while (true) {
			int elementToConsume = 0;
			try {
				semFull.acquire();
				elementToConsume = getElement();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semFree.release();
				consume();
			}
		}
	}

	private synchronized int getElement() {
		int element = this.criticalZone.get(0);
		this.criticalZone.remove(0);
		return element;
	}

	private void consume() {
		System.out.println("Consumed element");
		try {
			Thread.sleep(500);
		} catch (InterruptedException ex) {
			Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
