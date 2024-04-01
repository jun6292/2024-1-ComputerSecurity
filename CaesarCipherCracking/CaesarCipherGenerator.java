package CaesarCipherCracking;

import java.util.Random;

public class CaesarCipherGenerator {
    public static String encrypt(String plainText) {
        Random random = new Random();
        int shiftKey = random.nextInt(100) + 1;  // 1~25 사이의 랜덤한 수를 시프트 키로 선택
        StringBuilder cipherText = new StringBuilder();

        for (char ch : plainText.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                char shifted = (char)(((ch - 'a' + shiftKey) % 26) + 'a');
                cipherText.append(shifted);
            } else if (ch >= 'A' && ch <= 'Z') {
                char shifted = (char)(((ch - 'A' + shiftKey) % 26) + 'A');
                cipherText.append(shifted);
            } else {
                // 알파벳이 아닌 경우 변환하지 않고 그대로 추가
                cipherText.append(ch);
            }
        }
        return cipherText.toString();
    }
}
