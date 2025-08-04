package urlshortner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class UrlShortenerService {
	private static final String BASE_HOST = "http://short.ly/";
	private static final int SHORT_KEY_LENGTH = 6;
	private Map<String, ShortUrl> shortToLongMap = new HashMap<>();
	private Map<String, String> longToShortKeyMap = new HashMap<>();
	//private long idCounter = 100000;
	
	private static final AtomicLong idCounter = new AtomicLong(System.currentTimeMillis());
	public String shortenUrl(String longUrl) {
		
		if(longToShortKeyMap.containsKey(longUrl)) {
			return BASE_HOST + longToShortKeyMap.get(longUrl);
		}
		
		String shortKey = generateShortKey();
		ShortUrl shortUrl = new ShortUrl(shortKey, longUrl, LocalDateTime.now(), null);

        shortToLongMap.put(shortKey, shortUrl);
        longToShortKeyMap.put(longUrl, shortKey);

        return BASE_HOST + shortKey;
	}

	 public String getLongUrl(String shortKey) {
	        ShortUrl shortUrl = shortToLongMap.get(shortKey);
	        if (shortUrl == null) return null;

	        if (shortUrl.getExpiryAt() != null &&
	            LocalDateTime.now().isAfter(shortUrl.getExpiryAt())) {
	            shortToLongMap.remove(shortKey);
	            longToShortKeyMap.remove(shortUrl.getLongUrl());
	            return null;
	        }

	        return shortUrl.getLongUrl();
	    }

//	    private String generateShortKey() {
//	        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//	        StringBuilder key = new StringBuilder();
//	        Random random = new Random();
//
//	        do {
//	            key.setLength(0);
//	            for (int i = 0; i < SHORT_KEY_LENGTH; i++) {
//	                key.append(chars.charAt(random.nextInt(chars.length())));
//	            }
//	        } while (shortToLongMap.containsKey(key.toString()));
//
//	        return key.toString();
//	    }
	 //using Base62 encode 
	 //You can generate short URLs by encoding a unique ID (like an auto-increment counter or UUID hash) into Base62.
	 private String generateShortKey() {
	        return encodeBase62(idCounter.getAndIncrement());
	    }

	    private String encodeBase62(long number) {
	        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        StringBuilder sb = new StringBuilder();
	        while (number > 0) {
	            sb.append(chars.charAt((int)(number % 62)));
	            number /= 62;
	        }
	        return sb.reverse().toString();
	    }
}
