package utils;

public class GeneratorUtil {
    private static final String ALPHABET_NUM = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";

    public  static String getRandomWord(int count) {
        StringBuilder builder = new StringBuilder();
        while(count-- != 0) {
            int ch = (int)(Math.random()*ALPHABET_NUM.length());
            builder.append(ALPHABET_NUM.charAt(ch));
        }
        return builder.toString();
    }
}
