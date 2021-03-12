package ar.com.mercadolibre.challenge.exceptions;

public class SatelliteException extends Exception{

	private static final long serialVersionUID = 1L;

	public SatelliteException(String errorMessage){
        super(errorMessage);
    }
}