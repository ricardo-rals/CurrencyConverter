import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CurrencyConverter converter = new CurrencyConverter();
        Scanner scanner = new Scanner(System.in);

        try {
            ExchangeRates exchangeRates = converter.getExchangeRates();

            while (true) {
                System.out.println("\nMenu de Conversão de Moedas:");
                System.out.println("1. Converter de USD para outra moeda");
                System.out.println("2. Converter de outra moeda para USD");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                int option = scanner.nextInt();
                scanner.nextLine();

                if (option == 3) {
                    System.out.println("Saindo...");
                    break;
                }

                if (option < 1 || option > 3) {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }

                System.out.println("Moedas disponíveis: " + String.join(", ", exchangeRates.getRates().keySet()));

                System.out.print("Escolha a moeda (USD, ARS, BOB, BRL, CLP, COP): ");
                String targetCurrency = scanner.nextLine().toUpperCase();

                if (!exchangeRates.getRates().containsKey(targetCurrency)) {
                    System.out.println("Moeda inválida. Tente novamente.");
                    continue;
                }

                System.out.print("Digite o valor que deseja converter: ");
                double amount = scanner.nextDouble();
                scanner.nextLine();

                double convertedAmount = 0;
                if (option == 1) {
                    convertedAmount = exchangeRates.convertFromUSD(amount, targetCurrency);
                    System.out.printf("%.2f USD equivale a %.2f %s%n", amount, convertedAmount, targetCurrency);
                } else if (option == 2) {
                    double rate = exchangeRates.getRates().get(targetCurrency);
                    convertedAmount = amount / rate;
                    System.out.printf("%.2f %s equivale a %.2f USD%n", amount, targetCurrency, convertedAmount);
                }
            }

        } catch (IOException | InterruptedException e) {
            e.getMessage();
        } finally {
            scanner.close();
        }
    }
}
