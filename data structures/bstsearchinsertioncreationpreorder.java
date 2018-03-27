import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class TestClass{
	 class Node{
			Node left,right;
			long value;
			Node(long item){
				left=right=null;
				value=item;
			}
	}
	Node root;
    TestClass(){
        root=null;
    }
    public Node insert(Node temp,Node current ){
        if(current==null){
            current=temp;
        }
        else if(temp.value<current.value){
            //System.out.println(temp.value+" "+current.value);
           current.left=insert(temp,current.left);
        }
        else{
                  //  System.out.println(temp.value+" "+current.value);
           current.right=insert(temp,current.right);
        }
       return current;
    }
    
	public  void create(long item){
        Node temp=new Node(item);
        if(root==null){
            root=temp;
        }
        else{
            insert(temp,root);
        }
        
			
	}
    public void prepreorder(long key,Node root1){
        if(root1.value==key){
         preorder(root1);
            
        }
        else if(root1.value<key){
            prepreorder(key,root1.right);
        }
        else{
            prepreorder(key,root1.left);
        }
    }
    public void preorder(Node current){
        if(current!=null){
           
        System.out.println(current.value);
        // System.out.println(current.right.value);
          //System.out.println(current.left.value);
        
            preorder(current.left);
       
        
            preorder(current.right);}
       
        
    }

	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	int numberOfNodes=sc.nextInt();
    TestClass tree=new TestClass();
	for(int i=0;i<numberOfNodes;i++){
        
        tree.create(sc.nextLong());
	}
        long key=sc.nextLong();
       tree.prepreorder(key,tree.root);
//ologn
	}
}