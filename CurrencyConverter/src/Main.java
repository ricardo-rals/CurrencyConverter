import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        try {
            String baseCurrency = "USD";
            ExchangeRates exchangeRates = converter.getExchangeRates(baseCurrency);

            System.out.println("Taxa de câmbio base: " + exchangeRates.getBase_code());
            System.out.println("Moedas filtradas: ");

            Map<String, Double> rates = exchangeRates.getRates();
            if (rates != null) {
                for (String currency : rates.keySet()) {
                    System.out.println(currency + ": " + rates.get(currency));
                }

                Scanner scanner = new Scanner(System.in);
                System.out.print("\nDigite o valor em USD que deseja converter: ");
                double amountUSD = scanner.nextDouble();
                scanner.nextLine(); // Consumir a quebra de linha

                System.out.println("\nConversões:");
                for (String currency : rates.keySet()) {
                    System.out.println(currency + ": " + exchangeRates.convertFromUSD(amountUSD, currency));
                }
            } else {
                System.out.println("Não foi possível obter as taxas de câmbio para as moedas desejadas.");
            }

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
    }
}
