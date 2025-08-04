 1. Logging System
Purpose: Capture, format, and persist application logs with support for log levels (INFO, DEBUG, ERROR).

Features:

Log routing to different outputs (console, file, etc.)

Thread-safe logger instance (Singleton pattern)

Extensible architecture for adding new log destinations


2. URL Shortener
Purpose: Shortens long URLs and maps them to unique short keys for easy sharing and redirection.

Features:

Unique key generation using Base62 encoding

HashMap-based key-to-URL mapping

Support for duplicate URL detection

In-memory storage with ability to swap to DB

3. Rate Limiter
Purpose: Restricts the number of API requests a user/client can make in a given time window.

Features:

Sliding window and fixed window algorithms

Customizable limit thresholds

In-memory map for tracking request counts

Scalable and extensible for real-world systems


 Tech Stack
Language: Java

Design Principles: OOP, SOLID

Patterns Used: Singleton, Strategy, Factory (where applicable)

