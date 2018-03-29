package test;

import static org.junit.Assert.*;

import org.junit.Test;

import business.model.Client;
import business.services.ClientService;

public class ClientTest {

	ClientService cs = new ClientService();
	
	@Test
	public void test() {
		cs.insertClient("Ema Q", "CJ", "2314454522211", "Rasp 34", "074432932", "ema@yahoo.com");
		
		Client cl =  cs.findClientByName("Ema Q");
		
		assertEquals("Ema Q", cl.getName());
		assertEquals("CJ", cl.getCardNo());
		assertEquals("2314454522211", cl.GetCnp());
		assertEquals("Rasp", cl.getAddress());
		assertEquals("074432932", cl.getPhone());
		assertEquals("ema@yahoo.com", cl.getEmail());
	}

}
