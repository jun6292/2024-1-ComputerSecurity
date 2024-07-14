package CaesarCipherCracking;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomSentenceGenerator {
    public static String generateRandomSentence(String filePath) throws FileNotFoundException {
        List<Long> wordPositions = new ArrayList<>();
        Random random = new Random();
        // 단어의 시작 위치를 저장
        try (RandomAccessFile file = new RandomAccessFile(new File(filePath), "r")) {
            while (file.getFilePointer() < file.length()) {
                wordPositions.add(file.getFilePointer());
                file.readLine(); // 다음 줄로 이동
            }
        } catch (Exception e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return null;
        }

        // 7개 단어 ~ 10개 단어로 이루어진 문장 생성
        int wordCount = 7 + random.nextInt(4);
        StringBuilder sentence = new StringBuilder();

        for (int i = 0; i < wordCount; i++) {
            try (RandomAccessFile file = new RandomAccessFile(new File(filePath), "r")) {
                int selectedPositionIndex = random.nextInt(wordPositions.size());
                file.seek(wordPositions.get(selectedPositionIndex)); // 랜덤한 단어 위치로 이동
                String selectedWord = file.readLine(); // 단어 읽기
                sentence.append(selectedWord);
            } catch (Exception e) {
                System.out.println("Error accessing the file: " + e.getMessage());
                return null;
            }
        }
        return sentence.toString();
    }
}
