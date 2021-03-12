package ar.com.mercadolibre.challenge.services;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;

import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.entities.Satellite;
import ar.com.mercadolibre.challenge.exceptions.LocationException;

@Service
public class LocationService {
	private Logger logger = LogManager.getLogger(getClass());

	public Position getLocation(List<Position> positions, List<Satellite> satellites) throws LocationException {
		Position position = null;

		List<String> names = satellites.stream().map(e -> e.getName()).collect(Collectors.toList());

		ListIterator<Position> positionsEvaluate = positions.listIterator();
		while (positionsEvaluate.hasNext()) {
			Position positionAux = positionsEvaluate.next();
			if (!names.contains(positionAux.getName())) {
				positionsEvaluate.remove();
			}
		}

		double[][] positionsArray = transformToPositions(positions);
		double[] distancesArray = transformToDistances(satellites);

		if (validaLocation(positionsArray, distancesArray)) {
			NonLinearLeastSquaresSolver solver = new NonLinearLeastSquaresSolver(
					new TrilaterationFunction(positionsArray, distancesArray), new LevenbergMarquardtOptimizer());
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
	 * Método para transformar a una matriz las posiciones de los satelites
	 * 
	 * @param positions lista de posiciones
	 * @return double[][] posiciones
	 */
	private double[][] transformToPositions(List<Position> positions) {
		double[][] positionsArray = new double[positions.size()][2];

		for (int i = 0; i < positions.size(); i++) {
			positionsArray[i][0] = positions.get(i).getX();
			positionsArray[i][1] = positions.get(i).getY();
		}

		return positionsArray;
	}

	/**
	 * Método para transformar a un arreglo las distancias de los mensaje
	 * 
	 * @param satellites
	 * @return double[] distancias
	 */
	private double[] transformToDistances(List<Satellite> satellites) {
		double[] distancesArray = new double[satellites.size()];

		for (int i = 0; i < satellites.size(); i++) {
			distancesArray[i] = satellites.get(i).getDistance();
		}

		return distancesArray;
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
	private boolean validaLocation(double[][] positions, double[] distances) throws LocationException {
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
			errorMessage = "Cada posicion debe poseer su distancia para poder evaluar la [posicion:" + positions.length
					+ " ,distancia" + distances.length + "]";
			logger.error(errorMessage);
			throw new LocationException(errorMessage);
		}
		return true;
	}

}
