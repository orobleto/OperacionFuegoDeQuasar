package ar.com.mercadolibre.challenge.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import ar.com.mercadolibre.challenge.entities.Position;

public interface PositionRepository extends JpaRepository<Position, String> {

}
