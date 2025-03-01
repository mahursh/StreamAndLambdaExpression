package clientServer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleServer {

    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
        httpServer.createContext("/api/data", new MyHandler());
        httpServer.setExecutor(null);
        httpServer.start();
        System.out.println("Server started at http://localhost:8000/api/data");
    }
    static class MyHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if("GET".equals(exchange.getRequestMethod())){
                String res = "{\"message\": \"Hello from server\", \"status\": 200}";
                exchange.sendResponseHeaders(200, res.getBytes().length);
                try(OutputStream os = exchange.getResponseBody()){
                    os.write(res.getBytes());
                }
            }else{
                exchange.sendResponseHeaders(405, -1);
            }
        }
    }

}
