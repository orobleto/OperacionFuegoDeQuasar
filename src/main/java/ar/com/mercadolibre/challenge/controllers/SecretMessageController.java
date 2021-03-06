package ar.com.mercadolibre.challenge.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.challenge.entities.MessageResponse;
import ar.com.mercadolibre.challenge.entities.Satellite;
import ar.com.mercadolibre.challenge.exceptions.LocationException;
import ar.com.mercadolibre.challenge.exceptions.MessageException;
import ar.com.mercadolibre.challenge.exceptions.SatelliteException;
import ar.com.mercadolibre.challenge.services.MessageSecretService;
import ar.com.mercadolibre.challenge.services.SatelliteService;

@RestController
@CrossOrigin(origins = "*")
public class SecretMessageController {

	@Autowired
	private SatelliteService satelliteService;
	@Autowired
	private MessageSecretService messageSecretService;

	private Logger logger = LogManager.getLogger(getClass());

	@PostMapping(path = "/topsecret", consumes = { MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, produces = {
			MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" })
	public ResponseEntity<Object> topSecret(RequestEntity<String> jsonRaw) {

		try {
			List<Satellite> satellites = satelliteService.validateRequestSatellite(jsonRaw.getBody());

			MessageResponse messageResponse = messageSecretService.getMessage(satellites);

			return ResponseEntity.status(HttpStatus.OK).body(messageResponse);

		} catch (SatelliteException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (LocationException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (MessageException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

	}
}
