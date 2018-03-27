import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
 class Main{
	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	while(sc.hasNext()){
		String next1=sc.next();
		//char c= (char)System.in.read();
		char[] chars=next1.toCharArray();
		int length=chars.length;
		int j=0;
		char[] chars2=new char[length];
		
		StringBuilder final1=new StringBuilder();
        StringBuilder temp=new StringBuilder();
        boolean flag=false;
		for(int i=0;i<length;i++){
			if(chars[i]=='['){
				if(flag==true){
					temp.reverse();
					final1.append(temp);
                    temp.setLength(0);
					//counter=0;
				}
				
				flag=true;
			}
			else if(chars[i]==']'){
				if(flag==true){
					temp.reverse();
					final1.append(temp);
                    temp.setLength(0);
				}
				flag=false;
			}
			else{
				if(flag==true){
					temp.append(next1.charAt(i));
				}
				else{
					chars2[j]=next1.charAt(i);
					j++;
				}
			}
		}

		System.out.println(final1.reverse().toString()+String.valueOf(chars2));
	}
	}
}