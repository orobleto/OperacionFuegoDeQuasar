package ar.com.mercadolibre.challenge.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class StatusController {
	
	
	@RequestMapping(path = "/status")
	public String statusOK() {
		return "Status OK";
	}

}
