package logger;

import java.util.ArrayList;
import java.util.List;

public class Logger {
	
	private static volatile Logger logger;
	private LogLevel currentLevel = LogLevel.DEBUG;
	private final List<LogHandler> handlers = new ArrayList<>();
	
	private Logger() {
		
	}
	
	public static Logger getInsatnce() {
		if(logger == null) {
			synchronized(Logger.class) {
				if(logger == null){
					logger = new Logger();
				}
			}
			
		}
		return logger;
	}
	
	public void addHandler(LogHandler handler) {
		handlers.add(handler);
	}
	
	public void setLevel(LogLevel level) {
        this.currentLevel = level;
    }
	
	public void log(String message, LogLevel level) {
		if(level.ordinal() >= currentLevel.ordinal()) {
			LogMessage logMessage = new LogMessage(level, message);
			for(LogHandler handler : handlers) {
				handler.write(logMessage);
			}
		}
	}
	
    // Helper shortcut methods
    public void info(String msg) { log(msg, LogLevel.INFO); }
    public void error(String msg) { log(msg, LogLevel.ERROR); }
    public void debug(String msg) { log(msg, LogLevel.DEBUG); }
    public void warn(String msg) { log(msg, LogLevel.WARN); }
}
