package ar.com.mercadolibre.challenge.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.interfaces.PositionRepository;

@Controller
public class InitializationDataBase implements CommandLineRunner {
	@Autowired
	private PositionRepository positionRepository;

	@Override
	public void run(String... args) throws Exception {
		Position Kenobi = new Position("Kenobi", -500f, -200f);
		Position Skywalker = new Position("Skywalker", 100f, -100f);
		Position Sato = new Position("Sato", 500f, 100f);
		positionRepository.save(Kenobi);
		positionRepository.save(Skywalker);
		positionRepository.save(Sato);
	}

}
