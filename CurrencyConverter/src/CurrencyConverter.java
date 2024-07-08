import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {

    private static final String API_KEY = "ff610871df30c6c116e41c66";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public ExchangeRates getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        String url = BASE_URL + API_KEY + "/latest/" + baseCurrency;

        // Criação do cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Criação da solicitação HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Accept", "application/json")
                .GET()
                .build();

        // Envio da solicitação e obtenção da resposta
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Parse da resposta JSON usando Gson
        Gson gson = new Gson();
        ExchangeRates exchangeRates = gson.fromJson(response.body(), ExchangeRates.class);

        return exchangeRates;
    }
}
