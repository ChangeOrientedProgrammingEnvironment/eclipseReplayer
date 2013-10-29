package edu.oregonstate.cope.clientRecorder;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.oregonstate.cope.tests.util.StubFileProvider;

public class RecorderPropertiesTest {

	private RecorderProperties properties;
	private StubFileProvider fileProvider;

	@Before
	public void setup() {
		fileProvider = new StubFileProvider();
		properties = new RecorderProperties(fileProvider);
	}

	@Test
	public void testAddNull() {
		properties.addProperty(null, null);
		assertNull(properties.getProperty(null), null);
		assertTrue(fileProvider.isCurrentFileEmpty());
	}
	
	@Test
	public void testAddEmpty() throws Exception {
		properties.addProperty("", "");
		assertTrue(fileProvider.isCurrentFileEmpty());
	}
	
	@Test
	public void testAddOneKey() throws Exception {
		properties.addProperty("k", "v");
		testForKey("k", "v");
	}

	@Test
	public void testAddTwoKeys() throws Exception {
		properties.addProperty("k1", "v1");
		properties.addProperty("k2", "v2");
		
		testForKey("k1", "v1");
		testForKey("k2", "v2");
	}

	private void testForKey(String key, String value) {
		assertEquals(value, properties.getProperty(key));
		assertEquals(value, new RecorderProperties(fileProvider).getProperty(key));
	}
}
