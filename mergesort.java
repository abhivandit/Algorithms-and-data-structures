import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Main{
   
 static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
 
        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
 
        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
 
        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');
 
            if (neg)
                return -ret;
            return ret;
        }
 
        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }
 
        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
 
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
 
            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }
 
            if (neg)
                return -ret;
            return ret;
        }
 
        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }
 
        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
 
        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
    static int arr[];
    int total=0;
    public void merge(int low,int mid,int high){
    	    	int n1=(mid-low)+1;
    	int n2=(high-mid);
    	int arr1[]=new int[n1];
    	int arr2[]=new int[n2];
    	int k=low;
        int l=mid+1;
        int n=Math.max(n1,n2);
    	for(int i=0;i<n;i++){
            if(i<n1){
    		arr1[i]=arr[k];
    		k++;}
            if(i<n2){
                arr2[j]=arr[l];
            l++;
            }

    	}
    	
    	
    	k=low;
    	int i=0,j=0;
    	while(i<n1 && j<n2){
    		if(arr1[i]<=arr2[j]){
    			arr[k]=arr1[i];
    			i++;
    			k++;

    		}
    		else{
    			arr[k]=arr2[j];
    			k++;
    			j++;
                total=total+(mid-i+1);//ordered pairs such as i<j but a[i]>a[j]
    		}
    	}
    	while(i<n1){
    		arr[k]=arr1[i];
    		k++;
    		i++;
    	}
    	while(j<n2){
    		arr[k]=arr2[j];
    		k++;
    		j++;
    	}
    }
    public void mergesort(int low,int high){
    	if(low<high){
    	int mid=(low+high)/2;
    	mergesort(0,mid);
    	mergesort(mid+1,high);
    	merge(low,mid,high);
    }
    }
    public void printarray(){
        int n=arr.length;
        for(int i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
    }
	public static void main(String[] args)throws IOException{
	Reader sc=new Reader();
	int n=sc.nextInt();
	arr=new int[n];
	for(int i=0;i<n;i++){
		arr[i]=sc.nextInt();
	}
	Main curr=new Main();
	curr.mergesort(0,n-1);
   // curr.printarray();
    System.out.println(curr.total);
	
	}
}