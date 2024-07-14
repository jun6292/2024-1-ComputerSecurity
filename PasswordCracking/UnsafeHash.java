package PasswordCracking;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class UnsafeHash {
    private static Scanner sc = new Scanner(System.in);
    private static final String NUMBERS = "0123456789";
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*";

    // 랜덤 패스워드 생성
    private static String generateRandomPassword(int length, String charSet) {
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charSet.length());
            password.append(charSet.charAt(index));
        }

        // 랜덤 패스워드 확인
        System.out.println("생성된 랜덤 타겟 패스워드: " + password);
        return password.toString();
    }

    // 백트래킹: 패스워드를 생성하고, 생성된 패스워드의 해시와 목표 해시를 비교
    private static boolean generateAndTest(StringBuilder currentPassword, int position, int maxLength, String charSet, int targetHash) throws NoSuchAlgorithmException {
        if (position == maxLength) {
            int testHash = unsafeHash(currentPassword.toString());
            return testHash == targetHash;
        }
        for (char ch : charSet.toCharArray()) {
            currentPassword.append(ch);
            if (generateAndTest(currentPassword, position + 1, maxLength, charSet, targetHash)) {
                return true;
            }
            currentPassword.deleteCharAt(currentPassword.length() - 1); // 마지막 문자 제거
        }
        return false;
    }

    // 브루트 포스 해시 크래커
    public static String crackPassword(int targetHash, int maxLength, String charSet) throws NoSuchAlgorithmException {
        for (int length = 1; length <= maxLength; length++) {
            StringBuilder testPassword = new StringBuilder(length);
            if (generateAndTest(testPassword, 0, length, charSet, targetHash)) {
                return testPassword.toString();
            }
        }
        return null;
    }

    // 안전하지 않은 해시 함수 - 문자열의 각 문자의 아스키 값을 더한 후 1024로 나눈 나머지 반환
    public static int unsafeHash(String input) {
        int hash = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            hash += c; // 각 문자의 아스키 값 더하기
        }
        return hash % 1024; // 16비트 숫자로 제한 2^10
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        // 사용자로부터 패스워드 길이 입력 받기
        System.out.print("Enter password length: ");
        int length = sc.nextInt();

        // 사용자로부터 패스워드 형태 입력 받기 - 1: 숫자, 2: 알파벳, 3: 숫자+알파벳, 4: 숫자+알파벳+특수문자
        System.out.print("Enter password type (1: Numbers, 2: Alphabet, 3: Numbers + Alphabet, 4: Numbers + Alphabet + Special Characters): ");
        int type = sc.nextInt();

        String charSet = "";
        switch (type) {
            case 1:
                charSet = NUMBERS;
                break;
            case 2:
                charSet = ALPHABET;
                break;
            case 3:
                charSet = NUMBERS + ALPHABET;
                break;
            case 4:
                charSet = NUMBERS + ALPHABET + SPECIAL_CHARACTERS;
                break;
        }

        String randomPassword = generateRandomPassword(length, charSet);
        int targetHash = unsafeHash(randomPassword);

        // 시간 측정
        Long startTime = System.currentTimeMillis();
        String foundPassword = crackPassword(targetHash, length, charSet);
        Long endTime = System.currentTimeMillis();

        if (foundPassword != null) {
            System.out.println("Password found: " + foundPassword);
            System.out.println("소요 시간: " + (endTime - startTime) + "ms");
        } else {
            System.out.println("Password not found.");
        }
    }
}
