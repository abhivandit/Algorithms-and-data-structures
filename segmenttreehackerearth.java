/**
*	Name: Abhivandit (abhivandit@gmail.com)
*	Institute: Indian Institute of Information Technology Guwahati 
*/
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class TestClass{
	static int arr[];
    static int arr2[];
	int getSegmentTreeSize(int N) {
  		int size = 1;
  		for (; size < N; size <<= 1);
  		return size << 1;
	}
	public void buildtree(int nodeindex,int from,int to){
		if(from==to){
			arr[nodeindex]=arr2[from];
		}
		else{
		int mid=(from+to)/2;
		int left=2*nodeindex;
		int right=left+1;
		buildtree(left,from,mid);
		buildtree(right,mid+1,to);
		merge(nodeindex,left,right);
	}

	}
	public void merge(int nodeindex,int left,int right){
		arr[nodeindex]=Math.min(arr[left],arr[right]);
	}
	public int query(int stindex,int min,int max,int from,int to){
		if(min==from && max==to){
			return arr[stindex];
		}
		if(from==to){
			return arr[stindex];
		}
		int mid=(from+to)/2;
		int left=2*stindex;
		int right=left+1;
		if(min<=mid && max<=mid){
			return query(left,min,max,from,mid);


		}
		else if(min>mid){
			return query(right,min,max,mid+1,to);
		}
		else{
			int left1=query(left,min,mid,from,mid);
			int right1=query(right,mid+1,max,mid+1,to);
			return Math.min(left1,right1);

		}



	}
	public void update(int stindex,int index,int value,int from,int to){
			if(from==to){
				arr[stindex]=value;
			}
			else{
				int mid=(from+to)/2;
				int left=2*stindex;
				int right=2*stindex+1;
				if(index<=mid){
					update(left,index,value,from,mid);
				}
				else{
					update(right,index,value,mid+1,to);
				}
				merge(stindex,left,right);
			}
	}
	public static void main(String[] args){
	Scanner sc=new Scanner(System.in);
	int number=sc.nextInt();

	 arr2=new int[number];
	int testcases=sc.nextInt();
	for(int i=0;i<number;i++){
		arr2[i]=sc.nextInt();
	}
	TestClass segtree=new TestClass();
    int size=segtree.getSegmentTreeSize(number);
	arr=new int[size];
	
	segtree.buildtree(1,0,number-1);
      /*  for(int i=1;i<size;i++){
		System.out.println(i+" "+arr[i]);
	}*/
	for(int i=0;i<testcases;i++){
		char type=sc.next().charAt(0);
		int value1=sc.nextInt();
		int value2=sc.nextInt();
		
		if(type=='q'){
			System.out.println(segtree.query(1,(value1-1),(value2-1),0,(number-1)));
		}
		else{
			segtree.update(1,(value1-1),value2,0,number-1);
		}

	}
	}
}