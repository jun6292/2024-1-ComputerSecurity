package CaesarCipherCracking;

import java.io.FileNotFoundException;

public class CaesarCipherCracking {
    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "words.txt";
        Long totalTime = 0L;

        for (int i = 0; i < 100; i++) {
            String sentence = RandomSentenceGenerator.generateRandomSentence(filePath);
            System.out.println("Original Sentence: " + sentence);
            String encryptedSentence = CaesarCipherGenerator.encrypt(sentence);
            System.out.println("Encrypted Sentence: " + encryptedSentence);

            Long startTime = System.currentTimeMillis();
            int foundShiftKey = CaesarCipherDecryptor.decrypt(encryptedSentence, sentence);
            Long endTime = System.currentTimeMillis();
            System.out.println("Found matching shift key: " + foundShiftKey);
            totalTime += (endTime - startTime);
        }

        System.out.println("Total Time: " + totalTime + "ms");
        double averageTime = (totalTime / 100.0);
        System.out.println("Average decryption time: " + averageTime + " ms");

    }
}
