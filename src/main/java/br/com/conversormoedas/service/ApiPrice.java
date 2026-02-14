package br.com.conversormoedas.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiPrice {
    private final String API_KEY = "6cbc527b59d419dd6eab6b50";

    public void showPriceForBRL() {
        List<String> coins = List.of("USD", "EUR", "GBP", "JPY", "CHF");
        System.out.println("Preços atuais do Real (BRL) em relação a outras moedas:");

        for (String coin : coins) {
            String url = String.format("https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + coin);

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
                    double price = taxes.get("BRL").getAsDouble();
                    System.out.printf("%s: %.4f BRL%n", coin, price);
                } else{
                    System.out.printf("Erro ao obter a taxa de conversão para %s. Status code: %d%n", coin, response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                System.out.printf("Erro ao obter a taxa de conversão para %s: %s%n", coin, e.getMessage());
            }
        }
    }
}
