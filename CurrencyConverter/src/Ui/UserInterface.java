package Ui;

import Model.ExchangeRates;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner = new Scanner(System.in);

    public int displayMenuAndGetChoice(String baseCurrency) {
        System.out.println("Menu:");
        System.out.println("1. Converter de " + baseCurrency + " para outra moeda");
        System.out.println("2. Converter de outra moeda para " + baseCurrency);
        System.out.println("3. Ver histórico de conversões");
        System.out.println("4. Adicionar nova moeda");
        System.out.println("5. Exibir taxas de câmbio");
        System.out.println("6. Exibir Log com datas");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
        return scanner.nextInt();
    }
    private final DecimalFormat decimalFormat;

    public UserInterface() {
        this.decimalFormat = new DecimalFormat("#.##");
    }
    public String getCurrencyFromUser(String prompt) {
        System.out.print(prompt);
        return scanner.next().toUpperCase();
    }

    public double getAmountFromUser() {
        System.out.print("Informe o valor a ser convertido: ");
        return scanner.nextDouble();
    }

    public void displayCurrencies(List<String> currencies) {
        System.out.println("Moedas disponíveis:");
        for (String currency : currencies) {
            System.out.println(currency);
        }
    }

    public void displayConversionResult(String result) {
        System.out.println(result);
    }

    public void displayHistory(List<String> history) {
        System.out.println("Histórico de conversões:");
        for (String entry : history) {
            System.out.println(entry);
        }
    }

    public void displayExchangeRates(ExchangeRates exchangeRates) {
        if (exchangeRates == null || exchangeRates.getRates() == null) {
            System.out.println("Não foi possível obter as taxas de câmbio.");
            return;
        }

        System.out.println("Taxas de Câmbio:");
        Map<String, Double> rates = exchangeRates.getRates();
        for (Map.Entry<String, Double> entry : rates.entrySet()) {
            String formattedRate = decimalFormat.format(entry.getValue());
            System.out.printf("%s: %s%n", entry.getKey(), formattedRate);
        }
    }

    public void displayError(String error) {
        System.out.println("Erro: " + error);
    }
}