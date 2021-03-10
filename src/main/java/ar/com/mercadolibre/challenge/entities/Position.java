package ar.com.mercadolibre.challenge.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "satelliteposition")
public class Position implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	private Float x;
	private Float y;

	public Position() {
		super();
	}

	/**
	 * Constructor que recibe la posición en eje x , y
	 * 
	 * @param name nombre del satelite
	 * @param x
	 * @param y
	 */
	public Position(String name, Float x, Float y) {
		super();
		this.name = name;
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor que recibe un arreglo con las coordenadas
	 * 
	 * @param name        nombre del satelite
	 * @param coordinates en el indice [0] enviar posición (x) en el indice [1]
	 *                    enviar posición (y)
	 */
	public Position(String name, Float[] coordinates) {
		super();
		this.name = name;
		x = coordinates[0];
		y = coordinates[1];
	}

	@Override
	public String toString() {
		return "Position [name=" + name + ", x=" + x + ", y=" + y + "]";
	}

	/**
	 * Método para simplificar el uso de la librería Trilateración
	 * 
	 * @return Double[] con las coordenadas [x,y]
	 */
	public double[] positions() {
		return new double[] { x, y };
	}

	public Float getX() {
		return x;
	}

	public void setX(Float x) {
		this.x = x;
	}

	public Float getY() {
		return y;
	}

	public void setY(Float y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
