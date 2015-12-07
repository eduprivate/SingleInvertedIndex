package br.com.nmonitor.search;

import java.io.File;
import java.util.List;
import java.util.Map;

import br.com.nmonitor.io.ProcessTitleFile;
import br.com.nmonitor.processor.exceptions.NoContentException;
import br.com.nmonitor.processor.exceptions.ServerInitializationError;

public class SearchServer {
	
	private Map<String, List<String>> mapIndex;
	private File fileIndex;
	private ProcessTitleFile processTitleFile;

	public SearchServer(File file) throws ServerInitializationError {
		this.fileIndex = file;
		this.processTitleFile = new ProcessTitleFile();
		configSearchServer();
	}
	
	private void configSearchServer() throws ServerInitializationError{
		try {
			this.mapIndex = this.processTitleFile.processIndexFile(this.fileIndex);
		} catch (NoContentException e) {
			throw new ServerInitializationError(e);
		}
	}

	public Map<String, List<String>> getMapIndex() {
		return mapIndex;
	}

}
