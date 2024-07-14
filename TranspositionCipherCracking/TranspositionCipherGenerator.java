package TranspositionCipherCracking;

public class TranspositionCipherGenerator {
    public static String encrypt(String plainText, int[] key, int columnCount) {
        // 평문 길이 조정
        int fillLength = columnCount - (plainText.length() % columnCount);
        if (fillLength < columnCount) {
            for (int i = 0; i < fillLength; i++) {
                plainText += "z";
            }
        }
        // 이차원 배열 생성
        int rowCount = plainText.length() / columnCount;
        char[][] grid = new char[rowCount][columnCount];

        // 이차원 배열에 평문 삽입
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < columnCount; c++) {
                grid[r][c] = plainText.charAt(r * columnCount + c);
            }
        }

        // 열의 순서 변경
        char[][] rearrangedGrid = new char[rowCount][columnCount];
        for (int c = 0; c < columnCount; c++) {
            int newCol = key[c]; // 인덱스 조정
            for (int r = 0; r < rowCount; r++) {
                rearrangedGrid[r][newCol] = grid[r][c];
            }
        }

        // 변경된 2차원 배열을 열 순서로 읽어 암호문 생성
        StringBuilder encryptedText = new StringBuilder();
        for (int c = 0; c < columnCount; c++) {
            for (int r = 0; r < rowCount; r++) {
                encryptedText.append(rearrangedGrid[r][c]);
            }
        }
        return encryptedText.toString();
    }
}
