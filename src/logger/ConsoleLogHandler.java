package logger;

public class ConsoleLogHandler implements LogHandler{

	@Override
	public void write(LogMessage logMessage) {
		System.out.println(logMessage.toString());
		
	}

}
