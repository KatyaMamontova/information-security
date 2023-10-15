package information_security.caesars_cipher;

public class RandomString {
    public static String makeRandomString(int n)
    {
//        String AlphaNumericString = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
        String AlphaNumericString = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }
}
