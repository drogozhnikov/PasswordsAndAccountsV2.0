package panda.utils;

import java.util.Random;
import java.util.regex.Pattern;

public class PasswordGenerator {

    private String lowCase = "qwertyuiopasdfghjklzxcvbnm";
    private String numses = "0123456789";
    private final String defaultTemplate = "Rezcjlwige27690";

    private int minimumSize = 3;
    private int maximumSize = 49;

    private Pattern upperCasePattern = Pattern.compile(".*[A-Z].*");
    private Pattern lowerCasePattern = Pattern.compile(".*[a-z].*");
    private Pattern numberPattern = Pattern.compile("\\d");

    private final Random random = new Random();

    public PasswordGenerator() {}

    public String generatePassword(String inputExample){
        char[] templateArray = defaultTemplate.toCharArray();
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
