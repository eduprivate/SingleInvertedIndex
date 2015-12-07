package br.com.nmonitor.search;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.nmonitor.analyzers.Tokenizer;
import br.com.nmonitor.io.ProcessTitleFile;
import br.com.nmonitor.processor.exceptions.NoContentException;
import br.com.nmonitor.processor.exceptions.ServerInitializationError;

public class SearchQueryTest {
	
	public static final String HTML_FILES_PATH = "./src/test/resources/";
	
	@Test
	public void queryTest() throws NoContentException {
		// Given
		ProcessTitleFile processTitleFile = new ProcessTitleFile();
		File file = new File(HTML_FILES_PATH + "10_titles.txt");
		List<String> titles = processTitleFile.processFile(file);
		Tokenizer tokenizer = new Tokenizer();
		Map<String, List<String>> indexMap = tokenizer.toquenize(titles);
		String query = "senhor";

		// When
		SearchQuery searchQuery = new SearchQuery(indexMap);
		List<String> queryResult = searchQuery.performQuery(query);

		// Then
		Assert.assertTrue(queryResult.size() > 0);
	}
	
	@Test
	public void queryFromIndexTest() throws NoContentException, ServerInitializationError {
		// Given
		File file = new File(HTML_FILES_PATH + "index.ix");
		String query = "senhor";

		// When
		SearchServer searchServer = new SearchServer(file);
		Map<String, List<String>> indexMap = searchServer.getMapIndex();
		SearchQuery searchQuery = new SearchQuery(indexMap);
		List<String> queryResult = searchQuery.performQuery(query);

		// Then
		Assert.assertTrue(queryResult.size() == 2);
	}
}
