package slidingWindowCounterRateLimiter;

public class RateLimiterService {

    private final SlidingWindowRateLimiter slidingWindowCounter;

    public RateLimiterService(int maxRequests, long windowSizeInMillis) {
        this.slidingWindowCounter = new SlidingWindowRateLimiter(maxRequests, windowSizeInMillis);
    }

    public boolean isRequestAllowed(RequestInfo requestInfo) {
        return slidingWindowCounter.allowRequest(requestInfo.getUserId());
    }
}

