package org.example;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

// Made to comply https://dvv.fi/henkilotunnus
// DdMmYy[century][numberId][checkMark]

public class Henkilotunnus {

    private String socialSecurityNumber;
    private int age;
    private String gender;

    // When creating an object instance of class Henkilotunnus, social security number must be valid!
    public Henkilotunnus(String givenSocialSecurityNumber) {
        if (validateSocialSecurityNumber(givenSocialSecurityNumber))
            socialSecurityNumber = givenSocialSecurityNumber;
        else
            throw new IllegalArgumentException("Social Security Number is not in allowed form.");
    }

    // Returns a boolean value of a social security number, whether it is valid or not. Can be used as a static method.
    public static boolean validateSocialSecurityNumber(String givenSocialSecurityNumber) {
        final Pattern PATTERN =
                Pattern.compile("^(0[1-9]|[1-2][0-9]|30|31)(0[1-9]|1[0-2])([0-9][0-9])[-+A][0-9]{3}[0-9A-Z]$");

        Matcher matcher = PATTERN.matcher(givenSocialSecurityNumber);

        StringBuilder ssn = new StringBuilder(givenSocialSecurityNumber);
        String birthDateAndNumberId = ssn.substring(0,6);
        birthDateAndNumberId += ssn.substring(7,10);

        if (birthDateAndNumberId.charAt(0) == '0')
            birthDateAndNumberId = birthDateAndNumberId.substring(1, birthDateAndNumberId.length() - 1);

        char[] ssnCheckMarkList = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'H', 'J',
            'K', 'L', 'M', 'N', 'P', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y'};

        Character ssnCheckMark = ssnCheckMarkList[Integer.parseInt(birthDateAndNumberId) % 31];
        Character userSsnCheckMark = givenSocialSecurityNumber.charAt(givenSocialSecurityNumber.length() - 1);
        boolean validSsnCheckMark = ssnCheckMark.equals(userSsnCheckMark);

        return matcher.matches() && validSsnCheckMark;
    }

    // Calculates age of social security numbers 7 first characters.
    private void calculateAge() {
        StringBuilder dateMonthYear = new StringBuilder(socialSecurityNumber);
        String birthDate = dateMonthYear.substring(0, 6);
        char centuryMark = dateMonthYear.charAt(6);

        int year = Integer.parseInt(birthDate.substring(4));
        int century = 1800;
        switch (centuryMark) {
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
        Period agePeriod = Period.between(dateOfBirth, now);
        age = agePeriod.getYears();
    }

    // Calculates gender based on social security numbers 8 to 10 characters.
    private void calculateGender() {
        StringBuilder idNumberBuilder = new StringBuilder(socialSecurityNumber);
        String idNumberStr = idNumberBuilder.substring(7, 10);
        int idNumber = Integer.parseInt(idNumberStr);

        if (idNumber % 2 == 0)
            gender = "female";
        else
            gender = "male";
    }

    public int getAge() {
        if (age == 0)
            calculateAge();
        return age;
    }

    public String getGender() {
        if (gender == null)
            calculateGender();
        return gender;
    }
}
