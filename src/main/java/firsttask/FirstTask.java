package firsttask;

public class FirstTask {
    public static void main(String[] args) {
        System.out.println(encrypt("Abcdefghij", 2));
        System.out.println(decrypt("dhaeibfjcg", 2));
    }

    private static String encrypt(String text, int n) {
        if (n == 0 || text == null) {
            return text;
        } else {
            text = text.toLowerCase();
            char[] textArray = text.toCharArray();
            char[] encryptedArray = new char[text.length()];
            for (int i = 0; i < text.length(); i++) {
                if (i < text.length() / 2) {
                    encryptedArray[i] = textArray[(i * 2) + 1];
                } else {
                    encryptedArray[i] = textArray[(i - (text.length() / 2)) * 2];
                }
            }
            return encrypt(new String(encryptedArray), n - 1);
        }
    }

    private static String decrypt(String encryptedText, int n) {
        if (n == 0 || encryptedText == null) {
            return encryptedText;
        } else {
            encryptedText = encryptedText.toLowerCase();
            char[] textArray = encryptedText.toCharArray();
            char[] decryptedArray = new char[encryptedText.length()];
            for (int i = 0; i < encryptedText.length(); i++) {
                if (i % 2 == 0) {
                    decryptedArray[i] = textArray[(encryptedText.length() + i) / 2];
                } else {
                    decryptedArray[i] = textArray[i / 2];
                }
            }
            return decrypt(new String(decryptedArray), n - 1);
        }
    }
}
