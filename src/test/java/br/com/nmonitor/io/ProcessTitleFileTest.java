package br.com.nmonitor.io;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.nmonitor.analyzers.Analyzer;
import br.com.nmonitor.processor.exceptions.NoContentException;
import br.com.nmonitor.processor.exceptions.ServerInitializationError;
import br.com.nmonitor.search.SearchServer;

public class ProcessTitleFileTest {
	
	public static final String HTML_FILES_PATH = "./src/test/resources/";

	@Test
	public void testReadTitleFileWithContent() throws NoContentException {
		// Given 
		ProcessTitleFile processTitleFile = new ProcessTitleFile();

		// When 
	    File file = new File(HTML_FILES_PATH + "titles.txt");
		List<String> titles= processTitleFile.processFile(file); 
		
		// Then 
		Assert.assertTrue(titles.size() > 0);
	}
 
	@Test
	public void testWriteIndex() throws NoContentException, ServerInitializationError, IOException {
		// Given 
		ProcessTitleFile processTitleFile = new ProcessTitleFile();
	    File file = new File(HTML_FILES_PATH + "titles.txt");
	    List<String> titles = processTitleFile.processFile(file);
	    Analyzer analyzer = new Analyzer();
		Map<String, List<String>> indexMap = analyzer.analyze(titles);
	    File fileIndex = new File(HTML_FILES_PATH+"index2.ix");

		// When 
		processTitleFile.writeIndex(indexMap, fileIndex);
		SearchServer searchServer = new SearchServer(fileIndex);
		indexMap = searchServer.getMapIndex();
		
		// Then 
		Assert.assertTrue(indexMap.size() > 0);
	}
	
	@Test(expected=NoContentException.class)
	public void testReadTitleFileWithoutContent() throws NoContentException {
		// Give 
		ProcessTitleFile processTitleFile = new ProcessTitleFile();

		// When 
	    File file = new File(HTML_FILES_PATH+"emptytitles.txt");
		processTitleFile.processFile(file); 
		
		// Then 
		// throws exception
	}
}
