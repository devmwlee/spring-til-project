package dev.mwlee.classforname;

public class Car {
	private final String name;
	private int position;

	public Car(String name, int position) {
		this.name = name;
		this.position = position;
	}

	public void move() {
		this.position++;
	}

	public int getPosition() {
		return position;
	}
}
