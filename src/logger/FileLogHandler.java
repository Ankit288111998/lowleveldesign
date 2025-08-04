package logger;

import java.io.FileWriter;
import java.io.IOException;

public class FileLogHandler implements LogHandler {

	private String fileName;

	public FileLogHandler(String name) {
		this.fileName = name;
	}

	@Override
	public void write(LogMessage logMessage) {
		try(FileWriter fw = new FileWriter(fileName, true)){
			fw.write(logMessage.toString() + "\n");
		}catch(IOException e) {
			System.out.println("Failed to write to file: " + e.getMessage());
		}
	}

}
