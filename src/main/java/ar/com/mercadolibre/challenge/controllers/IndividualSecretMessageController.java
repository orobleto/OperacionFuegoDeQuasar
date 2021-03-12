package ar.com.mercadolibre.challenge.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.mercadolibre.challenge.entities.MessageResponse;
import ar.com.mercadolibre.challenge.entities.Satellite;
import ar.com.mercadolibre.challenge.exceptions.LocationException;
import ar.com.mercadolibre.challenge.exceptions.MessageException;
import ar.com.mercadolibre.challenge.exceptions.SatelliteException;
import ar.com.mercadolibre.challenge.services.MessageSecretService;

@RestController
@CrossOrigin(origins = "*")

public class IndividualSecretMessageController {

	@Autowired
	private MessageSecretService messageSecretService;

	private Logger logger = LogManager.getLogger(getClass());

	List<Satellite> satellites = new ArrayList<>();

	@RequestMapping(path = "/topsecret_split/{name}", consumes = {
			MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, produces = {
					MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8" }, method = { RequestMethod.GET,
							RequestMethod.POST })
	public ResponseEntity<Object> topsecretSplit(@PathVariable("name") String name, @RequestBody Satellite satellite) {
		satellite.setName(name);
		satellites.remove(satellite);
		satellites.add(satellite);

		try {
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
