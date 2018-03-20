/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.ArrayList;

/**
 *
 * @author user
 */
public class Producer implements Runnable {

	private ArrayList<Integer> criticalZone;
	private Object condProd;
	private Object condCons;
	private int maxElements;

	Producer(ArrayList<Integer> criticalZone, Object condProd, Object condCons, int maxElements) {
		this.criticalZone = criticalZone;
		this.condProd = condProd;
		this.condCons = condCons;
		this.maxElements = maxElements;
	}

	@Override
	public void run() {
		while (true) {
			int producedElement = produce();
			while (!isQueueFull()) {
				try {
					synchronized (condProd) {
						this.condProd.wait();
					}

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			addElement(producedElement);
			synchronized (condCons) {
				condCons.notifyAll();
			}

		}
	}

	private synchronized boolean isQueueFull() {
		return criticalZone.size() == maxElements;
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
