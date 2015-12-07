package br.com.nmonitor.analyzers;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.nmonitor.io.ProcessTitleFile;
import br.com.nmonitor.processor.exceptions.NoContentException;

public class TokenizerTest {
	
	public static final String HTML_FILES_PATH = "./src/test/resources/";

	@Test
	public void tokenizeTitlesListTest() throws NoContentException {
		// Given
		ProcessTitleFile processTitleFile = new ProcessTitleFile();
	    File file = new File(HTML_FILES_PATH + "10_titles.txt");
		List<String> titles= processTitleFile.processFile(file); 

		// When 
		Tokenizer tokenizer = new Tokenizer();
		Map<String, List<String>> indexMap = tokenizer.toquenize(titles);
		
		// Then 
		Assert.assertTrue(indexMap.size() > 0);
	}
	
	@Test
	public void sameTokeMultipleDocsTest() throws NoContentException {
		// Given
		ProcessTitleFile processTitleFile = new ProcessTitleFile();
	    File file = new File(HTML_FILES_PATH + "10_titles.txt");
		List<String> titles= processTitleFile.processFile(file); 

		// When 
		Tokenizer tokenizer = new Tokenizer();
		Map<String, List<String>> indexMap = tokenizer.toquenize(titles);
		
		// Then 
		Assert.assertTrue(indexMap.size() > 0);
		Assert.assertTrue(indexMap.get("senhor") != null);
		Assert.assertTrue(indexMap.get("senhor").size() == 2);
	}
}
