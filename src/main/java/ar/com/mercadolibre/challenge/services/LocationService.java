package ar.com.mercadolibre.challenge.services;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.exceptions.LocationException;

public class LocationService {
	private Logger logger = LogManager.getLogger(getClass());

	public Position getLocation(double[][] positions, double[] distances) throws LocationException {
		Position position = null;
		if (validaLocation(positions, distances)) {
			NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
					new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
			double[] points = solver.solve().getPoint().toArray();
			Float[] coordinates = new Float[points.length];

			for (int i = 0; i < points.length; i++) {
				coordinates[i] = (float) points[i];
			}
			position = new Position("NavePortacargaImperial", coordinates);
		}
		logger.info("Envio de Localizacion: " + position);
		return position;
	}

	/**
	 * Método para validar que los arreglos poseean la longitud correcta para
	 * analizarlos
	 * 
	 * @param positions arreglo con las posiciones de los satelites
	 * @param distances arreglo con la distancia de los satelites
	 * @return boolean
	 * @throws LocationException
	 */
	public boolean validaLocation(double[][] positions, double[] distances) throws LocationException {
		String errorMessage = "";
		if (null == positions || positions.length == 0 || null == distances || distances.length == 0) {
			errorMessage = "No deben estar vacias las posiciones ni la distancias";
			logger.error(errorMessage);
			throw new LocationException(errorMessage);
		} else if (positions.length < 2 || distances.length < 2) {
			errorMessage = "Debe contener al menos 2 posiciones y dos distancias";
			logger.error(errorMessage);
			throw new LocationException(errorMessage);
		} else if (distances.length != positions.length) {
			errorMessage = "Cada posicion debe poseer su distancia para poder evaluarla [posicion:" + positions.length
					+ " ,distancia" + distances.length + "]";
			logger.error(errorMessage);
			throw new LocationException(errorMessage);
		}
		return true;
	}

}
