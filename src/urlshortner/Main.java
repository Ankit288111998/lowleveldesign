package urlshortner;

public class Main {
    public static void main(String[] args) {
        UrlShortenerService service = new UrlShortenerService();

        String longUrl = "https://www.openai.com/research/gpt-4";
        String shortUrl = service.shortenUrl(longUrl);

        System.out.println("Short URL: " + shortUrl);
        System.out.println("Original URL from short: " + service.getLongUrl(shortUrl.replace("http://short.ly/", "")));
    }
}

