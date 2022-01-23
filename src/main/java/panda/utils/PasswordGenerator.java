//package panda.utils;
//
//import panda.model.Options;
//
//import java.util.List;
//import java.util.Random;
//
//public class PasswordGenerator {
//
//    private final Options options = Options.getInstance();
//    private static PasswordGenerator instance;
//
//    public static synchronized PasswordGenerator getInstance() {
//        return instance;
//    }
//
//    public String generatePassword(List<String> existPasswordList) {
//        int length = options.getPgLength();
//        String lowCase = options.getPgLowCase();
//        String upCase = options.getPgUpperCase();
//        String nums = options.getPgNums();
//
//        String newPassword;
//
//        if (existPasswordList.size() > 0) {
//            do {
//                newPassword = createPassword(length, lowCase, upCase, nums);
//            } while (passwordValidation(newPassword, existPasswordList));
//        } else {
//            newPassword = createPassword(length, lowCase, upCase, nums);
//        }
//        return newPassword;
//    }
//
//    private String createPassword(int length, String lowCase, String upCase, String nums) {
//        char[] pass = new char[length];
//        StringBuilder password = new StringBuilder();
//        pass[0] = rand(upCase);
//        for (int i = 1; i < 10; i++) {
//            pass[i] = rand(lowCase);
//        }
//        for (int i = 10; i < 15; i++) {
//            pass[i] = rand(nums);
//        }
//        for (int i = 0; i < 15; i++) {
//            password.append(pass[i]);
//        }
//        return password.toString();
//    }
//
//    private char rand(String s) {
//        Random random = new Random();
//        char[] str = s.toCharArray();
//        int rand = random.nextInt(str.length);
//        return str[rand];
//    }
//
//    public boolean passwordValidation(String newPassword, List<String> existPasswordList) {
//        for (String existPassword : existPasswordList) {
//            if (existPassword.equals(newPassword)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//}
