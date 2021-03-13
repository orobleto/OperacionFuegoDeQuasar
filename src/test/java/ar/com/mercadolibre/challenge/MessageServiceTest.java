package ar.com.mercadolibre.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.mercadolibre.challenge.exceptions.MessageException;
import ar.com.mercadolibre.challenge.services.MessageService;

@SpringBootTest
public class MessageServiceTest {

	@Autowired
	private MessageService messageService;

	@Test
	public void detecMessageWithMinimum() throws MessageException {

		String[] Kenobi = { "este", "", "", "mensaje", "" };
		String[] Skywalker = { "", "es", "un", "", "secreto" };

		List<List<String>> messages = new ArrayList<>();
		messages.add(Arrays.asList(Kenobi));
		messages.add(Arrays.asList(Skywalker));

		String message = messageService.getMessage(messages);
		String messageToValidate = "este es un mensaje secreto";

		assertEquals(message, messageToValidate);
	}

	@Test
	public void detecMessageWithMore2() throws MessageException {
		String[] Kenobi = { "este", "", "", "mensaje", "" };
		String[] Skywalker = { "este", "es", "", "", "secreto" };
		String[] Sato = { "", "", "un", "", "" };

		List<List<String>> messages = new ArrayList<>();
		messages.add(Arrays.asList(Kenobi));
		messages.add(Arrays.asList(Skywalker));
		messages.add(Arrays.asList(Sato));

		String message = messageService.getMessage(messages);
		String messageToValidate = "este es un mensaje secreto";

		assertEquals(message, messageToValidate);
	}

}
