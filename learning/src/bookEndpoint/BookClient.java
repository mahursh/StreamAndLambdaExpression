package bookEndpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class BookClient {
    private static final String BASE_URL = "http://localhost:8081/api/books";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("\nChoose an option:");
            System.out.println("1. Get all books");
            System.out.println("2. get a book by ID");
            System.out.println("3. Add a book (POST)");
            System.out.println("4. DELETE a book by ID");
            System.out.println("5. EXIT");
            System.out.println("> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice){
                case 1 :
                    sendGetRequest(BASE_URL);
                    break;
                case 2 :
                    System.out.println("Enter book ID: ");
                    int id = scanner.nextInt();
                    sendGetRequest(BASE_URL + "/" + id);
                    break;
                case 3 :
                    sendPostRequest();
                    break;
                case 4 :
                    System.out.println("Enter book ID to delete: ");
                    int deleteId = scanner.nextInt();
                    sendDeleteRequest(BASE_URL + "/" + deleteId);
                    break;
                case 5 :
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice, try again.");

            }
        }
    }



    private static void sendGetRequest(String urlStr){
        try {
            URL url = new URL(urlStr);
            HttpURLConnection  conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            printResponse(conn);


        }catch(Exception e){
            e.printStackTrace();
        }

    }




    private static void sendPostRequest(){
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/json");

            String jsonInput = "{\"title\" : \"New Book\" , \"author\" : \"Unknown\"}";
            try(OutputStream os = conn.getOutputStream()){
                os.write(jsonInput.getBytes());
            }
            printResponse(conn);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void sendDeleteRequest(String urlStr){
        try{
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            printResponse(conn);



        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private static void printResponse(HttpURLConnection conn) throws IOException{
        int responseCode = conn.getResponseCode();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(
                responseCode < 400 ? conn.getInputStream() : conn.getErrorStream()))){
            String line;
            StringBuilder response = new StringBuilder();
            while((line = reader.readLine()) != null){
                response.append(line);
            }

            System.out.println("Response: " + response);
        }
    }



}
