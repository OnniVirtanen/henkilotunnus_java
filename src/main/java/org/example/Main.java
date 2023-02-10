package org.example;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Syötä henkilötunnus: ");
        Henkilotunnus hetu = new Henkilotunnus("110101A101ö");

        System.out.println(hetu.getAge());
        System.out.println(hetu.getGender());
    }
}