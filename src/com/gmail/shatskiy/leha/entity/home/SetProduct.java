package com.gmail.shatskiy.leha.entity.home;

import java.util.Random;

import com.gmail.shatskiy.leha.entity.lesson.SharedResource;

public class SetProduct implements Runnable {
	private Stock stock;
	private Random rand = new Random();
	private int delivered = 0;

	public SetProduct(Stock stock) {
		this.stock = stock;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			int element = rand.nextInt(100);
			int sleep = rand.nextInt(1000);
			try {
				setProduct(element);
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			delivered = delivered + element;
		}
	}

	public void setProduct(int element) throws InterruptedException {
		synchronized (stock) {
			if (stock.thereIs + element > stock.STOCK_SIZE) {
				stock.wait();
			} else {
				stock.setElement(element);
				System.out.println("Грузовик привёз: " + element + " | на складе: " + stock.thereIs);
				stock.notifyAll();
			}
		}
	}
}
