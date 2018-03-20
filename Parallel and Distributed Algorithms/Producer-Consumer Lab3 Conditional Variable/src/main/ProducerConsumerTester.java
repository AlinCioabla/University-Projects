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
public class ProducerConsumerTester {

	private ArrayList<Integer> criticalZone;
	private Object condProd;
	private Object condCons;
	private final int maxElements = 10;

	ProducerConsumerTester(int maxElements) {
		criticalZone = new ArrayList<Integer>();
		condProd = new Object();
		condCons = new Object();
	}

	public void start() throws InterruptedException {
		Thread consumer1 = new Thread(new Consumer(criticalZone, condProd, condCons));
		Thread producer1 = new Thread(new Producer(criticalZone, condProd, condCons, maxElements));
		Thread consumer2 = new Thread(new Consumer(criticalZone, condProd, condCons));
		Thread producer2 = new Thread(new Producer(criticalZone, condProd, condCons, maxElements));
		consumer1.start();
		producer1.start();
		consumer2.start();
		producer2.start();

		consumer1.join();
		producer1.join();
		consumer2.join();
		producer2.join();

	}

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumerTester pc = new ProducerConsumerTester(5);
		pc.start();
	}

}
