/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data_Projects;

/**
 *
 * @author WINCHESTER
 */
public class Node  {
    
    int data;
    Node next; // ileriye doğru bağlantı için
    Node prev; // geriye-aşağıya doğru bağlantı
    
    public Node(int data, Node next, Node prev){
        
        this.data=data;
        this.next=null;
        this.prev=null;
    }
    
    public Node(int data){
        
        this.data=data;
    }
    
    
    
    
}
