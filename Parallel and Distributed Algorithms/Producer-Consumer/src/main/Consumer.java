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
public class Consumer implements Runnable {

	private List<Integer> criticalZone;
	// private Semaphore mutex;

	Consumer(List<Integer> criticalZone /* , Semaphore mutex */) {
		this.criticalZone = criticalZone;
		// this.mutex = mutex;
	}

	@Override
	public void run() {
		while (true) {
			if (consume()) {
				System.out.println("Consumed element");
			} else {
				System.out.println("Cannot consume - zone is empty. Skipping...");
			}

			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	private synchronized boolean consume() {

		if (!this.criticalZone.isEmpty()) {
			this.criticalZone.remove(this.criticalZone.size() - 1);
			return true;
		}
		return false;
	}
}
