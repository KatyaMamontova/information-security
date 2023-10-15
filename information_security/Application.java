package information_security;

import information_security.caesars_cipher.ScytaleCipher;
import information_security.vigenere_cipher.VigenereCipher;

import java.util.Scanner;

public class Application {

    static EncryptionMethod encryptionMethod;
    //TODO добавить showHacking
    public static void menu() {
        Scanner in = new Scanner(System.in);
        encryptionMethod.chooseLanguage();

        System.out.println("1 Зашифровать сообщение \n2 Расшифровать сообщение \n3 Тест");
        int num = in.nextInt();
        switch (num) {
            case 1:
                encryptionMethod.showEncryption();
            case 2:
                encryptionMethod.showDecryption();
            case 3:
                encryptionMethod.test();
        }
        ;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {
        System.out.println("1 Скитала (многогранник и бумажная лента) \n2 Квадрат Виженера");

        System.out.print("Выберите метод шифрования: ");
        int cipher = Integer.parseInt(in.nextLine());

        encryptionMethod = switch(cipher) {
            case 1: yield new ScytaleCipher();
            case 2: yield new VigenereCipher();
            default:
                throw new IllegalStateException("Unexpected value: " + cipher);
        };
            menu();
            
        }
    }
}
