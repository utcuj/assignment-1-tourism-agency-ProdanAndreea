package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import business.model.History;
import business.services.HistoryService;

public class HistoryTest {
	HistoryService hs = new HistoryService();
	
	@Test
	public void testInsertFind() {	
		hs.insertHistory(1, "inserted a new data");
	
		History hist = hs.findHistorybyId(1);
		
		assertEquals(1, hist.getIdUser());
		assertEquals("inserted a new data", hist.getChange());
	}
	
	@Test
	public void testFindActivities() {
		ArrayList<History> arr = hs.findActivities("1", "2005-12-12", "2018-09-09");
		for (History h: arr) {
			System.out.println(h.getChange() + " " + h.getDate());
		}
	}

}





