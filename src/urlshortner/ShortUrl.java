package urlshortner;

import java.time.LocalDateTime;

public class ShortUrl {
	private String longUrl;
	private String shortKey;
	private LocalDateTime createdAt;
	private LocalDateTime expiryAt;
	public String getLongUrl() {
		return longUrl;
	}
	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}
	public String getShortKey() {
		return shortKey;
	}
	public void setShortKey(String shortKey) {
		this.shortKey = shortKey;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getExpiryAt() {
		return expiryAt;
	}
	public void setExpiryAt(LocalDateTime expiryAt) {
		this.expiryAt = expiryAt;
	}
	public ShortUrl(String longUrl, String shortKey, LocalDateTime createdAt, LocalDateTime expiryAt) {
		super();
		this.longUrl = longUrl;
		this.shortKey = shortKey;
		this.createdAt = createdAt;
		this.expiryAt = expiryAt;
	}
}
