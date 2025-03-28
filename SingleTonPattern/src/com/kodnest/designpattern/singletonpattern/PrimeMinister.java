package com.kodnest.designpattern.singletonpattern;

public class PrimeMinister {

	private static PrimeMinister pm = null; // field

	private PrimeMinister() {

	}

	static public PrimeMinister getObject() {
		if (pm == null) {
			pm = new PrimeMinister();
		}
		return pm;
	}
}
