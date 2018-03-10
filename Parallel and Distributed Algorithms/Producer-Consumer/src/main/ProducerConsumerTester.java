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
public class ProducerConsumerTester {

	Semaphore semFree, semFull;
	ArrayList<Integer> criticalZone;

	ProducerConsumerTester(int maxElements) {
		semFree = new Semaphore(maxElements);
		semFull = new Semaphore(0);
		criticalZone = new ArrayList<Integer>();
	}

	public void start() throws InterruptedException {
		Thread consumer1 = new Thread(new Consumer(criticalZone, semFree, semFull));
		Thread producer1 = new Thread(new Producer(criticalZone, semFree, semFull));
		Thread consumer2 = new Thread(new Consumer(criticalZone, semFree, semFull));
		Thread producer2 = new Thread(new Producer(criticalZone, semFree, semFull));
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
