/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aa;

/**
 *
 * @author WINCHESTER
 */
import java.util.Random;

public class TombolaNumbers{ //TombolaNumbers

    private int numbers[];
    private int arrayCheck[];
    private int k;

    public TombolaNumbers(){
        numbers = new int[90];
        arrayCheck = new int[90];
        arrayInitialization();
        k = 0;
    }

    private void arrayInitialization(){
        for(int i = 0; i < numbers.length; i++){
            numbers[i] = i + 1;
        }
    }

    public int[] numberExtraction(int[] updatedNumbers){ //random extraction
        Random rand = new Random();
        int[] results = new int[2];
        boolean exists = false;
        int i;
        do{
            int x = rand.nextInt(updatedNumbers.length);
            for(i = 0; i < updatedNumbers.length && !exists; i++){
                if (i == x){
                    results[0] = updatedNumbers[i]; //saving the number and its index
                    results[1] = i;
                    exists = true;
                }
            }
        }while(!exists);
        arrayCheck[k] = results[0];
        k++;
        return results;
    }

    public int[] getNumeri(){
        return numbers;
    }

}