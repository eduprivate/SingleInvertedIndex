package br.com.nmonitor.analyzers;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import br.com.nmonitor.analyzers.Analyzer;
import br.com.nmonitor.io.ProcessTitleFile;
import br.com.nmonitor.processor.exceptions.NoContentException;

public class AnalyzerTest {
	
	public static final String HTML_FILES_PATH = "./src/test/resources/";
	
	@Test
	public void testAnalyzerProcessing() throws NoContentException{
		// Give
		ProcessTitleFile processTitleFile = new ProcessTitleFile();
		File file = new File(HTML_FILES_PATH+"titles.txt");
		List<String> titles = processTitleFile.processFile(file);
		Analyzer analyzer = new Analyzer();

		// When
		Map<String, List<String>> mapIndex = analyzer.analyze(titles);

		// Then
		Assert.assertTrue(mapIndex.size() > 0);
	}

}
