/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data_Projects;

/**
 *
 * @author WINCHESTER
 */
public class MultiLinkedList {
    
    Node head; //listenin başı
    
   void addNode(int data){ // yeni bir düğüm eklemek için
       
       Node newNode= new Node(data);
       if(head==null){
           
           head=newNode;
       }else{
           
           Node temp=head;
           while(temp.next!=null){
               
               temp=temp.next;
           }
           temp.next=newNode;
       }
   }
   
   //kartı yazdırmak için metod
   
   void printCard(){
       
       Node temp=head;
               while(temp!=null){
                   
                   System.out.println(temp.data+ "\t");
                   temp=temp.next;
               }
               System.out.println();
   }
   
   //sayının kart üzerinde olup olmadığını kontrol etmek iin metod
   
   boolean contains(int number){
       
       Node temp=head;
       while(temp!=null){
           
           
           if(temp.data==number){
               
               return true;
           }
           temp=temp.next;
       }
       return false;
   }
   
   // Kartın boyutunu döndüren metot
    public int length() {
        int count = 0;
        Node temp = head;
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        return count;
    
}}
