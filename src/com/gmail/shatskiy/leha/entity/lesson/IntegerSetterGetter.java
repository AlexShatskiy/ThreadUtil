package com.gmail.shatskiy.leha.entity.lesson;

import java.util.Random;

public class IntegerSetterGetter extends Thread {

	private SharedResource resource;
	private volatile boolean run;

	private Random rand = new Random();

	public IntegerSetterGetter(String name, SharedResource resource) {
		super(name);
		this.resource = resource;
		run = true;
	}

	public void stopThread() {
		run = false;
	}

	@Override
	public void run() {
		int action;
		try {
			action = rand.nextInt(1000);
			if (action % 2 == 0) {
				getIntegerFromResource();
			} else {
				setIntegerFromResource();
			}
			System.out.println("Thread " + getName() + " finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void getIntegerFromResource() throws InterruptedException {
		Integer number;
		synchronized (resource) {
			System.out.println("Thread " + getName() + " ����� ������� �����");
			number = resource.getElement();
			while (number == null) {
				System.out.println("Thread " + getName() + " ��� ���� ������� ����������");
				resource.wait();
				System.out.println("Thread " + getName() + " ���������� ������");
				number = resource.getElement();
			}
			System.out.println("Thread " + getName() + " ����� ����� " + number);
		}

	}

	private void setIntegerFromResource() {
		Integer number = rand.nextInt(500);
		synchronized (resource) {
			resource.setElement(number);
			System.out.println("Thread " + getName() + " ������� ����� " + number);
			resource.notify();
		}
	}

}
