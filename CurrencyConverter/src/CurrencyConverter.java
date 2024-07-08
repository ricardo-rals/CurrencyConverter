import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.IOException;

public class CurrencyConverter {

    private static final String API_KEY = "ff610871df30c6c116e41c66";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public String getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        URI url = URI.create(BASE_URL + API_KEY + "/latest/" + baseCurrency);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(url)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
