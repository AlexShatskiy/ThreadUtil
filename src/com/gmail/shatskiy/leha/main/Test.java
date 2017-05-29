package com.gmail.shatskiy.leha.main;

import com.gmail.shatskiy.leha.entity.home.GetProduct;
import com.gmail.shatskiy.leha.entity.home.SetProduct;
import com.gmail.shatskiy.leha.entity.home.Stock;

public class Test {

	public static void main(String[] args) {
		Stock stock = new Stock();
		SetProduct set = new SetProduct(stock);
		GetProduct get = new GetProduct(stock);
		
		Thread thS1 = new Thread(set);
		thS1.start();
		Thread thS2 = new Thread(set);
		thS2.start();
		Thread thS3 = new Thread(set);
		thS3.start();

		Thread thG1 = new Thread(get);
		thG1.start();
		Thread thG2 = new Thread(get);
		thG2.start();
	}
}
