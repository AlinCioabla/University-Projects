/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author user
 */
public class ProducerConsumerTester {

	// Semaphore mutex;
	List<Integer> criticalZone;

	ProducerConsumerTester() {
		// mutex = new Semaphore(1);
		criticalZone = new LinkedList<Integer>();
	}

	public void start() throws InterruptedException {
		Thread consumer = new Thread(new Consumer(criticalZone));
		Thread producer = new Thread(new Producer(criticalZone));
		consumer.start();
		producer.start();
		consumer.join();
		producer.join();
	}

	public static void main(String[] args) throws InterruptedException {
		ProducerConsumerTester pc = new ProducerConsumerTester();
		pc.start();
	}

}
