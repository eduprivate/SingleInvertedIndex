package br.com.nmonitor.analyzers;

import java.util.ArrayList;
import java.util.List;

public class Normalizer {

	public String normalize(String title) {
		return title.toLowerCase().replaceAll("[^\\dA-Za-z ]", "");
	}
	
	public String normalizeToken(String title) {
		return title.toLowerCase().replaceAll("[\\[\\]]", "");
	}
	
	public List<String> normalize(List<String> titles) {
		List<String> normalizedTitles = new ArrayList<String>();
		
		for (String title : titles) {
			normalizedTitles.add(normalize(title));
		}
		
		return normalizedTitles;
	}
	
}
