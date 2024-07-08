import Model.ExchangeRates;
import Service.ApiExchangeRateService;
import Service.ConversionService;
import Service.ExchangeRateService;
import Ui.UserInterface;
import java.time.LocalDateTime;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> initialCurrencies = new HashSet<>(Arrays.asList("USD", "ARS", "BOB", "BRL", "CLP", "COP"));
        ExchangeRateService exchangeRateService = new ApiExchangeRateService(initialCurrencies);
        ConversionService conversionService = new ConversionService();
        UserInterface userInterface = new UserInterface();
        List<String> conversionHistory = new ArrayList<>();
        List<String> conversionLogs = new ArrayList<>();

        String baseCurrency = userInterface.getCurrencyFromUser("Informe a moeda base: ").toUpperCase();
        while (!initialCurrencies.contains(baseCurrency)) {
            userInterface.displayError("Moeda base inválida. Tente novamente.");
            baseCurrency = userInterface.getCurrencyFromUser("Informe a moeda base: ").toUpperCase();
        }

        try {
            while (true) {
                int option = userInterface.displayMenuAndGetChoice(baseCurrency);

                switch (option) {
                    case 1:
                        handleConversion(userInterface, conversionService, exchangeRateService, baseCurrency, conversionHistory, conversionLogs,true);
                        break;
                    case 2:
                        handleConversion(userInterface, conversionService, exchangeRateService, baseCurrency, conversionHistory, conversionLogs, false);
                        break;
                    case 3:
                        userInterface.displayHistory(conversionHistory);
                        break;
                    case 4:
                        handleAddCurrency(userInterface, initialCurrencies, exchangeRateService);
                        break;
                    case 5:
                        userInterface.displayExchangeRates(exchangeRateService.getExchangeRates(baseCurrency));
                        break;
                    case 6:
                        displayConversionLogs(userInterface, conversionLogs);
                        break;
                    case 7:
                        System.out.println("Saindo do programa...");
                        return;
                    default:
                        userInterface.displayError("Opção inválida. Tente novamente.");
                        break;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.getMessage();
        }
    }

    private static void handleConversion(UserInterface userInterface, ConversionService conversionService,
                                         ExchangeRateService exchangeRateService, String baseCurrency,
                                         List<String> conversionHistory,List<String> conversionLogs, boolean toTargetCurrency) throws IOException, InterruptedException {
        ExchangeRates exchangeRates = exchangeRateService.getExchangeRates(baseCurrency);
        userInterface.displayCurrencies(new ArrayList<>(exchangeRates.getRates().keySet()));

        String currency = userInterface.getCurrencyFromUser("Escolha a moeda " + (toTargetCurrency ? "de destino: " : "de origem: ")).toUpperCase();
        double amount = userInterface.getAmountFromUser();

        if (!exchangeRates.getRates().containsKey(currency)) {
            userInterface.displayError("Moeda inválida. Tente novamente.");
            return;
        }

        double convertedAmount;
        String conversionLog;

        if (toTargetCurrency) {
            convertedAmount = conversionService.convert(amount, exchangeRates.getRate(currency));
            conversionLog = String.format("%.2f %s equivale a %.2f %s", amount, exchangeRates.getBase(), convertedAmount, currency);
        } else {
            convertedAmount = conversionService.convertToBase(amount, exchangeRates.getRate(currency));
            conversionLog = String.format("%.2f %s equivale a %.2f %s", amount, currency, convertedAmount, exchangeRates.getBase());
        }

        LocalDateTime timestamp = LocalDateTime.now();
        String fullLog = String.format("[%s] - %s", timestamp, conversionLog);
        conversionLogs.add(fullLog);

        conversionHistory.add(conversionLog);
        userInterface.displayConversionResult(conversionLog);
    }

    private static void handleAddCurrency(UserInterface userInterface, Set<String> initialCurrencies,
                                          ExchangeRateService exchangeRateService) throws IOException, InterruptedException {
        String newCurrency = userInterface.getCurrencyFromUser("Digite o código da nova moeda: ").toUpperCase();
        initialCurrencies.add(newCurrency);
        userInterface.displayConversionResult("Moeda adicionada com sucesso.");

        System.out.println("Moedas atualizadas:");
        for (String currency : initialCurrencies) {
            System.out.println(currency);
        }
    }

    private static void displayConversionLogs(UserInterface userInterface, List<String> conversionLogs) {
        System.out.println("### Registros de Conversão ###");
        for (String log : conversionLogs) {
            System.out.println(log);
        }
        System.out.println("#############################");
    }
}
