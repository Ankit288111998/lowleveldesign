package slidingWindowCounterRateLimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //RateLimiterService limiter = new RateLimiterService(10, 10000); // 5 req per 10 sec
    	SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(10, 10000);
        String user = "ankit";

        for (int i = 1; i <= 50; i++) {
        	boolean allowed = limiter.allowRequest(user);
           // boolean allowed = limiter.isRequestAllowed(new RequestInfo(user, System.currentTimeMillis()));
            System.out.println("Request " + i + " allowed: " + allowed);
            Thread.sleep(500);
        }
    }
}

