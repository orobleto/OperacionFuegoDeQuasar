package ar.com.mercadolibre.challenge.entities;

import java.io.Serializable;
import java.util.Arrays;

public class Satellite implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private Float distance;
	private String[] message;

	public Satellite() {
		super();
	}

	public Satellite(String name, Float distance, String[] message) {
		super();
		this.name = name;
		this.distance = distance;
		this.message = message;
	}

	@Override
	public String toString() {
		return "Satellite [name=" + name + ", distance=" + distance + ", message=" + Arrays.toString(message) + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getDistance() {
		return distance;
	}

	public void setDistance(Float distance) {
		this.distance = distance;
	}

	public String[] getMessage() {
		return message;
	}

	public void setMessage(String[] message) {
		this.message = message;
	}

}
