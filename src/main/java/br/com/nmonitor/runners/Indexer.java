package br.com.nmonitor.runners;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import br.com.nmonitor.analyzers.Analyzer;
import br.com.nmonitor.io.ProcessTitleFile;
import br.com.nmonitor.processor.exceptions.InvalidParameterException;
import br.com.nmonitor.processor.exceptions.NoContentException;
import br.com.nmonitor.utils.ParameterUtil;
import br.com.nmonitor.utils.ParametersMapUtils;

public class Indexer {
	public static void main(String[] args) throws InvalidParameterException {
		if(args.length < 2){
			System.out.println("Usage: Indexer [OPTIONS...] \n" +
					"\n" +
					"	-titleFile	File with titles to be indexed.\n" +
					"	-destFile	Destiny file of the index.\n" );
			
			System.exit(-1);
		}
		
		Map<String, List<String>> params = ParametersMapUtils.buildParamMap(args);
		String titleFile = ParameterUtil.getParameter("titleFile", params, true);
		String destFile = ParameterUtil.getParameter("destFile", params, true);
		
		ProcessTitleFile processTitleFile = new ProcessTitleFile();
		Analyzer analyzer = new Analyzer();
		
	    File file = new File(titleFile);
	    File fileIndex = new File(destFile);
	    
	    List<String> titles;
		
	    try {
			titles = processTitleFile.processFile(file);
		
			Map<String, List<String>> indexMap = analyzer.analyze(titles);
		   
			processTitleFile.writeIndex(indexMap, fileIndex);
		
		} catch (NoContentException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("File indexed with success!");
	}
}
