package urlshortnersha256;

public class Main {
    public static void main(String[] args) {
        UrlShortenerService256 shortener = new UrlShortenerService256();

        String longUrl = "https://www.openai.com/research/chatgpt";
        String shortUrl = shortener.shortenUrl(longUrl);
        System.out.println("Shortened URL: " + shortUrl);

        String originalUrl = shortener.getOriginalUrl(shortUrl);
        System.out.println("Original URL: " + originalUrl);
    }
}

