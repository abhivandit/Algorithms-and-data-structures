import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Main(){

	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		String next=sc.next();
		int j=Integer.parseInt(next);
		BigInteger new1=new BigInteger(next);
		factorial(new1);
		for(long i=1;i<j;i++){
				new1=new1.multiply(i);
		}
	System.out.println(new1.toString());

	}
}
