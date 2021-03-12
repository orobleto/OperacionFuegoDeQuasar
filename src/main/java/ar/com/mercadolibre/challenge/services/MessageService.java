package ar.com.mercadolibre.challenge.services;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import ar.com.mercadolibre.challenge.exceptions.MessageException;

@Service
public class MessageService {
	private Logger logger = LogManager.getLogger(getClass());

	public String getMessage(List<List<String>> messages) throws MessageException {
		Map<Integer, String> messageStructure = null;
		if (validateMessage(messages)) {
			messageStructure = messageStructure(messages);
		}

		return messageStructure.values().stream().reduce("", (e1, e2) -> e1.concat(e2 + " ")).trim();
	}

	/**
	 * Método para validar que los mensajes poseean la longitud correcta para
	 * analizarlos
	 * 
	 * @param messages Lista con los arreglos de los mensajes de los satelites
	 * @return boolean
	 * @throws MessageException
	 */
	private boolean validateMessage(List<List<String>> messages) throws MessageException {
		String errorMessage = "";
		if (null == messages || messages.isEmpty()) {
			errorMessage = "No existe un mensaje";
			logger.error(messages + " " + errorMessage);
			throw new MessageException(errorMessage);
		} else if (messages.size() < 2) {
			errorMessage = "Debe contener al menos 2 mensajes";
			logger.error(messages + " " + errorMessage);
			throw new MessageException(errorMessage);
		} else if (!equalLength(messages)) {
			errorMessage = "Los mensajes deben poseer la misma cantidad de palabras";
			logger.error(messages + " " + errorMessage);
			throw new MessageException(errorMessage);
		}
		return true;
	}

	/**
	 * Método para validar que los mensajes poseean la misma longitud
	 * 
	 * @param messages Lista con los arreglos de los mensajes de los satelites
	 * @return boolean
	 */
	private boolean equalLength(List<List<String>> messages) {
		Set<Integer> lengths = new HashSet<>();
		messages.forEach(e -> {
			lengths.add(e.size());
		});
		return lengths.size() == 1;
	}

	/**
	 * Método para validar y retornar la estructura del mensaje
	 * 
	 * @param messages Lista con los arreglos de los mensajes de los satelites
	 * @return Map
	 * @throws MessageException
	 */
	private Map<Integer, String> messageStructure(List<List<String>> messages) throws MessageException {
		Map<Integer, String> structure = new TreeMap<>();
		String errorMessage = "";
		for (List<String> e : messages) {
			for (int i = 0; i < e.size(); i++) {
				String word = e.get(i);
				// no se agregan al mapa ni elementos nulos ni vacios
				if (!((null == word) || (word.isEmpty()))) {
					// si contiene el elemento pero no es la misma palabra devolvemos el error
					if (structure.containsKey(i) && !word.equals(structure.get(i))) {
						errorMessage = "La estructura de los Mensajes no coincide";
						logger.error(messages + " " + errorMessage);
						throw new MessageException(errorMessage);
					}
					structure.putIfAbsent(i, word);
				}
			}
		}

		for (int i = 0; i < structure.size(); i++) {
			if (!structure.containsKey(i)) {
				errorMessage = "No se puede determinar el mensaje";
				logger.error(messages + " " + errorMessage);
				throw new MessageException(errorMessage);
			}
		}
		return structure;
	}

}
