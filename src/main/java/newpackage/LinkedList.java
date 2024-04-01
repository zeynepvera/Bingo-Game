/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newpackage;

/**
 *
 * @author WINCHESTER
 */
public class LinkedList<T> {
    
    
    private Node<T> head;
    private int size;
    
    public int size(){
        return size;
    }
    
    public void addFirst(T data){
        
        Node<T> newNode= new Node<>(data);
        if(size==0){
            
            head=newNode;
            size++;
        }else{
            
            newNode.next=head;
            head=newNode;
            size++;
        }
    }
    
    public T getFirst(){
        return null;
    }
    
    public void addLast(T data){
        
        Node<T> newNode= new Node<>(data);
        
        if(size==0){
            
          head= newNode;
          size++;
          return;
            
            
        }
        
        Node<T> tmp=head;
        while(tmp!=null){
            
            tmp=tmp.next;
            
        }
        tmp.next=newNode;
        newNode.prev=tmp;
        size++;
        
        
        
    }
    
    
    public T getLast(){
        
        if(size==0){
            
            return null;
        }
        
        Node <T> tmp= head;
        while(tmp.next!=null){
            
            tmp=tmp.next;
        }
        return tmp.data;
        
        
    }
    
    
    
    public void addToIndex(int index, Node<T> data){
        
        
    }
    
    public T get(int index){
        
        if(index >=size || index<0){
            
            System.out.println("size: "+ size+ " Given index: "+ index);
            throw new IndexOutOfBoundsException();
        }
        Node<T> tmp=head;
        for (int i = 0; i < index; i++) {
            tmp=tmp.next;
        }
        
        return tmp.data;
    }
    
    
    
}


