/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data_Projects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.LinkedList;

/**
 *
 * @author WINCHESTER
 */
public class BingoCard {

    public static void main(String[] args) {
        MultiLinkedList[] cards = new MultiLinkedList[4]; // 4 oyuncu için kartlar

        // Kartları oluşturma
        for (int i = 0; i < cards.length; i++) {
            cards[i] = new MultiLinkedList();
            int[] usedNumbers = new int[90]; // 1'den 90'a kadar olan sayıların kullanılma durumunu tutacak dizi
            Random random = new Random();
            int count = 0;
            while (count < 15) {
                int number = random.nextInt(90) + 1; // 1 ile 90 arasında rastgele sayı seçme
                if (usedNumbers[number - 1] == 0) {
                    cards[i].addNode(number);
                    usedNumbers[number - 1] = 1;
                    count++;
                }
            }
        }

        // Kartları yazdırma
        for (int i = 0; i < cards.length; i++) {
            System.out.println("Kart " + (i + 1) + ":");
            cards[i].printCard();
            System.out.println();
        }

        // Bingo kontrolü ve diğer adımlar
        boolean bingo = false;
        boolean secondBingo = false;
        boolean fullHouse = false;
        int selectedNumbers = 0;
        Set<Integer> calledNumbers = new HashSet<>();
        Random random = new Random();
        while (!bingo && !secondBingo && !fullHouse && selectedNumbers < 90) {
            int calledNumber;
            do {
                calledNumber = random.nextInt(90) + 1; // 1 ile 90 arasında rastgele sayı seçme
            } while (calledNumbers.contains(calledNumber));
            calledNumbers.add(calledNumber);
            selectedNumbers++;

            // Kartları kontrol etme
            for (MultiLinkedList card : cards) {
                if (card.contains(calledNumber)) {
                    System.out.println("Kart " + (Arrays.asList(cards).indexOf(card) + 1) + " üzerinde " + calledNumber + " işaretlendi.");
                    // Bingo kontrolü
                    if (checkBingo(card)) {
                        System.out.println("Bingo!");
                        bingo = true;
                    }
                    // İkinci çinko kontrolü
                    if (checkSecondBingo(card)) {
                        System.out.println("İkinci çinko!");
                        secondBingo = true;
                    }
                    // Tam kart (Full House) kontrolü
                    if (checkFullHouse(card)) {
                        System.out.println("Tam Kart (Full House)!");
                        fullHouse = true;
                    }
                }
            }
        }
    }

    // Bingo kontrolü metodu
    static boolean checkBingo(MultiLinkedList card) {
        return checkRowBingo(card) || checkColumnBingo(card) || checkDiagonalBingo(card);
    }
    // İkinci çinko kontrolü metodu
    static boolean checkSecondBingo(LinkedList card) {
        // İkinci çinko için gerekli kontrol kodu buraya eklenebilir.
        return false;
    }

    // Tam kart (Full House) kontrolü metodu
    static boolean checkFullHouse(LinkedList card) {
        // Tam kart kontrolü için gerekli kontrol kodu buraya eklenebilir.
        return false;
    }
    

// Satır Bingo kontrolü
    static boolean checkRowBingo(MultiLinkedList card) {
        // Her satırı kontrol et
        for (int i = 0; i < card.length(); i += 5) { // Her satırda 5 sayı var
            int count = 0;
            for (int j = i; j < i + 5; j++) {
                if (card.contains(j)) {
                    count++;
                }
            }
            if (count == 5) {
                return true; // Satırda 5 işaretlenmiş sayı var, Bingo!
            }
        }
        return false; // Hiçbir satırda Bingo yok
    }

// Sütun Bingo kontrolü
    static boolean checkColumnBingo(MultiLinkedList card) {
        // Her sütunu kontrol et
        for (int i = 0; i < 5; i++) { // Toplam 5 sütun var
            int count = 0;
            for (int j = i; j < card.length(); j += 5) {
                if (card.contains(j)) {
                    count++;
                }
            }
            if (count == 5) {
                return true; // Sütunda 5 işaretlenmiş sayı var, Bingo!
            }
        }
        return false; // Hiçbir sütunda Bingo yok
    }

// Köşegen Bingo kontrolü
    static boolean checkDiagonalBingo(MultiLinkedList card) {
        // Sol üstten sağ alta doğru kontrol
        int count1 = 0;
        for (int i = 0; i < card.length(); i += 6) { // Sol üstten sağ alta doğru giderken her adımda 6 artar
            if (card.contains(i)) {
                count1++;
            }
        }
        if (count1 == 5) {
            return true; // Sol üstten sağ alta doğru köşegen Bingo!
        }

        // Sağ üstten sol alta doğru kontrol
        int count2 = 0;
        for (int i = 4; i < card.length() - 1; i += 4) { // Sağ üstten sol alta doğru giderken her adımda 4 artar
            if (card.contains(i)) {
                count2++;
            }
        }
        if (count2 == 5) {
            return true; // Sağ üstten sol alta doğru köşegen Bingo!
        }

        return false; // Hiçbir köşegende Bingo yok

    }

    private static boolean checkSecondBingo(MultiLinkedList card) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private static boolean checkFullHouse(MultiLinkedList card) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
