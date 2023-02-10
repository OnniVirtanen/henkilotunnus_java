package org.example;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Henkilotunnus hetu = new Henkilotunnus("ssn-number");

        System.out.printf("age: %d%n", hetu.getAge());
        System.out.printf("gender: %s%n", hetu.getGender());
    }
}