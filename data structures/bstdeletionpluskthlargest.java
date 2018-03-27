import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Main{
     class Node{
            Node left,right;
            long value;
            long rightlen;
            long leftlen;
            long totalsub;
            long deletionvalue;
            long replacedvalue;

            Node(long item){
                left=right=null;
                value=item;
                rightlen=totalsub=leftlen=0;
            }
    }
    Node root;
    Main(){
        root=null;
    }
    public Node insert(Node temp,Node current ){
        if(current==null){
            current=temp;
        }
        else if(temp.value<current.value){
            //System.out.println(temp.value+" "+current.value);
           current.left=insert(temp,current.left);
           current.leftlen=current.left.totalsub+1;
        }
        else{
                  //  System.out.println(temp.value+" "+current.value);
           current.right=insert(temp,current.right);
           current.rightlen=current.right.totalsub+1;
        }
        current.totalsub=current.rightlen+current.leftlen;
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
           
        System.out.println(current.value+" "+current.rightlen);
        // System.out.println(current.right.value);
          //System.out.println(current.left.value);
        
            preorder(current.left);
       
        
            preorder(current.right);}
       
        
    }
    public void minimum(Node root1){
        if(root1.left!=null){
            minimum(root)
        }
        else{
            replacedvalue=root1.value;
        }
    }
    public void find(long key,Node root1){
        if((root1.rightlen+1)==(key)){
            //System.out.println(root1.value);
            deletionvalue=root1.value;
        

        }
        else{
            if(key<=root1.rightlen){
                find(key,root1.right);

            }
            else{
                find((key-(root.rightlen+1)),root1.left);
            }
        }

    }
    public static void main(String[] args){
    Scanner sc=new Scanner(System.in);
    int numberOfNodes=sc.nextInt();
    int requiredlargest=sc.nextInt();
    Main tree=new Main();
    for(int i=0;i<numberOfNodes;i++){
        
        tree.create(sc.nextLong());
    }
        //long key=sc.nextLong();
       //tree.prepreorder(key,tree.root);
        //tree.preorder(tree.root);

    if(requiredlargest<=numberOfNodes){
         tree.find(requiredlargest,root1);
    }
//ologn
    }
}