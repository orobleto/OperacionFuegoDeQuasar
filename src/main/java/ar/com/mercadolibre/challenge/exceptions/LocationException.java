package ar.com.mercadolibre.challenge.exceptions;

public class LocationException extends Exception{

	private static final long serialVersionUID = 1L;

	public LocationException(String errorMessage){
        super(errorMessage);
    }
}