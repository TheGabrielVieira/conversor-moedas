package br.com.conversormoedas.service;

import br.com.conversormoedas.model.Conversion;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiConversion {
    private final String API_KEY = "6cbc527b59d419dd6eab6b50";

    public Conversion getConversion(String coinOrigin, String coinDestination) {
        String url = String.format("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + coinOrigin);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonObject taxes = jsonObject.getAsJsonObject("conversion_rates");
                if (taxes.has(coinDestination)){
                    double price = taxes.get(coinDestination).getAsDouble();
                    return new Conversion(coinOrigin, coinDestination, price);

                } else  {
                    System.out.println("Moeda de destino não encontrada");
                }

            } else{
                System.out.printf("Erro ao obter a taxa de conversão para %s. Status code: %d%n", coinDestination, response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            System.out.printf("Erro ao obter a taxa de conversão para %s: %s%n", coinDestination, e.getMessage());
        }
        return null;

    }
}
