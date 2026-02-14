package br.com.conversormoedas.reader;

import java.util.Scanner;

public class ReaderCoin {
    private static Scanner scanner = new Scanner(System.in);

    public static String ReaderCoin(String message) {
        displayConversationCoins();
        System.out.println(message);
        return scanner.nextLine().trim().toUpperCase();
    }

    private static void displayConversationCoins() {
        System.out.println("Moedas suportadas:");
        System.out.println("1. Dólar (USD)");
        System.out.println("2. Euro (EUR)");
        System.out.println("3. Real (BRL)");
        System.out.println("4. Libra Esterlina (GBP)");
        System.out.println("5. Iene (JPY)");
        System.out.println("6. Franco Suíço (CHF)");
    }
}
