package ar.com.mercadolibre.challenge.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.mercadolibre.challenge.entities.MessageResponse;
import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.entities.Satellite;
import ar.com.mercadolibre.challenge.exceptions.LocationException;
import ar.com.mercadolibre.challenge.exceptions.MessageException;
import ar.com.mercadolibre.challenge.exceptions.SatelliteException;
import ar.com.mercadolibre.challenge.interfaces.PositionRepository;

@Service
public class MessageSecretService {
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private LocationService locationService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SatelliteService satelliteService;

	/**
	 * Método para simplificar las clases y poder trabajar con ambos sevicios (topsecret y topsecret_split)
	 * 
	 * @param satellites lista
	 * @return MessageResponse
	 * @throws SatelliteException
	 * @throws LocationException
	 * @throws MessageException
	 */
	public MessageResponse getMessage(List<Satellite> satellites)
			throws SatelliteException, LocationException, MessageException {
		MessageResponse messageResponse = null;
		List<Position> positions = positionRepository.findAll();
		if (satelliteService.validateSatellites(satellites, positions)) {
			Position position = locationService.getLocation(positions, satellites);
			List<List<String>> messages = satellites.stream().map(e -> Arrays.asList(e.getMessage()))
					.collect(Collectors.toList());
			String message = messageService.getMessage(messages);

			messageResponse = new MessageResponse(position, message);

		}
		return messageResponse;
	}
}
