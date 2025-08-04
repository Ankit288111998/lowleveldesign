package slidingWindowCounterRateLimiter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter {

    private final long windowSizeInMillis;
    private final int maxRequests;

    // Map<userId, Map<secondBucket, count>>
    private final Map<String, TreeMap<Long, Integer>> userRequestCounts = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequests, long windowSizeInMillis) {
        this.maxRequests = maxRequests;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    public synchronized boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();
        System.out.println("currentTime: " + currentTime);
        long currentBucket = currentTime / 1000;
        System.out.println("currentBucket: " + currentBucket);
        userRequestCounts.putIfAbsent(userId, new TreeMap<>());
        TreeMap<Long, Integer> timestampCountMap = userRequestCounts.get(userId);
        System.out.println("timestampCountMap: " + timestampCountMap);
        // Clean up old entries
        long windowStartBucket = (currentTime - windowSizeInMillis) / 1000;
        timestampCountMap.headMap(windowStartBucket).clear();

        // Sum counts in current sliding window
        int totalRequests = timestampCountMap.tailMap(windowStartBucket).values().stream()
                .mapToInt(Integer::intValue).sum();
        System.out.println("totalRequests: " + totalRequests);
        if (totalRequests >= maxRequests) {
            return false; // Reject request
        }

        // Allow request and increment current bucket count
        timestampCountMap.put(currentBucket,
                timestampCountMap.getOrDefault(currentBucket, 0) + 1);

        return true;
    }
}
