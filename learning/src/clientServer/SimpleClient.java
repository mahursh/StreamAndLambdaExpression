package clientServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimpleClient {
    public static void main(String[] args) {
        try{
            URL url = new URL("http://localhost:8000/api/data");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            if(responseCode == 200){
                try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
                    String line;
                    StringBuilder response = new StringBuilder();
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }
                    System.out.println("Response from server : " + response);
                }
            }else{
                System.out.println("Request failed with status : " + responseCode);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
