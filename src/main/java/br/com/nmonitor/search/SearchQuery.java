package br.com.nmonitor.search;

import java.util.List;
import java.util.Map;

public class SearchQuery {
	
	private Map<String, List<String>> indexMap;

	public SearchQuery(Map<String, List<String>> indexMap) {
		this.indexMap = indexMap;
	}

	public List<String> performQuery(String query) {
		
		return indexMap.get(query);
	}

}
