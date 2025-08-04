package ratelimiter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private final int maxRequests;
    private final long windowSizeInMillis;
    
    // Stores per-user request info
    private final Map<String, UserRequestInfo> userMap = new ConcurrentHashMap<>();

    public RateLimiter(int maxRequests, int windowSizeInSeconds) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInSeconds * 1000L;
    }

    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        userMap.putIfAbsent(userId, new UserRequestInfo(0, currentTime));

        synchronized (userMap.get(userId)) {
            UserRequestInfo info = userMap.get(userId);

            // If current window expired
            if (currentTime - info.windowStart >= windowSizeInMillis) {
                info.windowStart = currentTime;
                info.requestCount = 1;
                return true;
            }

            if (info.requestCount < maxRequests) {
                info.requestCount++;
                return true;
            }

            // Rate limit exceeded
            return false;
        }
    }

    // Inner class for user tracking
    private static class UserRequestInfo {
        int requestCount;
        long windowStart;

        UserRequestInfo(int count, long start) {
            this.requestCount = count;
            this.windowStart = start;
        }
    }
}
