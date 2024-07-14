package TranspositionCipherCracking;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TranspositionCipherCracking {
    private static int columnCount = 5;
    private static int[] randomKeyMapGenerator(int columnCount) {
        List<Integer> keyList = new ArrayList<>();
        for (int i = 0; i < columnCount; i++) {
            keyList.add(i);
        }

        // List의 요소를 랜덤하게 섞음
        Collections.shuffle(keyList);
        System.out.println(keyList);

        int[] key = new int[columnCount];
        for (int i = 0; i < columnCount; i++) {
            key[i] = keyList.get(i);
        }
        return key;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "words.txt";
        Long totalTime = 0L;

        int key[] = randomKeyMapGenerator(columnCount); // 열의 순서를 변경하기 위한 비밀키


        for (int i = 0; i < 100; i++) {
            String sentence = RandomSentenceGenerator.generateRandomSentence(filePath);
            System.out.println("Original Sentence: " + sentence);
            String encryptedSentence = TranspositionCipherGenerator.encrypt(sentence, key, columnCount);
            System.out.println("Encrypted Sentence: " + encryptedSentence);

            Long startTime = System.currentTimeMillis();
            String decryptedText = TranspositionCipherDecryptor.decrypt(encryptedSentence, key);
            Long endTime = System.currentTimeMillis();
            System.out.println("Found decryptedText: " + decryptedText);
            System.out.println("\n");
            totalTime += (endTime - startTime);
        }

        System.out.println("Total Time: " + totalTime + "ms");
        double averageTime = (totalTime / 100.0);
        System.out.println("Average decryption time: " + averageTime + " ms");

    }
}
