package main;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class apitest {

    public static void main(String[] args) {
        
        String apiKey = "E1N03u5S2war1K9mF4TuaFX39ZPhNgom"; 
        
        String apiUrl = "http://localhost/dolibarr/htdocs/api/index.php/thirdparties";

    
        String jsonPayload = "{\n" +
                "  \"name\": \"test123\",\n" +
                "  \"client\": 2,\n" +
                "  \"email\": \"hej@test.se\",\n" +
                "  \"phone\": \"1234567\"\n" +
                "}";

        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("DOLAPIKEY", apiKey)
                .header("Content-Type", "application/json") 
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

        try {
            System.out.println("Skickar in ett nytt lead till Dolibarr");
            
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            System.out.println("HTTP Status: " + response.statusCode());
            
            
            if(response.statusCode() == 200 || response.statusCode() == 201) {
                System.out.println("Dolibarr skapade ett lead med ID: " + response.body());
            } else {
                System.out.println("Något gick fel: " + response.body());
            }
            
        } catch (Exception e) {
            System.out.println("Kunde inte ansluta: " + e.getMessage());
        }
    }
}
