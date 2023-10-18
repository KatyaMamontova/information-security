package information_security.caesars_cipher;

import information_security.EncryptionMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScytaleCipher implements EncryptionMethod {

    public String encrypt(String initialText, int lengthOfEdge, int numOfEdges) {
        StringBuilder encryptedText = new StringBuilder();
        int n = initialText.length();
        for (int i = 0; i < numOfEdges; i++) {
            for (int j = 0; j < lengthOfEdge; j++) {
                if (i + j * numOfEdges < n)
                    encryptedText.append(initialText.charAt(i + j * numOfEdges));
                else encryptedText.append("*");
            }
        }
        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, int lengthOfEdge, int numOfEdges) {
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < lengthOfEdge; i++) {
            for (int j = 0; j < numOfEdges; j++) {
                decryptedText.append(encryptedText.charAt(i + j * lengthOfEdge));
            }
        }
        return decryptedText.toString().replace('*', ' ').trim();
    }

    private static String reduceOptions(String hackedText) {
        String response = hackedText;
        if(hackedText.contains("  ")) return null;
        String[] impossibleTogether = {"ёя", "ёь", "ёэ", "ъж", "эё", "ъд", "цё", "уь", "щч", "чй", "шй", "шз", "ыф",
                "жщ", "жш", "жц", "ыъ", "ыэ", "ыю", "ыь", "жй", "ыы", "жъ", "жы", "ъш", "пй", "ъщ", "зщ", "ъч", "ъц",
                "ъу", "ъф", "ъх", "ъъ", "ъы", "ыо", "жя", "зй", "ъь", "ъэ", "ыа", "нй", "еь", "цй", "ьй", "ьл", "ьр",
                "пъ", "еы", "еъ", "ьа", "шъ", "ёы", "ёъ", "ът", "щс", "оь", "къ", "оы", "щх", "щщ", "щъ", "щц", "кй",
                "оъ", "цщ", "лъ", "мй", "шщ", "ць", "цъ", "щй", "йь", "ъг", "иъ", "ъб", "ъв", "ъи", "ъй", "ъп", "ър",
                "ъс", "ъо", "ън", "ък", "ъл", "ъм", "иы", "иь", "йу", "щэ", "йы", "йъ", "щы", "щю", "щя", "ъа", "мъ",
                "йй", "йж", "ьу", "гй", "эъ", "уъ", "аь", "чъ", "хй", "тй", "чщ", "ръ", "юъ", "фъ", "уы", "аъ", "юь",
                "аы", "юы", "эь", "эы", "бй", "яь", "ьы", "ьь", "ьъ", "яъ", "яы", "хщ", "дй", "фй"};
        for (String impsblStr : impossibleTogether
        ) {
            if (hackedText.contains(impsblStr)) {
                return null;
            }
        }
        return response;
    }

    public static List<String> hack(String encryptedText) {
        List<String> decryptedText = new ArrayList<String>();
        String oneOption;
        int n = encryptedText.length();
        for (int lenOfEdge = 1; lenOfEdge < n; lenOfEdge++) {
            oneOption = decrypt(encryptedText, n / lenOfEdge, lenOfEdge);
            if (oneOption.length() > 2) {
                String checkedOption = reduceOptions(oneOption);
                if (checkedOption != null) decryptedText.add(checkedOption);
//                decryptedText.add(oneOption);
            }
        }
        return decryptedText;
    }


    @Override
    public void showEncryption() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nВведите текст: ");
        String text = in.nextLine().toLowerCase();

        System.out.print("\nВведите количество рёбер цилиндра: ");
        int n = in.nextInt();

        System.out.print("Введите длину рёбер цилиндра: ");
        int len = in.nextInt();

        String encryptedText = encrypt(text, len, n);

        System.out.println("\nЗашифрованный текст: ");
        System.out.println(encryptedText);
    }

    @Override
    public void showDecryption() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nВведите зашифрованный текст: ");
        String text = in.nextLine().toLowerCase();

        System.out.print("\nВведите количество рёбер цилиндра: ");
        int n = in.nextInt();

        System.out.print("Введите длину рёбер цилиндра: ");
        int len = in.nextInt();

        System.out.println("\nРасшифрованный текст: ");
        System.out.println(decrypt(text, len, n));
    }

    @Override
    public void test() {
        Scanner in = new Scanner(System.in);

        System.out.print("\nВведите текст: ");
        String text = in.nextLine().toLowerCase();

        System.out.print("\nВведите количество рёбер цилиндра: ");
        int n = in.nextInt();

        System.out.print("Введите длину рёбер цилиндра: ");
        int len = in.nextInt();

        String encryptedText = encrypt(text, len, n);

        System.out.println("\nЗашифрованный текст: ");
        System.out.println(encryptedText);

        System.out.println("\nРасшифрованный текст: ");
        System.out.println(decrypt(encryptedText, len, n));

        System.out.println("\nВзломанный текст (варианты): ");
        List<String> options = hack(encryptedText);
        //для отладки удобнее List, потом заменить на HashSet?
        options.forEach(System.out::println);
    }

    @Override
    public void chooseLanguage() {
    }
}
