package information_security.vigenere_cipher;

import information_security.EncryptionMethod;

import java.util.Scanner;

public class VigenereCipher implements EncryptionMethod {
    String alphabetString;

    public String encrypt(String text, String keyWord) {
        char[] textToArr = text.replace(" ", "").toUpperCase().toCharArray();
        int count = 0;
        StringBuilder response = new StringBuilder("");
        for (char letter : textToArr
        ) {
            int k = alphabetString.indexOf(letter);
            int m = alphabetString.indexOf(keyWord.charAt(count % keyWord.length()));
            int index = k + m + 1;
            if (index > alphabetString.length() - 1) index -= alphabetString.length();
            response.append(alphabetString.charAt(index));
            count++;
        }
        return String.valueOf(response);
    }

    public String decrypt(String text, String keyWord) {
        char[] textToArr = text.replace(" ", "").toUpperCase().toCharArray();
        int count = 0;
        StringBuilder response = new StringBuilder("");
        for (char letter : textToArr
        ) {
            int m = alphabetString.indexOf(keyWord.charAt(count % keyWord.length()));
            int index = alphabetString.indexOf(letter);
            int k = index - m - 1;
            if (k < 0) k += alphabetString.length();
            response.append(alphabetString.charAt(k));
            count++;
        }
        return String.valueOf(response);
    }

    @Override
    public void showEncryption() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nВведите текст: ");
        String text = in.nextLine();

        System.out.print("\nВведите слово-ключ: ");
        String key = in.nextLine();

        String encryptedText = encrypt(text, key.toUpperCase());

        System.out.println("\nЗашифрованный текст: ");
        System.out.println(encryptedText);
    }

    @Override
    public void showDecryption() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nВведите зашифрованный текст: ");
        String text = in.nextLine();

        System.out.print("\nВведите слово-ключ: ");
        String key = in.nextLine();

        String decryptedText = decrypt(text, key.toUpperCase());

        System.out.println("\nРасшифрованный текст: ");
        System.out.println(decryptedText);
    }

    @Override
    public void test() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nВведите текст: ");
        String text = in.nextLine();

        System.out.print("\nВведите слово-ключ: ");
        String key = in.nextLine();

        String encryptedText = encrypt(text, key.toUpperCase());
        String decryptedText = decrypt(encryptedText, key.toUpperCase());

        System.out.println("\nЗашифрованный текст: ");
        System.out.println(encryptedText);

        System.out.println("\nРасшифрованный текст: ");
        System.out.println(decryptedText);
    }

    public void chooseLanguage() {
        Scanner in = new Scanner(System.in);

        System.out.println("\n1 Русский \n2 Английский");

        System.out.print("\nВыберите язык: ");
        String numOfLang = in.nextLine();


        alphabetString = switch (numOfLang) {
            case "1":
                yield "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
            case "2":
                yield "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            default:
                throw new IllegalStateException("Unexpected value: " + numOfLang);
        };
    }
}
