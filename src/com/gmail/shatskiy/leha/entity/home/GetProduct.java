package com.gmail.shatskiy.leha.entity.home;

import java.util.Random;

public class GetProduct implements Runnable {
	private Stock stock;
	private Random rand = new Random();
	private int delivered = 0;

	public GetProduct(Stock stock) {
		this.stock = stock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 5; i++) {
			int element = rand.nextInt(100);
			int sleep = rand.nextInt(1000);
			try {
				getProduct(element);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			delivered = delivered + element;
		}
	}

	public void getProduct(int element) throws InterruptedException {
		synchronized (stock) {
			if (stock.thereIs < element) {
				stock.wait();
			} else {
				stock.getElement(element);
				System.out.println("������� ������: ываыва" + element + " | �� ������: " + stock.thereIs);
				stock.notifyAll();
			}
		}
	}
}
