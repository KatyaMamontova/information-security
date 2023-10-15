package information_security.caesars_cipher;

import information_security.EncryptionMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScytaleCipher implements EncryptionMethod {

    public String encrypt(String initialText, int numOfEdges, int lengthOfEdge) {
        StringBuilder encryptedText = new StringBuilder();
        int n = initialText.length();
        for (int i = 0; i < lengthOfEdge; i++) {
            for (int j = 0; j < numOfEdges; j++) {
                if (i + j * lengthOfEdge < n)
                    encryptedText.append(initialText.charAt(i + j * lengthOfEdge));
                else encryptedText.append("*");
            }
        }
        return encryptedText.toString();
    }

    public String decrypt(String encryptedText, int numOfEdges, int lengthOfEdge) {
        StringBuilder decryptedText = new StringBuilder();
        int n = encryptedText.length();
//        for (int i = 0; i < lengthOfEdge; i++) {
//            for (int j = 0; j < numOfEdges; j++) {
//                if (i + j * lengthOfEdge < n)
//                    decryptedText.append(encryptedText.charAt(i + j * lengthOfEdge));
//                else decryptedText.append("*");
//            }
//        }
        for (int i = 0; i < numOfEdges; i++) {
            for (int j = 0; j < lengthOfEdge; j++) {
                System.out.println(decryptedText);
                decryptedText.append(encryptedText.charAt(i + j * numOfEdges));
            }
        }
        return decryptedText.toString();
    }

    private static String reduceOptions(String hackedText) {
        String response = hackedText;
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
                response = null;
                break;
            }
        }
        return response;
    }

    public static List<String> hack(String encryptedText) {
        List<String> decryptedText = new ArrayList<String>();
        StringBuilder oneOption = new StringBuilder();
        int n = encryptedText.length();
        for (int numOfEdges = 2; numOfEdges < n; numOfEdges++) {
            for (int i = 1; i < n - 1; i += numOfEdges) {
                oneOption.append(encryptedText.charAt(i - 1));
            }
//            System.out.println("oneOption " + oneOption);
            if (oneOption.length() > 2)
                decryptedText.add(reduceOptions(oneOption.toString()));
//            decryptedText.add(oneOption.toString());
            oneOption = new StringBuilder();
        }
        return decryptedText;
    }


    @Override
    public void showEncryption() {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String text = in.nextLine().toLowerCase();

        System.out.print("Введите количество рёбер цилиндра: ");
        int n = in.nextInt();

        System.out.print("Введите длину рёбер цилиндра: ");
        int len = in.nextInt();

        String encryptedText = encrypt(text, n, len);

        System.out.println("Зашифрованный текст: ");
        System.out.println(encryptedText);
    }

    @Override
    public void showDecryption() {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите зашифрованный текст: ");
        String text = in.nextLine().toLowerCase();

        System.out.print("Введите количество рёбер цилиндра: ");
        int n = in.nextInt();

        System.out.print("Введите длину рёбер цилиндра: ");
        int len = in.nextInt();

        System.out.println("Расшифрованный текст (варианты): ");
        System.out.println(decrypt(text, n, len));

        List<String> options = hack(text);
        //для отладки удобнее List, потом заменить на HashSet?
        System.out.println("options " + options);
        options.forEach(System.out::println);
    }

    @Override
    public void test() {
        Scanner in = new Scanner(System.in);

        System.out.print("Введите текст: ");
        String text = in.nextLine().toLowerCase();

        System.out.print("Введите количество рёбер цилиндра: ");
        int n = in.nextInt();

        System.out.print("Введите длину рёбер цилиндра: ");
        int len = in.nextInt();

        String encryptedText = encrypt(text, n, len);

        System.out.println("Зашифрованный текст: ");
        System.out.println(encryptedText);

        System.out.println(decrypt(encryptedText, n, len));

        List<String> options = hack(encryptedText);
        //для отладки удобнее List, потом заменить на HashSet?
        System.out.println("options " + options);
        options.forEach(System.out::println);
    }

    @Override
    public void chooseLanguage() {
    }
}
