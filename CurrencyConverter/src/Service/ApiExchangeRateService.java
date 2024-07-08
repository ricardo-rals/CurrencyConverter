package Service;

import Model.ExchangeRates;
import Util.HttpClientUtil;
import com.google.gson.Gson;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ApiExchangeRateService implements ExchangeRateService {
    private static final String API_KEY = "ff610871df30c6c116e41c66";
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";
    private final Set<String> supportedCurrencies;

    public ApiExchangeRateService(Set<String> supportedCurrencies) {
        this.supportedCurrencies = supportedCurrencies;
    }

    @Override
    public ExchangeRates getExchangeRates(String baseCurrency) throws IOException, InterruptedException {
        String url = API_URL + baseCurrency;
        String response = HttpClientUtil.sendGetRequest(url);

        Map<String, Object> responseData = new Gson().fromJson(response, Map.class);
        Map<String, Double> filteredRates = new HashMap<>();

        Map<String, Double> allRates = (Map<String, Double>) responseData.get("conversion_rates");
        for (String currency : supportedCurrencies) {
            if (allRates.containsKey(currency)) {
                filteredRates.put(currency, allRates.get(currency));
            }
        }

        return new ExchangeRates(baseCurrency, filteredRates);
    }
}