package ar.com.mercadolibre.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.mercadolibre.challenge.entities.Position;
import ar.com.mercadolibre.challenge.entities.Satellite;
import ar.com.mercadolibre.challenge.exceptions.LocationException;
import ar.com.mercadolibre.challenge.services.LocationService;

@SpringBootTest
public class LocationServiceTest {

	@Autowired
	private LocationService locationService;

	@Test
	public void detecLocationWithMinimum() throws LocationException {

		Position Kenobi = new Position("Kenobi", -500f, -200f);
		Position Skywalker = new Position("Skywalker", 100f, -100f);

		List<Position> positions = new ArrayList<>();
		positions.add(Kenobi);
		positions.add(Skywalker);

		List<Satellite> satellites = new ArrayList<>();
		Satellite KenobiS = new Satellite("Kenobi", 100f, new String[] { "este", "", "", "mensaje", "" });
		Satellite SkywalkerS = new Satellite("Skywalker", 115.5f, new String[] { "este", "es", "", "", "secreto" });

		satellites.add(KenobiS);
		satellites.add(SkywalkerS);

		Position position = locationService.getLocation(positions, satellites);
		Position positionToValidate = new Position("NavePortacargaImperial", -215.0261f, -152.50354f);

		assertEquals(position.getX(), positionToValidate.getX());
		assertEquals(position.getY(), positionToValidate.getY());

	}

	@Test
	public void detecLocationWithMore2() throws LocationException {

		Position Kenobi = new Position("Kenobi", -500f, -200f);
		Position Skywalker = new Position("Skywalker", 100f, -100f);
		Position Sato = new Position("Sato", 500f, 100f);

		List<Position> positions = new ArrayList<>();
		positions.add(Kenobi);
		positions.add(Skywalker);
		positions.add(Sato);

		List<Satellite> satellites = new ArrayList<>();
		Satellite KenobiS = new Satellite("Kenobi", 100f, new String[] { "este", "", "", "mensaje", "" });
		Satellite SkywalkerS = new Satellite("Skywalker", 115.5f, new String[] { "este", "es", "", "", "secreto" });
		Satellite SatoS = new Satellite("Sato", 142.7f, new String[] { "", "", "un", "", "" });
		satellites.add(KenobiS);
		satellites.add(SkywalkerS);
		satellites.add(SatoS);

		Position position = locationService.getLocation(positions, satellites);
		Position positionToValidate = new Position("NavePortacargaImperial", -58.31525f, -69.551414f);

		assertEquals(position.getX(), positionToValidate.getX());
		assertEquals(position.getY(), positionToValidate.getY());

	}

}
