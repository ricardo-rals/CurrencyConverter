package Model;

import java.util.Map;

public class ExchangeRates {
    private String base;
    private Map<String, Double> rates;

    public ExchangeRates(String base, Map<String, Double> rates) {
        this.base = base;
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public double getRate(String currency) {
        return rates.getOrDefault(currency, 0.0);
    }
}