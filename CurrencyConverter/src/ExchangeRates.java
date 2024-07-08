import java.util.Map;

public class ExchangeRates {
    private String base_code;
    private Map<String, Double> rates;

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public double convertFromUSD(double amount, String targetCurrency) {
        Double rate = rates.get(targetCurrency);
        return rate != null ? amount * rate : 0;
    }
}
