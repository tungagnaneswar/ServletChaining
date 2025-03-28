package com.kodnest.designpattern.singletonpattern;

public class IndiaApp {

	public static void main(String[] args) {

		PrimeMinister pm =PrimeMinister.getObject();
		System.out.println(pm);
		PrimeMinister pm2 =PrimeMinister.getObject();
		System.out.println(pm2);
		
	}
}
