package jUnit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import antiSpamUI.Extensions;

class ExtensionsTests {

	@Test
	void testToTableModel() {
		new Extensions();
		Map<?,?> map = new HashMap<String,String>();
		assertTrue(Extensions.toTableModel(map, false) instanceof DefaultTableModel);
	}
	
}
