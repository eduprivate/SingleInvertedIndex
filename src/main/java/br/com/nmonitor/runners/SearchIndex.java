package br.com.nmonitor.runners;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.nmonitor.processor.exceptions.InvalidParameterException;
import br.com.nmonitor.processor.exceptions.ServerInitializationError;
import br.com.nmonitor.search.SearchQuery;
import br.com.nmonitor.search.SearchServer;
import br.com.nmonitor.utils.ParameterUtil;
import br.com.nmonitor.utils.ParametersMapUtils;

public class SearchIndex {
	public static void main(String[] args) throws InvalidParameterException, ServerInitializationError {
		if (args.length < 1) {
			System.out.println("Usage: SearchIndex [OPTIONS...] \n" + "\n"
					+ "	-indexPath	Path of index file.\n");

			System.exit(-1);
		}

		Map<String, List<String>> params = ParametersMapUtils
				.buildParamMap(args);
		String indexPath = ParameterUtil.getParameter("indexPath", params, true);

		File file = new File(indexPath);

		SearchServer searchServer = new SearchServer(file);
		Map<String, List<String>> indexMap = searchServer.getMapIndex();
		SearchQuery searchQuery = new SearchQuery(indexMap);
		
		Scanner scanner = new Scanner(System.in);
	    
		while (true) {
	        System.out.println("Insert query:");
	        String query = scanner.nextLine().trim();
	        List<String> queryResult = searchQuery.performQuery(query);
	        
	        if (queryResult != null && queryResult.size() > 0) {
		        for (String result : queryResult) {
					System.out.println( result );
		        }
	        } else {
	        	System.out.println( " --- Nenhum documento encontrado! " );
	        }     
	    }
	}
}
