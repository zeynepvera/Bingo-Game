/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aa;

/**
 *
 * @author WINCHESTER
 */
import java.lang.Math;

public class Card{ //Cards

    private int[][] table;
    private boolean[][] signsTable;
    private int[] rowsCheck;
    private int[] rowsCheckPre;
    private int tombola;

    public Card(){
        table = new int[3][5];
        signsTable = new boolean[3][5];
        rowsCheck = new int[3];
        rowsCheckPre = new int[3]; //used for the real exercise, there is no need for that
        fillCard(table,signsTable);
        tombola = 0;
    }

    public int[][] getTable(){
        return table;
    }

    public boolean[][] getSignsTable(){
        return signsTable;
    }

    public String check(int n){ //checking the numbers of the card that have been extracted
        String ris = "";
        boolean checked;
        for(int i = 0; i < table.length; i++){
            checked = false;
            for(int j = 0; j < table[i].length && !checked; j++){
                checked = n == table[i][j];
                if (checked && rowsCheck[i] < 5){
                    signsTable[i][j] = true;
                    rowsCheck[i]++;
                }
            }
        }

        for(int i = 0; i < rowsCheck.length; i++){
            if (rowsCheck[i] != rowsCheckPre[i]){
                switch(rowsCheck[i]){
                    //case 1: ris = "null"; break; //case I needed for the real exercise
                    case 1 : break;
                    case 2: ris = ris + prize.AMBO; break;
                    case 3: ris = ris + prize.TERNA; break;
                    case 4: ris = ris + prize.QUATERNA; break;
                    case 5: {
                        ris = ris + prize.CINQUINA;
                        tombola += 5;
                        break;
                    }
                }
            }
            rowsCheckPre[i] = rowsCheck[i];
        }
        if(tombola == 15) { ris = "" + prize.TOMBOLA; tombola = 15; }

        return ris;
    }

    enum prize {
        AMBO,         //<--
        TERNA,        //<--   I'm still trying to figure out how to show
        QUATERNA,     //<--   these results too
        CINQUINA,     //<--
        TOMBOLA
    }

    public static void fillCard(int[][] table, boolean[][] signsTable){ //filling the card with random numbers
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                signsTable[i][j] = false;
                do {
                    table[i][j] = numerFinder(table[i]);
                } while(!differents(table));
            }
        }
    }

    //with this method I check that all numbers in one card are differents
    private static boolean differents(int[][] table){
        int[] bitmap = new int[91];
        for(int i = 0; i < table.length; i++){
            for(int j = 0; j < table[i].length; j++){
                bitmap[table[i][j]]++;
                if (bitmap[table[i][j]] == 2 && table[i][j] != 0) return false;
            }
        }
        return true;
    }

    private static int numerFinder(int[] row){ // in 1 line there can't be 26 and 23 for example
        boolean exists;
        int number;
        do{
            number = (int)(Math.random() * 90) + 1;
            exists = false;
            for(int i = 0; i < row.length && !exists; i++){
                exists = (row[i] - number < 10) && (row[i] - number > - 10);
            }
        }while(exists);
        return number;
    }

}
