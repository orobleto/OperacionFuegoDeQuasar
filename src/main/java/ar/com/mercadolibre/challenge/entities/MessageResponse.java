package ar.com.mercadolibre.challenge.entities;

public class MessageResponse {
	private Position position;
	private String message;

	public MessageResponse() {
		super();
	}

	public MessageResponse(Position position, String message) {
		super();
		this.position = position;
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessageResponse [position=" + position + ", message=" + message + "]";
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
