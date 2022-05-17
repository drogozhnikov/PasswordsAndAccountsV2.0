package panda.utils;

import java.util.Random;
import java.util.regex.Pattern;

public class PasswordGenerator {

    private final String lowCase = "qwertyuiopasdfghjklzxcvbnm";
    private final String numses = "0123456789";
    private final String pattern = "Rezcjlwige27690";

    private final int minimumSize = 3;
    private final int maximumSize = 49;

    private final Pattern upperCasePattern = Pattern.compile(".*[A-Z].*");
    private final Pattern lowerCasePattern = Pattern.compile(".*[a-z].*");
    private final Pattern numberPattern = Pattern.compile("\\d");

    private final Random random = new Random();

    public PasswordGenerator() {
    }

    public String generatePassword(String inputExample){
        char[] templateArray = pattern.toCharArray();
        if (inputExample != null && inputExample.length() > minimumSize && inputExample.length() < maximumSize) {
            templateArray = inputExample.toCharArray();
        }
        StringBuffer resultPass = new StringBuffer();
        for (int i = 0; i < templateArray.length; i++) {
            String letter = Character.toString(templateArray[i]);
            if (upperCasePattern.matcher(letter).matches()) {
                resultPass.append(getRandomChar(lowCase.toUpperCase()));
            } else if (lowerCasePattern.matcher(letter).matches()) {
                resultPass.append(getRandomChar(lowCase));
            } else if (numberPattern.matcher(letter).matches()) {
                resultPass.append(getRandomChar(numses));
            } else {
                resultPass.append(letter);
            }
        }
        return resultPass.toString();
    }

    private char getRandomChar(String inputLine) {
        char[] str = inputLine.toCharArray();
        int rand = random.nextInt(str.length);
        return str[rand];
    }

}
