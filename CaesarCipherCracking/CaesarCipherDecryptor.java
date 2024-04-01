package CaesarCipherCracking;

public class CaesarCipherDecryptor {
    public static int shiftKey;
    public static int decrypt(String cipherText, String originalText) {
        for (int shift = 1; shift <= 100; shift++) {
            StringBuilder plainTextCandidate = new StringBuilder();
            for (char character : cipherText.toCharArray()) {
                if (character >= 'a' && character <= 'z') {
                    char shifted = (char)(((character - 'a' - shift + 26) % 26) + 'a');
                    plainTextCandidate.append(shifted);
                } else if (character >= 'A' && character <= 'Z') {
                    char shifted = (char)(((character - 'A' - shift + 26) % 26) + 'A');
                    plainTextCandidate.append(shifted);
                } else {
                    // 알파벳이 아닌 경우 변환하지 않고 그대로 추가
                    plainTextCandidate.append(character);
                }
            }
            // 자동으로 올바른 해독을 선택
            if (plainTextCandidate.toString().equals(originalText)) {
                shiftKey = shift;
            }
            System.out.println("Shift " + shift + ": " + plainTextCandidate);
        }
        return shiftKey;
    }
}
