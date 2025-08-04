package logger;

import java.time.LocalDateTime;

public class LogMessage {
	private String message;
	private LogLevel level;
	private LocalDateTime timestamp;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LogLevel getLevel() {
		return level;
	}
	public void setLevel(LogLevel level) {
		this.level = level;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public LogMessage(LogLevel level, String message) {
        this.timestamp = LocalDateTime.now();
        this.level = level;
        this.message = message;
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] [" + level + "] " + message;
    }
}
