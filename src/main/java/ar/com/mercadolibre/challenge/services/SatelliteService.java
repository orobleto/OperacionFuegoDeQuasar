package ar.com.mercadolibre.challenge.services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ar.com.mercadolibre.challenge.entities.Satellite;

@Service
public class SatelliteService {
	private Logger logger = LogManager.getLogger(getClass());

	/**
	 * Método para validar al Json y armar los objetos Satellite ver
	 * {@link ar.com.mercadolibre.challenge.entities #Satellite}
	 * 
	 * @param satelliteRaw
	 * @return List de satelites
	 */
	public List<Satellite> validateRequestSatellite(String satelliteRaw) {
		List<Satellite> satellites = new ArrayList<>();

		JSONObject jsonObject = new JSONObject(satelliteRaw);

		JSONArray satellitesJsonArray = jsonObject.getJSONArray("satellites");

		for (int i = 0; i < satellitesJsonArray.length(); i++) {
			JSONObject jsonObjectAux = satellitesJsonArray.getJSONObject(i);

			String name = jsonObjectAux.getString("name");
			Float distance = jsonObjectAux.getFloat("distance");
			JSONArray messageAux = jsonObjectAux.getJSONArray("message");
			String[] message = new String[messageAux.length()];
			for (int j = 0; j < messageAux.length(); j++) {
				Object ms = messageAux.get(j);
				message[j] = !(null == ms) ? ms.toString() : "";
			}
			satellites.add(new Satellite(name, distance, message));
		}

		logger.info("Satellites[]:" + satellites);

		return satellites;
	}

}
