/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author user
 */
public class Producer implements Runnable {

	private ArrayList<Integer> criticalZone;
	private Semaphore semFree;
	private Semaphore semFull;

	Producer(ArrayList<Integer> criticalZone, Semaphore semFree, Semaphore semFull) {
		this.criticalZone = criticalZone;
		this.semFree = semFree;
		this.semFull = semFull;
	}

	@Override
	public void run() {
		while (true) {
			int producedElement = produce();
			try {
				semFree.acquire();
				addElement(producedElement);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				semFull.release();
			}
		}

	}

	private int produce() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Produced element");
		return 1;
	}

	private synchronized void addElement(Integer element) {
		this.criticalZone.add(element);
		System.out.println("Added element");
	}

}
