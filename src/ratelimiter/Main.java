package ratelimiter;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter(5, 10); // 5 requests per 10 seconds

        String user = "user123";

        for (int i = 0; i < 7; i++) {
            System.out.println("Request " + i + ": " + limiter.allowRequest(user));
            Thread.sleep(1000); // Simulate 1-second gap
        }
    }
}

