package br.com.nmonitor.analyzers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tokenizer {

	public Map<String, List<String>> toquenize(List<String> titles) {
		
		Map<String, List<String>> indexMap = new HashMap<String, List<String>>();

		Normalizer normalizer = new Normalizer();
		List<String> normalizeTitles = normalizer.normalize(titles);
		
		for (String normalizeTitle : normalizeTitles) {
			
			String tokens[] = normalizeTitle.split("\\s");
			
			for (int i = 0; i < tokens.length; i++) {
				if(tokens[i].trim().isEmpty())
					continue;
				if (indexMap.get(tokens[i]) != null) {
					List<String> indexedTitles = indexMap.get(tokens[i]);
					addTitleToList(normalizeTitle, indexedTitles);
				} else {
					List<String> indexedTitles = new ArrayList<String>();
					addTitleToList(normalizeTitle, indexedTitles);
					indexMap.put(tokens[i], indexedTitles);
				}
			}
		}

		return indexMap;
	}
	
	private void addTitleToList(String normalizeTitle,
			List<String> indexedTitles) {
		indexedTitles.add(normalizeTitle);
	}

}
