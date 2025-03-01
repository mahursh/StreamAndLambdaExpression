package RestTemplates;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HelloHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        if("GET".equals(exchange.getRequestMethod())){
            String jsonResponse = "{\"message\": \"Hello, World!\"}";

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsonResponse.getBytes().length);

            try(OutputStream os = exchange.getResponseBody()){
                os.write(jsonResponse.getBytes());
            }
        }else{
            exchange.sendResponseHeaders(405, -1);
        }
    }
}
