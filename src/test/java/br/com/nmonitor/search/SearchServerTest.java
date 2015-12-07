package br.com.nmonitor.search;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.nmonitor.processor.exceptions.NoContentException;
import br.com.nmonitor.processor.exceptions.ServerInitializationError;

public class SearchServerTest {
	
	public static final String HTML_FILES_PATH = "./src/test/resources/";
	
	@Test
	public void searchServerTest() throws NoContentException, ServerInitializationError {
		// Given
		File file = new File(HTML_FILES_PATH + "index.ix");

		// When
		SearchServer searchServer = new SearchServer(file);
		Map<String, List<String>> indexMap = searchServer.getMapIndex();
		
		// Then
		Assert.assertTrue(indexMap.size() > 0);
	}
}
