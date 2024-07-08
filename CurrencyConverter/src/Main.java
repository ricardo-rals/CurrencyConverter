import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        try {
            String baseCurrency = "USD";
            ExchangeRates exchangeRates = converter.getExchangeRates(baseCurrency);

            // Exibir informações
            System.out.println("Taxa de câmbio base: " + baseCurrency);
            System.out.println("Moedas filtradas:");
            System.out.println("ARS: " + exchangeRates.getARS());
            System.out.println("BOB: " + exchangeRates.getBOB());
            System.out.println("BRL: " + exchangeRates.getBRL());
            System.out.println("CLP: " + exchangeRates.getCLP());
            System.out.println("COP: " + exchangeRates.getCOP());

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
    }
}
