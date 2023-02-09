package org.example;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Syötä henkilötunnus: ");
        Henkilotunnus hetu = new Henkilotunnus(scanner.nextLine());

        System.out.println(hetu.getIka());
        System.out.println(hetu.getSukupuoli());
    }
}