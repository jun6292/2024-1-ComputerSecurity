package TranspositionCipherCracking;

public class TranspositionCipherDecryptor {
    public static String decrypt(String encryptedText, int[] key) {
        int columnCount = key.length;
        int rowCount = encryptedText.length() / columnCount;
        char[][] rearrangedGrid = new char[rowCount][columnCount];

        // 암호문을 2차원 배열에 열 순서대로 삽입
        int charIndex = 0;
        for (int c = 0; c < columnCount; c++) {
            for (int r = 0; r < rowCount; r++) {
                rearrangedGrid[r][c] = encryptedText.charAt(charIndex++);
            }
        }

        // 비밀키의 역을 계산하여 열을 원래 순서로 되돌림
        int[] reverseKey = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            reverseKey[key[i]] = i;
        }

        char[][] originalGrid = new char[rowCount][columnCount];
        for (int c = 0; c < columnCount; c++) {
            int originalCol = reverseKey[c];
            for (int r = 0; r < rowCount; r++) {
                originalGrid[r][originalCol] = rearrangedGrid[r][c];
            }
        }

        // 원래 순서로 되돌린 2차원 배열을 행 순서대로 읽어 평문 복구
        StringBuilder decryptedText = new StringBuilder();
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                decryptedText.append(originalGrid[r][c]);
            }
        }

        return decryptedText.toString().replaceAll("z+$", ""); // 마지막에 추가된 'z' 제거
    }
}
