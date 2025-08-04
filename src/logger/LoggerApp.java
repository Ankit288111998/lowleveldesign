package logger;

public class LoggerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = Logger.getInsatnce();
		logger.addHandler(new ConsoleLogHandler());
		logger.addHandler(new FileLogHandler("app.log"));
		
		logger.setLevel(LogLevel.INFO);

        logger.debug("This is a debug message"); // Ignored
        logger.info("User login successful");
        logger.error("Database connection failed");
	}
	
	
	/*
	 * Optional Extensions
Add AsyncLogger using BlockingQueue<LogMessage> + background thread.

Add formatting templates using LogFormatter.

Support rolling files (e.g., max file size = 5MB, then roll over).

Allow logging to remote systems via HttpLogHandler.
	 */

}
