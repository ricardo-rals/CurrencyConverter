import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {

    private static final String API_KEY = "ff610871df30c6c116e41c66";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/USD";
    private static final String[] CURRENCIES = {"USD", "ARS", "BOB", "BRL", "CLP", "COP"};

    public ExchangeRates getExchangeRates() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            String jsonResponse = response.body();
            JsonObject json = new Gson().fromJson(jsonResponse, JsonObject.class);

            ExchangeRates exchangeRates = new ExchangeRates();
            exchangeRates.setBase_code(json.get("base_code").getAsString());

            JsonObject conversionRates = json.getAsJsonObject("conversion_rates");

            Map<String, Double> filteredRates = new HashMap<>();
            for (String currency : CURRENCIES) {
                if (conversionRates.has(currency)) {
                    filteredRates.put(currency, conversionRates.get(currency).getAsDouble());
                }
            }

            exchangeRates.setRates(filteredRates);

            return exchangeRates;
        } else {
            throw new IOException("Erro na conexão: " + response.statusCode());
        }
    }
}
