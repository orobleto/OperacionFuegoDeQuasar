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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Satellite other = (Satellite) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
