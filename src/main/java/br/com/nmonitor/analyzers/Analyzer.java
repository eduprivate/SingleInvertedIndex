package br.com.nmonitor.analyzers;

import java.util.List;
import java.util.Map;

public class Analyzer {

	public Map<String, List<String>> analyze(List<String> titles) {

		Tokenizer tokenizer = new Tokenizer();
		Normalizer normalizer = new Normalizer();
		
		List<String> normalizeTitles = normalizer.normalize(titles);
		Map<String, List<String>> indexMap = tokenizer.toquenize(normalizeTitles);
		
		return indexMap;
	}

}
