package urlshortnersha256;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlShortenerService256 {
    private static final String DOMAIN = "https://short.ly/";
    private static final int CODE_LENGTH = 8;
    int length = CODE_LENGTH;

    private Map<String, String> codeToUrl = new ConcurrentHashMap<>();
    private Map<String, String> urlToCode = new ConcurrentHashMap<>();

    public String shortenUrl(String longUrl) {
        if (urlToCode.containsKey(longUrl)) {
            return DOMAIN + urlToCode.get(longUrl);
        }

        String hash = generateHash(longUrl);
        String code = hash.substring(0, CODE_LENGTH);

        // Handle rare collision
        while (codeToUrl.containsKey(code) && !codeToUrl.get(code).equals(longUrl)) {
            length++; // Expand hash window
        	code = hash.substring(0, length);  
        }

        codeToUrl.put(code, longUrl);
        urlToCode.put(longUrl, code);
        return DOMAIN + code;
    }

    public String getOriginalUrl(String shortUrl) {
        String code = shortUrl.replace(DOMAIN, "");
        return codeToUrl.getOrDefault(code, null);
    }

    private String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing algorithm not found");
        }
    }
}

