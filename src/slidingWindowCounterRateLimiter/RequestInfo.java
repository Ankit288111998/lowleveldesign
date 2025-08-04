package slidingWindowCounterRateLimiter;

public class RequestInfo {
    private String userId;
    private long timestamp; // epoch millis

    public RequestInfo(String userId, long timestamp) {
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
