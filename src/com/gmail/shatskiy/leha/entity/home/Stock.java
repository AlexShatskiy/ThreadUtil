package com.gmail.shatskiy.leha.entity.home;

public class Stock {
	public final int STOCK_SIZE = 100;
	int thereIs = 0;

	public void setElement(int element) {
		thereIs = thereIs + element;
	}

	public void getElement(int element) {
		thereIs = thereIs - element;
	}

	public int getThereIs() {
		return thereIs;
	}
}
