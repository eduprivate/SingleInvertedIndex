package br.com.nmonitor.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.nmonitor.analyzers.Normalizer;
import br.com.nmonitor.processor.exceptions.NoContentException;

public class ProcessTitleFile {

	public List<String> processFile(File file) throws NoContentException {

		List<String> titles = new ArrayList<String>();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			String title;

			while ((title = bufferedReader.readLine()) != null) {
				titles.add(title);
			}

			if (titles.size() == 0) {
				throw new NoContentException("");
			}
		} catch (IOException e) {
			throw new NoContentException(e);
		}
		return titles;
	}

	public Map<String, List<String>> processIndexFile(File file) throws NoContentException {

		Map<String, List<String>> indexMap = new HashMap<String, List<String>>();
		Normalizer normalizer = new Normalizer();
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
			StringBuffer tokenBuffer = new StringBuffer();
			String tokenLine;

			while ((tokenLine = bufferedReader.readLine()) != null) {
				tokenBuffer.append(tokenLine);
			}

			if (tokenBuffer.length() == 0) {
				throw new NoContentException("No index content");
			}
			
			String fileTokens[] = tokenBuffer.toString().split("\\],");
			
			for (int i = 0; i < fileTokens.length; i++) {
				if(fileTokens[i].trim().isEmpty())
					continue;
				String[] index = fileTokens[i].split("=");
				String token = index[0];
				String unnormalizedDoc = index[1];
				
				if (!token.isEmpty() && !unnormalizedDoc.isEmpty()){
					String[] docs = normalizer.normalizeToken(unnormalizedDoc).split(",");
					List<String> listDocs = Arrays.asList(docs); 
					indexMap.put(token.trim(), listDocs);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return indexMap;
	}

	public void writeIndex(Map<String, List<String>> indexMap, File fileIndex) throws IOException, NoContentException {
		BufferedWriter output = null;
		try {
			
			Map<String, List<String>> existingIndexMap = null;
			if (fileIndex.exists()) {
				existingIndexMap = processIndexFile(fileIndex);
				indexMap.putAll(existingIndexMap);
			} 
			
			String content = indexMap.toString().replaceAll("[\\{\\}]", "");
            output = new BufferedWriter(new FileWriter(fileIndex));
            output.write(content);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( output != null ) output.close();
        }
		
	}
}
