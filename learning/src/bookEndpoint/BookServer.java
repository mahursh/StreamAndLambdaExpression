package bookEndpoint;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookServer {
    private static List<Book> books = new ArrayList<>();

    public static void main(String[] args) throws IOException{

        Book b1 = new Book();
        Book b2 = new Book();
        Book b3 = new Book();

        b1.setId(1).setTitle("Java").setAuthor("Oracle");
        b2.setId(2).setTitle("1984").setAuthor("George Orwell");
        b3.setId(3).setTitle("Brahe New World").setAuthor("Aldous Huxley");

        books.add(b1);
        books.add(b2);
        books.add(b3);

        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/api/books", new BookHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Server Started at http://localhost:8081/api/books");

    }

    static class BookHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String[] pathParts = path.split("/");

            if("GET".equals(method)){
                if(pathParts.length == 4){

                    int bookId = Integer.parseInt(pathParts[3]);
                    Optional<Book> book = books.stream().filter(b -> b.getId() == bookId).findFirst();
                    sendResponse(exchange, book.map(Book::toString).orElse("{\"message\" : \"Book not found\"}"), book.isPresent() ? 200 : 404);

                }else{
                    String response = books.toString();
                    sendResponse(exchange, response, 200);
                }
            }else if("POST".equals(method)){
                sendResponse(exchange, "{\"message\" : \"Book saved successfully\"}", 201);

            }else if ("DELETE".equals(method)){
                if(pathParts.length == 4){
                    int bookId = Integer.parseInt(pathParts[3]);
                    boolean removed = books.removeIf(b -> b.getId() == bookId);
                    sendResponse(exchange, removed ? "{\"message\" : \"Book deleted successfully\"}" : "{\"message\" : \"Book not found\"}", removed ? 200 :404);

                }else{
                    sendResponse(exchange, "{\"error\": \"Invalid request\"}", 400);
                }
            }else{
                sendResponse(exchange, "{\"error\": \"Method not allowed \"}",405);
            }
        }

        private void sendResponse(HttpExchange exchange, String response, int statusCode) throws IOException{
            exchange.sendResponseHeaders(statusCode, response.length());
            try(OutputStream os = exchange.getResponseBody()){
                os.write(response.getBytes());
            }
        }
    }
}
