package Service;

import Model.ExchangeRates;

import java.io.IOException;

public interface ExchangeRateService {
    ExchangeRates getExchangeRates(String baseCurrency) throws IOException, InterruptedException;
}