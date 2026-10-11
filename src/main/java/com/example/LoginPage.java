package com.example;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

public class LoginPage {
    private static HttpServer server;

    public static void start() throws IOException {
        if (server != null) return;
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8085), 0);
        server.createContext("/", exchange -> {
            String html = """
                <!doctype html>
                <html lang="en">
                <head><meta charset="UTF-8"><title>Demo Login</title></head>
                <body>
                  <h1>Demo Login</h1>
                  <form id="loginForm">
                    <label>Username <input id="username" name="username" required></label><br>
                    <label>Password <input id="password" name="password" type="password" required></label><br>
                    <button id="loginButton" type="submit">Login</button>
                  </form>
                  <p id="message" aria-live="polite"></p>
                  <script>
                    document.getElementById('loginForm').addEventListener('submit', function(e) {
                      e.preventDefault();
                      const u = document.getElementById('username').value;
                      const p = document.getElementById('password').value;
                      document.getElementById('message').textContent =
                        (u === 'student' && p === 'Pass123!') ?
                        'Login successful' : 'Invalid username or password';
                    });
                  </script>
                </body></html>
                """;
            byte[] bytes = html.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (var out = exchange.getResponseBody()) {
                out.write(bytes);
            }
        });
        server.start();
    }

    public static void stop() {
        if (server != null) {
            server.stop(0);
            server = null;
        }
    }

    public static String url() {
        return "http://127.0.0.1:8085/";
    }
}
