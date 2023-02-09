package org.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;


public class Henkilotunnus {

    private String henkilotunnus;

    public Henkilotunnus(String givenHenkilotunnus) {
        if (isCorrectHenkilotunnus(givenHenkilotunnus))
            henkilotunnus = givenHenkilotunnus;
        else
            throw new IllegalArgumentException("Ei sallitussa henkilötunnus muodossa.");
    }

    public boolean isCorrectHenkilotunnus(String givenHenkilotunnus) {
        final Pattern PATTERN =
                Pattern.compile("^(0[1-9]|[1-2][0-9]|30|31)(0[1-9]|1[0-2])([0-9][0-9])[-+A][0-9]{3}[0-9A-Z]$");

        Matcher matcher = PATTERN.matcher(givenHenkilotunnus);
        return matcher.matches();
    }

    public int getIka() {
        StringBuilder dateMonthYear = new StringBuilder(henkilotunnus);
        String birthDate = dateMonthYear.substring(0, 6);
        char valimerkki = dateMonthYear.charAt(6);

        int year = Integer.parseInt(birthDate.substring(4));
        int century = 1800;
        switch (valimerkki) {
            case 'A':
                century = 2000;
                break;
            case '-':
                century = 1900;
                break;
            case '+':
                century = 1800;
        }
        year += century;
        birthDate = birthDate.substring(0, 4) + year;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        LocalDate dateOfBirth = LocalDate.parse(birthDate, formatter);
        LocalDate now = LocalDate.now();
        Period age = Period.between(dateOfBirth, now);
        return age.getYears();
    }

    public String getSukupuoli() {
        StringBuilder yksilointinumeroMuodostaja = new StringBuilder(henkilotunnus);
        String yksilointinumeroMj = yksilointinumeroMuodostaja.substring(7,10);
        int yksilointinumero = Integer.parseInt(yksilointinumeroMj);

        if (yksilointinumero % 2 == 0)
            return "nainen";
        else
            return "mies";
    }
}
