package br.com.conversormoedas;

import br.com.conversormoedas.model.Conversion;
import br.com.conversormoedas.service.ApiConversion;
import br.com.conversormoedas.service.ApiPrice;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ApiConversion apiConversion = new ApiConversion();
        ApiPrice apiPrice = new ApiPrice();

        System.out.println("""
            Bem-vindo ao Conversor de Moedas!
            Moedas disponíveis:
            USD - Dólar Americano
            EUR - Euro
            GBP - Libra Esterlina
            JPY - Iene Japonês
            CHF - Franco Suíço
            BRL - Real Brasileiro
                """);

        int opcao = 0;

        while (opcao != 3) {
            System.out.println("Menu:");
            System.out.println("1. Converter moedas");
            System.out.println("2. Verificar cotação");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");

            if (scanner.hasNextInt()) {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1 -> {
                        System.out.print("Digite a moeda de origem (USD, EUR, GBP, JPY, CHF, BRL): ");
                        String coinOrigin = scanner.nextLine().toUpperCase();
                        System.out.print("Digite a moeda de destino (USD, EUR, GBP, JPY, CHF, BRL): ");
                        String coinDestination = scanner.nextLine().toUpperCase();
                        System.out.println("Digite o valor a ser convertido: ");
                        double amount = scanner.nextDouble();
                        scanner.nextLine();

                        Conversion conversion = apiConversion.getConversion(coinOrigin, coinDestination);

                        if (conversion != null) {
                            double convertedAmount = conversion.convertAmount(amount);
                            System.out.printf("%.2f %s equivalem a %.2f %s%n", amount, coinOrigin, convertedAmount, coinDestination);
                        } else {
                            System.out.println("Não foi possível realizar a conversão. Por favor, tente novamente.");
                        }
                    }
                    case 2 -> apiPrice.showPriceForBRL();
                    case 3 -> System.out.println("Saindo do programa. Obrigado por usar o Conversor de Moedas!");
                    default -> System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                }
            }
        }
    }
}