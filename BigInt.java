import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Main(){
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String next=sc.next();
		BigInteger new1=new BigInteger("0");
		while(((next).equals("0"))==false){
			new1=new1.add(new BigInteger(next));
			
			next=sc.next();
		}
	System.out.println(new1.toString());
	}
}